package timofey.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timofey.db.repositories.UserRepository;
import timofey.entities.Resource;
import timofey.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByTelegramId(Long telegramId) {
        return userRepository.findByTelegramUserId(telegramId).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteByTelegramId(Long telegramId) {
        userRepository.deleteById(telegramId);
    }

    @Override
    public void subscribeUserToSource(int userId, int resourceId) {

    }

    @Override
    public List<Resource> getAllSubscriptions() {
        return null;
    }

}
