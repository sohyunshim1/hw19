package ch19.A4;

public class ClosingState implements State {
    private static ClosingState singleton = new ClosingState();

    // 싱글톤 패턴을 위한 private 생성자
    private ClosingState() {
    }

    // 유일한 인스턴스를 반환하는 메서드
    public static State getInstance() {
        return singleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        // 요구사항: hour < 17 이면 DayState로 전환, 20 <= hour 이면 NightState로 전환
        if (hour < 17) {
            context.changeState(DayState.getInstance());
        } else if (20 <= hour) {
            context.changeState(NightState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        // 요구사항: 경비 센터에 마감 금고 점검을 통보
        context.callSecurityCenter("마감 금고 점검");
    }

    @Override
    public void doAlarm(Context context) {
        // 요구사항: 비상벨 보고 후 UrgentState로 전환
        context.callSecurityCenter("비상벨(마감)");
        context.changeState(UrgentState.getInstance());
    }

    @Override
    public void doPhone(Context context) {
        // 요구사항: 퇴근 확인 통화 기록
        context.recordLog("퇴근 확인 통화(마감)");
    }

    @Override
    public String toString() {
        return "[마감]";
    }
}