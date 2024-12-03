package bridge.view;

import bridge.common.ErrorMessage;
import bridge.domain.command.RetryCommand;
import bridge.domain.command.UserCommand;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 20;

    public int readBridgeSize() {
        String input = Console.readLine();
        int size = convertToInt(input);
        validateSize(size);
        return size;
    }

    public UserCommand readMoving() {
        String input = Console.readLine();
        return UserCommand.getCommand(input);
    }

    public RetryCommand readGameCommand() {
        String input = Console.readLine();
        return RetryCommand.getCommand(input);
    }

    private int convertToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_TYPE.getMessage());
        }
    }

    private void validateSize(final int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_RANGE.getMessage());
        }
    }
}
