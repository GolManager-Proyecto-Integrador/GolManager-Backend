package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User getUserByEmail(String email);
}
