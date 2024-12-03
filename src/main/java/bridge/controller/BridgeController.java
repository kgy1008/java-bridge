package bridge.controller;

import bridge.domain.bridge.Bridge;
import bridge.domain.bridge.BridgeService;
import bridge.domain.command.RetryCommand;
import bridge.domain.command.UserCommand;
import bridge.dto.UserBridgeStatus;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class BridgeController {

    private InputView inputView;
    private OutputView outputView;
    private BridgeService bridgeService;

    public BridgeController(final InputView inputView, final OutputView outputView, final BridgeService bridgeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeService = bridgeService;
    }

    public void run() {
        outputView.printWelcomeMessage();
        int size = inputSize();
        boolean flag = true;
        int tryCount = 0;
        int status = 0;
        startBridgeGame(size, flag, tryCount, status);
        printResult(tryCount, status, size);
    }

    private void startBridgeGame(int size, boolean flag, int tryCount, int status) {
        Bridge bridge = bridgeService.generateBridge(size);
        A:
        while (flag) {
            while (status < size) {
                UserCommand userCommand = inputUserCommand();
                boolean movable = move(bridge, status, userCommand);
                printBridgeStatus(bridge, status, movable);
                if (!movable) {
                    flag = retry();
                    tryCount++;
                    status = 0;
                    continue A;
                }
                status++;
            }
        }
    }

    private void printResult(final int tryCount, final int status, final int size) {
        boolean isSuccess = status == size;
        outputView.printResult(tryCount, isSuccess);
    }

    private int inputSize() {
        return retryTemplate(() -> inputView.readBridgeSize());
    }

    private UserCommand inputUserCommand() {
        return retryTemplate(() -> inputView.readMoving());
    }

    private boolean move(final Bridge bridge, final int step, final UserCommand userCommand) {
        return bridgeService.judgeMove(bridge, step, userCommand);
    }

    private boolean retry() {
        RetryCommand retryCommand = inputRetryCommand();
        return bridgeService.judgeRetry(retryCommand);
    }

    private RetryCommand inputRetryCommand() {
        return retryTemplate(() -> inputView.readGameCommand());
    }

    private void printBridgeStatus(final Bridge bridge, final int step, final boolean movable) {
        UserBridgeStatus userBridgeStatus = UserBridgeStatus.from(bridge, step, movable);
        outputView.printMap(userBridgeStatus);
    }

    private <T> T retryTemplate(final Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
