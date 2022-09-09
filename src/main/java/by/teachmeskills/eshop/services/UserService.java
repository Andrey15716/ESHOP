package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.io.Writer;
import java.util.Optional;

@Service
public interface UserService extends BaseServices<User> {

    ModelAndView addNewUser(User user, BindingResult bindingResult);

    ModelAndView getProfileAccount(int pageNumber, int pageSize);

    Optional<User> findByLogin(String username);

    void downloadOrderCsvFile(Writer writer, int id);
}