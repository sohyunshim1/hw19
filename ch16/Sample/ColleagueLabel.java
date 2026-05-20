package ch16.Sample;

import java.awt.Color;
import java.awt.Label;
import java.awt.Font; // 폰트 기능 가져오기

public class ColleagueLabel extends Label implements Colleague {
    private Mediator mediator;

    //생성자 
    public ColleagueLabel(String text) {
        super(text);
        // 텍스트 추가
        // this.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
    }

    // 중재자 Mediator를 설정한다 
    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 중재자Mediator에서 지시하는 상태 활성/비활성에 따라 텍스트와 색상 변경
    @Override
    public void setColleagueEnabled(boolean enabled) {
        if (enabled) {
            this.setText(" ● login O ");
            this.setForeground(Color.blue);
        } else {
            this.setText(" ● login X");
            this.setForeground(Color.gray);
        }
    }
}