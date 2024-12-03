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

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeService bridgeService;

    public BridgeController(final InputView inputView, final OutputView outputView, final BridgeService bridgeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeService = bridgeService;
    }

    public void run() {
        outputView.printWelcomeMessage();
        int size = inputSize();
        int tryCount = 1;
        Bridge bridge = bridgeService.generateBridge(size);

        while (true) {
            boolean gameCompleted = playGame(bridge, size);
            if (gameCompleted || !askRetry()) {
                printResult(tryCount, gameCompleted);
                break;
            }
            tryCount++;
        }
    }

    private boolean playGame(final Bridge bridge, final int size) {
        int currentStep = 0;
        while (currentStep < size) {
            UserCommand userCommand = inputUserCommand();
            boolean movable = move(bridge, currentStep, userCommand);
            printBridgeStatus(bridge, currentStep, movable);
            if (!movable) {
                return false;
            }
            currentStep++;
        }
        return true;
    }

    private boolean askRetry() {
        RetryCommand retryCommand = inputRetryCommand();
        return bridgeService.judgeRetry(retryCommand);
    }

    private void printResult(final int tryCount, final boolean isSuccess) {
        outputView.printResult(tryCount, isSuccess);
    }

    private int inputSize() {
        return retryTemplate(inputView::readBridgeSize);
    }

    private UserCommand inputUserCommand() {
        return retryTemplate(inputView::readMoving);
    }

    private boolean move(final Bridge bridge, final int step, final UserCommand userCommand) {
        return bridgeService.judgeMove(bridge, step, userCommand);
    }

    private RetryCommand inputRetryCommand() {
        return retryTemplate(inputView::readGameCommand);
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
