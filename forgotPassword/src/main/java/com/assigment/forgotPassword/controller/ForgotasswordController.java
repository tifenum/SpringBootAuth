//package com.assigment.forgotPassword.controller;
//
//import com.assigment.forgotPassword.service.ForgotPasswordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ForgotasswordController {
//    @Autowired
//    private ForgotPasswordService service;
//
//    @PostMapping("/forgot-password")
//    public String forgotPass(@RequestParam String email){
//        String response = service.forgotPass(email);
//
//        if(!response.startsWith("Invalid")){
//            response= "http://localhost:8000/reset-password?token=" + response;
//        }
//        return response;
//    }
//    @PostMapping("/signup")
//    public String signUp(@RequestParam String username, @RequestParam String email, @RequestParam String password){
//        return service.signUp(username, email, password);
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password){
//        return service.login(username, password);
//    }
//
//    @PutMapping("/reset-password")
//        public String resetPass(@RequestParam String token, @RequestParam String password){
//            return service.resetPass(token,password);
//        }
//
//}
package com.assigment.forgotPassword.controller;

import com.assigment.forgotPassword.entity.User;
import com.assigment.forgotPassword.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForgotasswordController {
    @Autowired
    private ForgotPasswordService service;

    @PostMapping("/forgot-password")
    public String forgotPass(@RequestParam String email){
        String response = service.forgotPass(email);

        if(!response.startsWith("Invalid")){
            response= "http://localhost:8000/reset-password?token=" + response;
        }
        return response;
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        return service.signUp(username, email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return service.login(username, password);
    }

    @PutMapping("/reset-password")
    public String resetPass(@RequestParam String token, @RequestParam String password){
        return service.resetPass(token,password);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username){
        return service.logout(username);
    }

    @GetMapping("/user/{id}")
    public User consulterDonnees(@PathVariable Long id){
        return service.consulterDonnees(id);
    }

    @PutMapping("/user")
    public String modifierUtilisateur(@RequestParam Long id, @RequestParam String username,
                                      @RequestParam String email, @RequestParam String password,
                                      @RequestParam String name){
        return service.modifierUtilisateur(id, username, email, password, name);
    }
}
