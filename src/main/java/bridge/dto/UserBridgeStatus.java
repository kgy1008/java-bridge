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
            String status = bridge.getStatus(i);
            if (movable || i != step) {
                addMovableStatus(status, upper, lower);
            } else {
                addUnmovableStatus(status, upper, lower);
            }
        }
        return new UserBridgeStatus(upper, lower);
    }

    private static void addMovableStatus(String status, List<String> upper, List<String> lower) {
        if (status.equals("U")) {
            upper.add("O");
            lower.add(" ");
        } else {
            lower.add("O");
            upper.add(" ");
        }
    }

    private static void addUnmovableStatus(String status, List<String> upper, List<String> lower) {
        if (status.equals("U")) {
            upper.add(" ");
            lower.add("X");
        } else {
            upper.add("X");
            lower.add(" ");
        }
    }
}
