package ch19.A4;

public class DayState implements State {
    private static DayState singleton = new DayState();

    private DayState() {
    }

    public static State getInstance() {
        return singleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        // [수정] 17시부터 20시 미만까지는 ClosingState로 전환하고, 그 외 야간 시간대 처리를 분기함
        if (hour < 9 || 20 <= hour) {
            context.changeState(NightState.getInstance());
        } else if (17 <= hour && hour < 20) {
            context.changeState(ClosingState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.recordLog("금고 사용(주간)");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("비상벨(주간)");
        context.changeState(UrgentState.getInstance());
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("일반 통화(주간)");
    }

    @Override
    public String toString() {
        return "[주간]";
    }
}