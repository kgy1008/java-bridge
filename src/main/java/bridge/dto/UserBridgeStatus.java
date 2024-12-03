package bridge.dto;

import bridge.domain.bridge.Bridge;

import java.util.ArrayList;
import java.util.List;

public record UserBridgeStatus(
        List<String> upperLadder,
        List<String> lowerLadder
) {
    private static final String UPPER = "U";
    private static final String LOWER = "O";
    private static final String EMPTY = " ";
    private static final String FAIL = "X";

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
        if (status.equals(UPPER)) {
            upper.add(LOWER);
            lower.add(EMPTY);
        } else {
            lower.add(LOWER);
            upper.add(EMPTY);
        }
    }

    private static void addUnmovableStatus(String status, List<String> upper, List<String> lower) {
        if (status.equals(UPPER)) {
            upper.add(EMPTY);
            lower.add(FAIL);
        } else {
            upper.add(FAIL);
            lower.add(EMPTY);
        }
    }
}
