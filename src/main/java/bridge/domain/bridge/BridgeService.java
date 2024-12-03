package bridge.domain.bridge;

import bridge.domain.command.RetryCommand;
import bridge.domain.command.UserCommand;

public class BridgeService {

    private final BridgeMaker bridgeMaker;
    private final BridgeGame bridgeGame;

    public BridgeService(final BridgeMaker bridgeMaker, final BridgeGame bridgeGame) {
        this.bridgeMaker = bridgeMaker;
        this.bridgeGame = bridgeGame;
    }

    public Bridge generateBridge(final int size) {
        return new Bridge(bridgeMaker.makeBridge(size));
    }

    public boolean judgeMove(final Bridge bridge, final int step, final UserCommand userCommand) {
        String birdgeStatus = bridge.getStatus(step);
        UserCommand bridgeCommand = UserCommand.getCommand(birdgeStatus);
        return bridgeGame.move(bridgeCommand, userCommand);
    }

    public boolean judgeRetry(final RetryCommand retryCommand) {
        return bridgeGame.retry(retryCommand);
    }
}
