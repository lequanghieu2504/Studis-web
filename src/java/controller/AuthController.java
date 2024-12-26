/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Result;
import service.businessService.AuthService;

/**
 *
 * @author ho huy
 */
public class AuthController {
    
    private AuthService authService = new AuthService();
    
    public Result handleLogin(String nameOrEmail, String password){
        return authService.login(nameOrEmail, password);
    }
    
    public Result handleRegister(String name, String email, String password, String confirmPassword){
        return authService.register(name, email, password, confirmPassword);
    }
}
