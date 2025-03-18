package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Role;
import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.RoleRepository;
import com.example.cakeprj.Repository.UserRepository;
import com.example.cakeprj.dto.request.UserCreationRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection (Best Practice)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void save(UserCreationRequest user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        Users userToSave = new Users();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(encodedPassword);
        userToSave.setEmail(user.getEmail());
        userToSave.setFirstName(user.getFirstName());
        userToSave.setLastName(user.getLastName());

        // Assign default role
        Set<Role> userRoles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        Role userRole = optionalRole.orElseGet(() -> {
            Role newRole = new Role("ROLE_USER");
            return roleRepository.save(newRole);
        });

        userRoles.add(userRole);
        userToSave.setRoles(userRoles);

        userRepository.save(userToSave);
    }

    public void updateUserPassword(UUID userId, String newPassword) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void updateUser(Users updatedUser){
        Users existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + updatedUser.getId()));
        if (!existingUser.getUsername().equals(updatedUser.getUsername()) &&
                userRepository.existsByUsername(updatedUser.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

        userRepository.save(existingUser);
    }

    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Long countRegisteredUser(){
        return userRepository.count();
    }

    public Long countUsersWithUserRole() {
        return userRepository.countUsersByRole("ROLE_USER");
    }
}
