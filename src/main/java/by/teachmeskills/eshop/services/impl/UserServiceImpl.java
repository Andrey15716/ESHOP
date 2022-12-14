package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Role;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.RoleRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import by.teachmeskills.eshop.services.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.EshopConstants.ERROR;
import static by.teachmeskills.eshop.utils.EshopConstants.ID;
import static by.teachmeskills.eshop.utils.EshopConstants.NAME_FIELD;
import static by.teachmeskills.eshop.utils.EshopConstants.PASSWORD_FIELD;
import static by.teachmeskills.eshop.utils.EshopConstants.ROLE_USER;
import static by.teachmeskills.eshop.utils.EshopConstants.SURNAME_FIELD;
import static by.teachmeskills.eshop.utils.PagesPathEnum.PROFILE_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.REGISTRATION_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.REGISTRATION_SUCCESS_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.ERROR_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_FIRST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_LAST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.LOGGED_IN_USER_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.LOGIN_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.NUMBER_OF_PAGES;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_NUMBER;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_SIZE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.TOTAL_ELEMENTS;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.USER_ORDERS;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository,
                           PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User entity) {
        Role role = roleRepository.findRoleByName(ROLE_USER);
        entity.setRole(role);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        log.info("Password has been successfully encoded");
        return userRepository.save(entity);
    }

    @Override
    public List<User> read() {
        return userRepository.findAll();
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public ModelAndView addNewUser(User user, BindingResult bindingResult) throws AuthenticationException {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        String username = user.getName();
        if (bindingResult.hasErrors()) {
            fieldError(NAME_FIELD, modelAndView, bindingResult);
            fieldError(SURNAME_FIELD, modelAndView, bindingResult);
            fieldError(PASSWORD_FIELD, modelAndView, bindingResult);
            modelAndView.setViewName(REGISTRATION_PAGE.getPath());
            return modelAndView;
        }
        if (userRepository.getUserByName(username).isEmpty()) {
            create(user);
            log.info("New user has been added!");
            modelMap.addAttribute(LOGIN_PARAM.getValue(), username);
            return new ModelAndView(REGISTRATION_SUCCESS_PAGE.getPath(), modelMap);
        } else {
            modelMap.addAttribute(ERROR_PARAM.getValue(), "This user with name " + user.getName() + " allready exist");
            log.error("User with login " + user.getName() + " has already exist, can`t create account");
        }
        return new ModelAndView(REGISTRATION_PAGE.getPath(), modelMap);
    }

    private void fieldError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + ERROR,
                    Objects.requireNonNull(bindingResult.getFieldError(field))
                            .getDefaultMessage());
        }
    }

    @Override
    public ModelAndView getProfileAccount(int pageNumber, int pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            userRepository.getUserByName(loggedInUser).ifPresent(user -> {
                modelMap.addAttribute(LOGGED_IN_USER_PARAM.getValue(), user);
                Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(ID).descending());
                Page<Order> userOrders = orderRepository.getOrdersByUserId(user.getId(), paging);
                modelMap.addAttribute(NUMBER_OF_PAGES.getValue(), userOrders.getTotalPages());
                modelMap.addAttribute(USER_ORDERS.getValue(), userOrders.getContent());
                modelMap.addAttribute(PAGE_SIZE.getValue(), pageSize);
                modelMap.addAttribute(IS_FIRST_PAGE.getValue(), userOrders.isFirst());
                modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
                modelMap.addAttribute(IS_LAST_PAGE.getValue(), userOrders.isLast());
                modelMap.addAttribute(TOTAL_ELEMENTS.getValue(), userOrders.getTotalElements());
                modelAndView.setViewName(PROFILE_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
                log.info("Profile page has been selected");
            });
        } catch (Exception e) {
            log.error("Profile page is not presented");
        }
        return modelAndView;
    }

    @Override
    public Optional<User> findByLogin(String name) {
        return userRepository.getUserByName(name);
    }

    @Override
    public void downloadOrderCsvFile(Writer writer, int userId) {
        List<Order> order = orderRepository.getOrdersByUserId(userId);
        try {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(order);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}