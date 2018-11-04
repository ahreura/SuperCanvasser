package team830.SuperCanvasser.User;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public interface UserInterface {

    User editUser(User user);
    User addUser(User user);
    User getUserByEmail(String email);
    User getUserBy_id(String id);
    User loginUser(User user) throws UnsupportedEncodingException;
    User getUserBy_id(String _id);

}
