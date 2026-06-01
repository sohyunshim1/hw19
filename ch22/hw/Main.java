import command.*;
import drawer.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener, MouseMotionListener, WindowListener {
    private MacroCommand history = new MacroCommand();
    private DrawCanvas canvas = new DrawCanvas(400, 300, history);
    
    // 버튼들 선언
    private JButton clearButton = new JButton("Clear");
    private JButton redButton = new JButton("Red");
    private JButton greenButton = new JButton("Green");
    private JButton blueButton = new JButton("Blue");
    private JButton undoButton = new JButton("Undo");
    private JButton redoButton = new JButton("Redo");

    public Main(String title) {
        super(title);
        this.addWindowListener(this);
        canvas.addMouseMotionListener(this);
        
        // 리스너 연결
        clearButton.addActionListener(this);
        redButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
        undoButton.addActionListener(this);
        redoButton.addActionListener(this);

        // 버튼 패널 구성
        Box buttonBox = new Box(BoxLayout.X_AXIS);
        buttonBox.add(clearButton);
        buttonBox.add(redButton);
        buttonBox.add(greenButton);
        buttonBox.add(blueButton);
        buttonBox.add(undoButton);
        buttonBox.add(redoButton);
        
        // ✨ 한글 및 글씨 깨짐 방지를 위한 폰트 강제 적용 (과거의 경험 살림!)
        Font font = new Font("Malgun Gothic", Font.BOLD, 14);
        clearButton.setFont(font);
        redButton.setFont(font);
        greenButton.setFont(font);
        blueButton.setFont(font);
        undoButton.setFont(font);
        redoButton.setFont(font);

        Box mainBox = new Box(BoxLayout.Y_AXIS);
        mainBox.add(buttonBox);
        mainBox.add(canvas);
        getContentPane().add(mainBox);

        pack();
        setVisible(true);
    }

    // [Step 6] 버튼 이벤트 처리 로직
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            history.clear(); // commandsForRedo까지 싹 비워짐
            canvas.init();   // 캔버스 초기화 (초기 빨간색 세팅 포함)
            canvas.repaint();
        } else if (e.getSource() == redButton) {
            Command cmd = new ColorCommand(canvas, Color.red);
            history.append(cmd);
            cmd.execute();
        } else if (e.getSource() == greenButton) {
            Command cmd = new ColorCommand(canvas, Color.green);
            history.append(cmd);
            cmd.execute();
        } else if (e.getSource() == blueButton) {
            Command cmd = new ColorCommand(canvas, Color.blue);
            history.append(cmd);
            cmd.execute();
        } else if (e.getSource() == undoButton) {
            history.undo();   // Undo 호출
            canvas.repaint(); // 화면 갱신
        } else if (e.getSource() == redoButton) {
            history.redo();   // Redo 호출
            canvas.repaint(); // 화면 갱신
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        Command cmd = new DrawCommand(canvas, e.getPoint());
        history.append(cmd);
        cmd.execute();
    }

    // 윈도우 종료 처리 등
    @Override public void windowClosing(WindowEvent e) { System.exit(0); }
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowOpened(WindowEvent e) {}

    public static void main(String[] args) {
        new Main("Command Pattern Undo/Redo");
    }
}