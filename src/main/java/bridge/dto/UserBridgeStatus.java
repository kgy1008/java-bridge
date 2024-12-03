package bridge.dto;

import bridge.domain.bridge.Bridge;
import java.util.ArrayList;
import java.util.List;

public record UserBridgeStatus(
        List<String> upper,
        List<String> lower
) {
    public static UserBridgeStatus from(final Bridge bridge, final int step, final boolean movable) {
        List<String> upper = new ArrayList<>();
        List<String> lower = new ArrayList<>();
        for (int i = 0; i <= step; i++) {
            if (movable) {
                if (bridge.getStatus(i).equals("U")) {
                    upper.add("O");
                    lower.add(" ");
                } else {
                    lower.add("O");
                    upper.add(" ");
                }
            } else {
                if (bridge.getStatus(i).equals("U")) {
                    upper.add("X");
                    lower.add(" ");
                } else {
                    lower.add("X");
                    upper.add(" ");
                }
            }
        }
        return new UserBridgeStatus(upper, lower);
    }
}
