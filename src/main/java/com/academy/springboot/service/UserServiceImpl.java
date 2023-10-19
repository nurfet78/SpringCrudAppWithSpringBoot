package com.academy.springboot.service;

import com.academy.springboot.model.User;
import com.academy.springboot.problem.NotFoundException;
import com.academy.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("firstName").ascending());
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));

        userRepository.delete(user);
    }


    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
    }
}
