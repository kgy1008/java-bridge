package bridge.view;

import bridge.dto.UserBridgeStatus;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public void printWelcomeMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println();
    }

    public void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public void printMap(final UserBridgeStatus userBridgeStatus) {
        printUpperMap(userBridgeStatus);
        printLowerMap(userBridgeStatus);
    }

    private void printUpperMap(final UserBridgeStatus userBridgeStatus) {
        System.out.print("[ ");
        String upperResult = String.join(" | ", userBridgeStatus.upperLadder());
        System.out.print(upperResult);
        System.out.print(" ]" + NEW_LINE);
    }

    private void printLowerMap(final UserBridgeStatus userBridgeStatus) {
        System.out.print("[ ");
        String lowerResult = String.join(" | ", userBridgeStatus.lowerLadder());
        System.out.print(lowerResult);
        System.out.print(" ]");
    }

    public void printResult(final int tryCount, final boolean isSuccess) {
        System.out.println(NEW_LINE);
        if (isSuccess) {
            System.out.println(NEW_LINE + "게임 성공 여부: 성공");
        } else {
            System.out.println(NEW_LINE + "게임 성공 여부: 실패");
        }
        System.out.printf("총 시도한 횟수: %d", tryCount);
    }
}
