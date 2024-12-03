package bridge.config;

import bridge.controller.BridgeController;
import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeRandomNumberGenerator;
import bridge.domain.bridge.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;

public class AppConfig {

    public BridgeController bridgeController() {
        return new BridgeController(new InputView(),new OutputView(), bridgeService());
    }

    private BridgeService bridgeService() {
        return new BridgeService(new BridgeMaker(new BridgeRandomNumberGenerator()), new BridgeGame());
    }
}
