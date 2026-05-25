package ch17.A1;

public class Main {
    public static void main(String[] args) {
        // 1. EvenNumberGenerator(0, 30) 생성 (관찰 대상)
        NumberGenerator generator = new EvenNumberGenerator(0, 30);
        
        // 2. 관찰자들 생성
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        Observer observer3 = new RangeObserver(); // 새로 만든 관찰자
        
        // 3. 관찰 대상을 구독 (등록)
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.addObserver(observer3);
        
        // 4. 실행 (0, 2, 4 ... 28까지 짝수 생성 및 통보 시작)
        generator.execute();
    }
}
