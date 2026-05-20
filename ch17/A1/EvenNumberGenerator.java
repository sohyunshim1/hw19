package ch17.A1;

public class EvenNumberGenerator extends NumberGenerator {
    private int start;
    private int end;
    private int number; // 현재 숫자 상태를 저장할 변수

    // 요구사항: 생성자 EvenNumberGenerator(int start, int end)
    public EvenNumberGenerator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int getNumber() {
        return number; // 관찰자들이 값을 요구할 때 현재 숫자를 반환
    }

    @Override
    public void execute() {
        // 요구사항: start부터 end 미만까지 짝수만 순서대로 생성 (증가폭 2로 고정)
        for (int i = start; i < end; i += 2) {
            number = i;          // 1. 상태(숫자) 변경
            notifyObservers();   // 2. 등록된 모든 관찰자에게 "숫자 바뀌었어!" 하고 통보
        }
    }
}