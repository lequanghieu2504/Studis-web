/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ho huy
 */
public class Result<T> {

    private int status;
    private String message;
    private T Data;
    private String errorDetails;

    public Result(int status, String message, T Data, String errorDetails) {
        this.status = status;
        this.message = message;
        this.Data = Data;
        this.errorDetails = errorDetails;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return Data;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    @Override
    public String toString() {
        return "Result{" + "status=" + status + ", message=" + message + ", Data=" + Data + ", errorDetails=" + errorDetails + '}';
    }

}
