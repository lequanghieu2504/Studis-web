/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.resultUtils;

import model.Result;
import model.User;

/**
 *
 * @author ho huy
 */
public class ResultMapper {

    public static Result<User> userResultMapper(User user) {
        if (user == null) {
            return new Result<>(
                    500,
                    "An error occurred while performing the operation.",
                    null,
                    "The operation could not be completed due to an unexpected error."
            );
        }

        if (user.isDefault()) {
            return new Result<>(
                    400,
                    "The operation was not successful.",
                    user,
                    "The user returned is marked as default, indicating the operation did not succeed."
            );
        }

        return new Result<>(
                200,
                "The operation was completed successfully.",
                user,
                null
        );
    }

}
