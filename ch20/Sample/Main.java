package ch20.Sample;

public class Main {
    // 실제 터미널 색상 출력을 위한 정확한 ANSI 코드 정의
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main digits");
            System.out.println("Example: java Main 1212123");
            System.exit(0);
        }

        String input = args[0];
        BigString bs = new BigString(input);

        // 테스트 요구사항(1212123)에 맞게 7가지 색상 배열 구성
        String[] colorPool = { RED, BLUE, GREEN, MAGENTA, CYAN, YELLOW, BLUE };
        String[] colors = new String[input.length()];
        
        for (int i = 0; i < input.length(); i++) {
            colors[i] = colorPool[i % colorPool.length]; // 길이에 맞춰 순환
        }

        // [Step 3 추가] 색상 배열을 넘겨주며 출력!
        bs.print(colors);
    }
}