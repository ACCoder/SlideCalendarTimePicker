package net.angrycode.mvp.model;

/**
 * Created by lancelot on 15/11/7.
 */
public class BaseModel {

    private int code;
    private String message;
    private boolean state;

    public BaseModel() {
    }

    public BaseModel(int code, String message, boolean state) {
        this.code = code;
        this.message = message;
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
