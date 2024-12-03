package bridge.common;

public enum ErrorMessage {

    INVALID_INPUT_TYPE("숫자로 입력해주세요."),
    INVALID_INPUT_RANGE("다리 길이는 최소 3, 최대 20입니다."),
    INVALID_RETRY_INPUT("재시도는 R 종료는 Q를 입력해주세요."),
    INVALID_USER_INPUT("U 또는 D를 입력해주세요.");

    private static final String HEADER = "[ERROR ]";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return HEADER + message;
    }
}
