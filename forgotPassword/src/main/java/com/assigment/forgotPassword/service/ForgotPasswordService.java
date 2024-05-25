//package com.assigment.forgotPassword.service;
//
//import com.assigment.forgotPassword.entity.User;
//import com.assigment.forgotPassword.repository.ForgotPasswordRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class ForgotPasswordService {
//    private static final long EXPIRE_TOKEN=30;
//
//    @Autowired
//    private ForgotPasswordRepo repo;
//
//    public String forgotPass(String email){
//        Optional<User> userOptional = Optional.ofNullable(repo.findByEmail(email));
//
//        if(!userOptional.isPresent()){
//            return "Invalid email id.";
//        }
//
//        User user=userOptional.get();
//        user.setToken(generateToken());
//        user.setTokenCreationDate(LocalDateTime.now());
//
//        user=repo.save(user);
//        return user.getToken();
//    }
//    public String signUp(String username, String email, String password) {
//        if (repo.existsByUsername(username)) {
//            return "Username already exists.";
//        }
//
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(email);
//        user.setPassword(password);
//
//        user = repo.save(user);
//        return "User registered successfully.";
//    }
//    public String login(String username, String password) {
//        User user = repo.findByUsername(username);
//
//        if (user == null) {
//            return "User not found.";
//        }
//
//        if (!user.getPassword().equals(password)) {
//            return "Invalid password.";
//        }
//
//        return "Login successful.";
//    }
//    public String resetPass(String token, String password){
//        Optional<User> userOptional= Optional.ofNullable(repo.findByToken(token));
//
//        if(!userOptional.isPresent()){
//            return "Invalid token";
//        }
//        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();
//
//        if (isTokenExpired(tokenCreationDate)) {
//            return "Token expired.";
//
//        }
//
//        User user = userOptional.get();
//
//        user.setPassword(password);
//        user.setToken(null);
//        user.setTokenCreationDate(null);
//
//        repo.save(user);
//
//        return "Your password successfully updated.";
//    }
//
//    private String generateToken() {
//        StringBuilder token = new StringBuilder();
//
//        return token.append(UUID.randomUUID().toString())
//                .append(UUID.randomUUID().toString()).toString();
//    }
//
//    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
//
//        LocalDateTime now = LocalDateTime.now();
//        Duration diff = Duration.between(tokenCreationDate, now);
//
//        return diff.toMinutes() >=EXPIRE_TOKEN;
//    }
//
//}
package com.assigment.forgotPassword.service;

import com.assigment.forgotPassword.entity.User;
import com.assigment.forgotPassword.repository.ForgotPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {
    private static final long EXPIRE_TOKEN=30;

    @Autowired
    private ForgotPasswordRepo repo;

    public String forgotPass(String email){
        Optional<User> userOptional = Optional.ofNullable(repo.findByEmail(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user=userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user=repo.save(user);
        return user.getToken();
    }

    public String signUp(String username, String email, String password) {
        if (repo.existsByUsername(username)) {
            return "Username already exists.";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        user = repo.save(user);
        return "User registered successfully.";
    }

    public String login(String username, String password) {
        User user = repo.findByUsername(username);

        if (user == null) {
            return "User not found.";
        }

        if (!user.getPassword().equals(password)) {
            return "Invalid password.";
        }

        return "Login successful.";
    }

    public String logout(String username) {
        User user = repo.findByUsername(username);

        if (user == null) {
            return "User not found.";
        }

        user.setToken(null);
        user.setTokenCreationDate(null);
        repo.save(user);

        return "Logout successful.";
    }

    public List<User> consulterDonner() {
        return repo.findAll();
    }

    public User consulterDonnees(Long id) {
        Optional<User> userOptional = repo.findById(id);
        return userOptional.orElse(null);
    }

    public String modifierUtilisateur(Long id, String username, String email, String password, String name) {
        Optional<User> userOptional = repo.findById(id);

        if (!userOptional.isPresent()) {
            return "User not found.";
        }

        User user = userOptional.get();
        if (repo.existsByUsername(username) && !user.getUsername().equals(username)) {
            return "Username already exists.";
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        repo.save(user);
        return "User details updated successfully.";
    }

    public String resetPass(String token, String password){
        Optional<User> userOptional= Optional.ofNullable(repo.findByToken(token));

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }

        User user = userOptional.get();

        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);

        repo.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >=EXPIRE_TOKEN;
    }
}
