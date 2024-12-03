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
    private static final String NEW_LINE = System.lineSeparator();

    public int readBridgeSize() {
        System.out.println("다리의 길이를 입력해주세요.");
        String input = Console.readLine();
        int size = convertToInt(input);
        validateSize(size);
        return size;
    }

    public UserCommand readMoving() {
        System.out.println(NEW_LINE + "이동할 칸을 선택해주세요. (위: U, 아래: D)" );
        String input = Console.readLine();
        return UserCommand.getCommand(input);
    }

    public RetryCommand readGameCommand() {
        System.out.println(NEW_LINE + "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
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
