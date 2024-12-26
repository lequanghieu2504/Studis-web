/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.daoService;

import dao.UserDAO;
import exceptions.BusinessException;
import model.Result;
import model.User;
import utils.hashUtils.HashUtils;

/**
 *
 * @author ho huy
 */
public class UserModificationService extends BaseService {

    private UserDAO userDAO = new UserDAO();

    public Result handleCreate(String name, String email, String password) {
        return executeServiceOperation(() -> {
            if (!userDAO.retrieve("user_name", name).isDefault()) {
                throw new BusinessException("User name already exists.");
            }

            if (!userDAO.retrieve("user_email", email).isDefault()) {
                throw new BusinessException("User email already exists.");
            }

            String salt = HashUtils.generateSalt();
            String hashedPassword = HashUtils.hashPassword(password, salt);

            return userDAO.create(new User(name, email, hashedPassword, salt));
        });
    }

    public Result handleUpdateNameOrEmail(int id, String field, String value) {
        return executeServiceOperation(() -> {
            User updateUser = userDAO.retrieve(id);
            if (updateUser == null || updateUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            if (!userDAO.retrieve(field, value).isDefault()) {
                throw new BusinessException("This " + field + " already exists.");
            }

            switch (field) {
                case "user_name":
                    updateUser.setName(value);
                    break;
                case "user_email":
                    updateUser.setEmail(value);
                    break;
                default:
                    throw new BusinessException("Invalid field for update.");
            }

            return userDAO.update(updateUser);
        });
    }

    public Result handleUpdatePassword(int id, String newPassword) {
        return executeServiceOperation(() -> {
            User updateUser = userDAO.retrieve(id);
            if (updateUser == null || updateUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            String salt = HashUtils.generateSalt();
            String hashedPassword = HashUtils.hashPassword(newPassword, salt);

            updateUser.setHashedPassword(hashedPassword);
            updateUser.setSalt(salt);

            return userDAO.update(updateUser);
        });
    }

    public Result handleDelete(int id) {
        return executeServiceOperation(() -> {
            User deleteUser = userDAO.retrieve(id);
            if (deleteUser == null || deleteUser.isDefault()) {
                throw new BusinessException("User not found or is default.");
            }

            return userDAO.delete(deleteUser);
        });
    }

}
