package ch16.Sample;

import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame implements ActionListener, Mediator {
    private ColleagueCheckbox checkGuest;
    private ColleagueCheckbox checkLogin;
    private ColleagueTextField textUser;
    private ColleagueTextField textPass;
    private ColleagueButton buttonOk;
    private ColleagueButton buttonCancel;
    
    // 1. 상태를 표시할 라벨 필드 추가
    private ColleagueLabel statusLabel; 

    public LoginFrame(String title) {
        super(title);
        setBackground(Color.lightGray);
        
        // 2. 레이아웃을 4x2에서 5x2로 변경 (statusLabel이 들어갈 자리 만들기)
        setLayout(new GridLayout(5, 2)); 
        
        createColleagues();
        
        add(checkGuest);
        add(checkLogin);
        add(new Label("Username:"));
        add(textUser);
        add(new Label("Password:"));
        add(textPass);
        add(buttonOk);
        add(buttonCancel);
        
        // 3. 마지막 행에 빈 라벨과 상태 라벨 추가
        add(new Label("")); 
        add(statusLabel); 
        
        colleagueChanged();
        
        pack();
        setVisible(true);
    }

    @Override
    public void createColleagues() {
        CheckboxGroup g = new CheckboxGroup();
        checkGuest = new ColleagueCheckbox("Guest", g, true);
        checkLogin = new ColleagueCheckbox("Login", g, false);
        textUser = new ColleagueTextField("", 10);
        textPass = new ColleagueTextField("", 10);
        textPass.setEchoChar('*');
        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");
        
        // 4. statusLabel 객체 생성 및 중재자 등록
        statusLabel = new ColleagueLabel("● 로그인 불가"); 
        statusLabel.setMediator(this); 
        
        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPass.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);
        
        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);
        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    // 5. 컴포넌트 상태 변경 조율 
    @Override
    public void colleagueChanged() {
        if (checkGuest.getState()) { 
            // Guest 모드일 때
            textUser.setColleagueEnabled(false);
            textPass.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(true);
            statusLabel.setColleagueEnabled(true); // 파란색 "로그인 가능"
        } else { 
            // Login 모드일 때
            textUser.setColleagueEnabled(true);
            userpassChanged();
        }
    }

    // 사라졌던 메서드 복구 + statusLabel 로직 추가
    private void userpassChanged() {
        if (textUser.getText().length() > 0) {
            textPass.setColleagueEnabled(true);
            if (textUser.getText().length() >= 4 && textPass.getText().length() >= 4) {
                buttonOk.setColleagueEnabled(true);
                statusLabel.setColleagueEnabled(true); // 파란색 "로그인 가능"
            } else {
                buttonOk.setColleagueEnabled(false);
                statusLabel.setColleagueEnabled(false); // 회색 "로그인 불가"
            }
        } else {
            textPass.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(false);
            statusLabel.setColleagueEnabled(false); // 회색 "로그인 불가"
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }
}