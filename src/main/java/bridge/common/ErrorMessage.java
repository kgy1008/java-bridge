package bridge.common;

public enum ErrorMessage {

    INVALID_INPUT_TYPE("숫자로 입력해주세요.");

    private static final String HEADER = "[ERROR ]";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return HEADER + message;
    }
}
