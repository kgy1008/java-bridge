package bridge.domain.command;

import bridge.common.ErrorMessage;
import java.util.Arrays;

public enum UserCommand {

    UP("U"),
    DOWN("D");

    private final String command;

    UserCommand(final String command) {
        this.command = command;
    }

    public static UserCommand getCommand(final String input) {
        return Arrays.stream(UserCommand.values())
                .filter(userCommand -> userCommand.isEqual(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_USER_INPUT.getMessage()));
    }

    private boolean isEqual(final String command) {
        return this.command.equals(command);
    }
}
