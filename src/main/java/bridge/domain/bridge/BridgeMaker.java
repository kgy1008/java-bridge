package bridge.domain.bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BridgeMaker {

    private static final int MOVABLE = 1;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        List<String> bridge = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int num = bridgeNumberGenerator.generate();
            if (num == MOVABLE) {
                bridge.add("U");
            } else {
                bridge.add("D");
            }
        }
        return Collections.unmodifiableList(bridge);
    }
}
