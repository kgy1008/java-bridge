package bridge.domain.bridge;

import java.util.List;

public class Bridge {

    private final List<String> bridge;

    public Bridge(final List<String> bridge) {
        this.bridge = bridge;
    }

    public String getStatus(final int idx) {
        return bridge.get(idx);
    }
}
