package command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class MacroCommand implements Command {
    // 실행된 명령을 차곡차곡 쌓아두는 서랍
    private Deque<Command> commands = new ArrayDeque<>();
    
    // [Step 1] Undo 시 삭제된 명령을 임시 보관하는 Redo용 서랍
    private Deque<Command> commandsForRedo = new ArrayDeque<>();

    // 실행
    @Override
    public void execute() {
        // [Step 4] 스택 구조상 나중에 들어온 것이 머리(top)에 있으므로,
        // descendingIterator를 사용해 꼬리(가장 먼저 추가된 옛날 명령)부터 순서대로 재현합니다.
        Iterator<Command> it = commands.descendingIterator();
        while (it.hasNext()) {
            it.next().execute();
        }
    }

    // 추가
    public void append(Command cmd) {
        if (cmd == this) {
            throw new IllegalArgumentException("자기 자신을 추가할 수 없습니다.");
        }
        commands.push(cmd);
        // 새로운 그림을 그리거나 색상을 바꾸면, 기존에 임시 보관했던 Redo 내역은 날아갑니다.
        commandsForRedo.clear();
    }

    // [Step 2] 마지막 명령 취소 (Undo)
    public void undo() {
        if (!commands.isEmpty()) {
            Command cmd = commands.pop(); // 1. 최근 명령을 빼서
            commandsForRedo.push(cmd);    // 2. Redo 서랍에 임시 보관!
        }
    }

    // [Step 3] 취소한 명령 다시 실행 (Redo)
    public void redo() {
        if (!commandsForRedo.isEmpty()) {
            Command cmd = commandsForRedo.pop(); // 1. 임시 보관된 최신 명령을 빼서
            commands.push(cmd);                  // 2. 다시 실행된 서랍으로 복구!
        }
    }

    // 전부 삭제
    public void clear() {
        commands.clear();
        // [Step 5] Clear 시 commandsForRedo(임시 보관소)도 함께 싹 비워줍니다.
        commandsForRedo.clear();
    }
}