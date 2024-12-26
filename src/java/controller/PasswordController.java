/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Result;
import service.daoService.UserModificationService;
import service.daoService.UserRetrieveService;

/**
 *
 * @author ho huy
 */
public class PasswordController {
    
    private UserModificationService ums = new UserModificationService();
    private UserRetrieveService urs = new UserRetrieveService();
    
    public Result updatePassword(String email, String password){
        Result findId = urs.handleRetrieveId("user_email", email);
        return ums.handleUpdatePassword((int) findId.getData(), password);
    }
}
