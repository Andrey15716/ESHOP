package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationsExceptions;
import by.teachmeskills.eshop.exceptions.RepositoryExceptions;
import by.teachmeskills.eshop.exceptions.ServiceExceptions;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface UserService extends BaseServices<User> {
    ModelAndView authenticate(User user) throws ServiceExceptions, RepositoryExceptions, AuthorizationsExceptions;

    ModelAndView addNewUser(User user) throws ServiceExceptions, RepositoryExceptions;

    ModelAndView getProfileAccount(User user, int pageNumber, int pageSize) throws ServiceExceptions, RepositoryExceptions;
}