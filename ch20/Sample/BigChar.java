package ch20.Sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BigChar {
    // 문자의 이름 
    private char charname;
    // 큰 문자를 표현하는 문자열('#' '.' '\n'으로 이루어진 열)
    private String fontdata;
    
    // 외재 상태 출력을 원래대로 되돌리기 위한 리셋 코드
    private static final String RESET = "\u001B[0m";

    // 생성자 
    public BigChar(char charname) {
        this.charname = charname;
        try {
            String filename = "big" + charname + ".txt";
            StringBuilder sb = new StringBuilder();
            for (String line: Files.readAllLines(Path.of(filename))) {
                sb.append(line);
                sb.append("\n");
            }
            this.fontdata = sb.toString();
        } catch (IOException e) {
            this.fontdata = charname + "?";
        }
    }

    // 기존: 큰 문자를 표시한다
    public void print() {
        System.out.print(fontdata);
    }

    // [Step 1 추가] 색상 코드를 외재 상태로 받아 출력하는 오버로드 메서드
    public void print(String colorCode) {
        System.out.print(colorCode + fontdata + RESET);
    }
}