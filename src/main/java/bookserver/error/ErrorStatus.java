package bookserver.error;

public enum ErrorStatus {
    USERNAME_OR_PASSWORD_ERROR(-1, "username or password error"),
    USER_NOT_EXIST(-2, "user not exist");

    private int code;
    private String message;

    ErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
