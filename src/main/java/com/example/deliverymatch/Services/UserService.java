package com.example.deliverymatch.Services;

import com.example.deliverymatch.dtos.UserDTO;
import com.example.deliverymatch.entities.User;
import com.example.deliverymatch.mappers.UserMapper;
import com.example.deliverymatch.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper mapper, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO updateUser(Long id, UserDTO dto){
        User user = repository.findById(id).orElse(null);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }

    public List<UserDTO> getUsersList(){
        List<User> users = repository.findAll();
        return mapper.toDTOs(users);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
