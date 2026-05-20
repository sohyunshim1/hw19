package ch17.A1;

public class RangeObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        // 1. 관찰 대상(generator)으로부터 최신 숫자를 가져옴
        int value = generator.getNumber();
        String label = "";
        
        // 2. 범위 판별
        if (value >= 0 && value < 10) {
            label = "Low";
        } else if (value >= 10 && value < 20) {
            label = "Mid";
        } else if (value >= 20) {
            label = "High";
        }
        
        // 3. 요구된 형식에 맞춰 출력
        System.out.println("RangeObserver: [" + label + "] " + value);
        
    }
}