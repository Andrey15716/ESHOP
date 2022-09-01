package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationsExceptions;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import by.teachmeskills.eshop.repositories.OrderRepository;
import by.teachmeskills.eshop.repositories.UserRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.utils.PagesPathEnum.PROFILE_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.REGISTRATION_SUCCESS_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.START_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.CATEGORIES_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_FIRST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.IS_LAST_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.LOGGED_IN_USER_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.LOGIN_PARAM;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.NUMBER_OF_PAGES;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_NUMBER;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.PAGE_SIZE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.USER_ORDERS;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.orderRepository = orderRepository;
    }

    @Override
    public User create(User entity) throws ServiceExceptions, RepositoryExceptions {
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
    public ModelAndView authenticate(User user) throws ServiceExceptions, RepositoryExceptions, AuthorizationsExceptions {
        ModelAndView modelAndView = new ModelAndView();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getName()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.getUserByNameAndPassword(user.getName(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                ModelMap modelMap = new ModelMap();
                List<Category> categoriesList = categoryService.read();
                modelMap.addAttribute(CATEGORIES_PARAM.getValue(), categoriesList);
                modelAndView.setViewName(START_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
                log.info("User is authenticated!");
            } else {
                log.info("User is not found!");
                throw new AuthorizationsExceptions("User is not authorised!");
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView addNewUser(User user) throws ServiceExceptions, RepositoryExceptions {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        String username = user.getName();
        modelMap.addAttribute(LOGIN_PARAM.getValue(), username);
        modelAndView.addObject(modelMap);
        create(user);
        modelAndView.setViewName(REGISTRATION_SUCCESS_PAGE.getPath());
        log.info("New user has been added!");
        return modelAndView;
    }

    @Override
    public ModelAndView getProfileAccount(User user, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        User loggedInUser = userRepository.getUserByNameAndPassword(user.getName(), user.getPassword());
        modelMap.addAttribute(LOGGED_IN_USER_PARAM.getValue(), loggedInUser);
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<Order> userOrders = orderRepository.getOrdersByUserId(loggedInUser.getId(), paging);
        modelMap.addAttribute(NUMBER_OF_PAGES.getValue(), userOrders.getTotalPages());
        modelMap.addAttribute(USER_ORDERS.getValue(), userOrders.getContent());
        modelMap.addAttribute(PAGE_SIZE.getValue(), pageSize);
        modelMap.addAttribute(IS_FIRST_PAGE.getValue(), userOrders.isFirst());
        modelMap.addAttribute(PAGE_NUMBER.getValue(), pageNumber);
        modelMap.addAttribute(IS_LAST_PAGE.getValue(), userOrders.isLast());
        modelAndView.setViewName(PROFILE_PAGE.getPath());
        modelAndView.addAllObjects(modelMap);
        log.info("Profile page has been selected");
        return modelAndView;
    }

    @Override
    public Optional<User> findByLogin(String username) {
        return Optional.ofNullable(userRepository.getUserByName(username));
    }
}