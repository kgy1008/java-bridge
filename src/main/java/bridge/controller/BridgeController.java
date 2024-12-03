package bridge.controller;

import bridge.domain.bridge.Bridge;
import bridge.domain.bridge.BridgeService;
import bridge.domain.command.RetryCommand;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;
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
        int size = inputSize();
        Bridge bridge = bridgeService.generateBridge(size);
        RetryCommand retryCommand = RetryCommand.START;
        while (retryCommand == RetryCommand.START) {

        }

    }

    private int inputSize() {
        return retryTemplate(() -> inputView.readBridgeSize());
    }

    private RetryCommand inputRetryCommand() {
        return retryTemplate(() -> inputView.readGameCommand());
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

    private void retryTemplate(final Runnable action) {
        while (true) {
            try {
                action.run();
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
