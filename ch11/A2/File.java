package ch11.hw11;

import java.util.ArrayList;
import java.util.List;

public class File extends Entry {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }

    // ⭐ 추가: search 메소드 구현 (Leaf 노드)
    @Override
    public List<Entry> search(String keyword) {
        List<Entry> result = new ArrayList<>();
        if (this.name.contains(keyword)) {
            result.add(this); // 자신의 이름이 키워드를 포함하면 리스트에 추가
        }
        return result;
    }
}