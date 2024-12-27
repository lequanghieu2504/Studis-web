package utils.validator;

import model.entity.Result;

/**
 * Validator is a generic interface that defines the structure for validation classes.
 * Any class implementing this interface is expected to provide its own validation logic for a specific type of input.
 * 
 * @param <T> The type of value to be validated (e.g., String, Integer, etc.).
 */
public interface Validator<T> {
    
    /**
     * Validates the given value.
     * 
     * @param value The value to be validated. It can be any object of type T.
     * @return A Result object indicating whether the validation was successful or not.
     *         It will contain success or failure messages, and any additional details.
     */
    public Result<Void> validate(T value);
}
