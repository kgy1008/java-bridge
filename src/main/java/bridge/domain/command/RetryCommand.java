package bridge.domain.command;

import bridge.common.ErrorMessage;
import java.util.Arrays;

public enum RetryCommand {

    START("R"),
    QUIT("Q");

    private final String command;

    RetryCommand(final String command) {
        this.command = command;
    }

    public static RetryCommand getCommand(final String input) {
        return Arrays.stream(RetryCommand.values())
                .filter(retryCommand -> retryCommand.isEqual(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_RETRY_INPUT.getMessage()));
    }

    private boolean isEqual(final String command) {
        return this.command.equals(command);
    }
}
