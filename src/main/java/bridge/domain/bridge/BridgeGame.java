package bridge.domain.bridge;

import bridge.domain.command.RetryCommand;
import bridge.domain.command.UserCommand;

public class BridgeGame {

    public boolean move(final UserCommand bridgeCommand, final UserCommand userCommand) {
        if (bridgeCommand == userCommand) {
            return true;
        }
        return false;
    }

    public boolean retry(final RetryCommand retryCommand) {
        return retryCommand == RetryCommand.START;
    }
}
