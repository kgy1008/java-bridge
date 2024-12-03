package bridge.domain.bridge;

import java.util.List;

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
}
