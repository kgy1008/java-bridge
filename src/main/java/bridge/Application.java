package bridge;

import bridge.config.AppConfig;
import bridge.controller.BridgeController;

public class Application {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        BridgeController bridgeController = appConfig.bridgeController();
        bridgeController.run();
    }
}
