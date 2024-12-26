/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.daoService;

import dao.UserDAO;
import model.Result;
import model.User;
import utils.resultUtils.ResultMapper;

/**
 *
 * @author ho huy
 */
public class UserRetrieveService extends BaseService {

    private UserDAO userDAO = new UserDAO();

    public Result handleRetrieve(int id) {
        try {
            User result = userDAO.retrieve(id);
            return ResultMapper.userResultMapper(result);
        } catch (Exception e) {
            return new Result<>(500, "Error occurred while retrieving user.", null, e.getMessage());
        }
    }

    public Result handleRetrieve(String nameOrEmail) {
        try {
            User result = userDAO.retrieve(nameOrEmail);
            return ResultMapper.userResultMapper(result);
        } catch (Exception e) {
            return new Result<>(500, "Error occurred while retrieving user.", null, e.getMessage());
        }
    }

    public Result handleRetrieve(String field, String value) {
        try {
            User result = userDAO.retrieve(field, value);
            return ResultMapper.userResultMapper(result);
        } catch (Exception e) {
            return new Result<>(500, "Error occurred while retrieving user.", null, e.getMessage());
        }
    }

    public Result handleRetrieveId(String field, String value) {
        try {
            int result = userDAO.retrieveId(field, value);
            if (result == -1) {
                return new Result<>(
                        400,
                        "The operation was not successful.",
                        result,
                        "The user returned is marked as default, indicating the operation did not succeed."
                );
            }
            return new Result<>(
                    200,
                    "The operation was completed successfully.",
                    result,
                    null
            );
        } catch (Exception e) {
            return new Result<>(500, "Error occurred while retrieving user.", null, e.getMessage());
        }
    }
}
