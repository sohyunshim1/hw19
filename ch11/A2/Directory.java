package ch11.A2;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry {
    private String name;
    private List<Entry> directory = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry entry: directory) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        for (Entry entry: directory) {
            entry.printList(prefix + "/" + name);
        }
    }

    public Entry add(Entry entry) {
        directory.add(entry);
        entry.setParent(this);
        return this;
    }

    // ⭐ 추가: search 메소드 구현 (Composite 노드)
    @Override
    public List<Entry> search(String keyword) {
        List<Entry> result = new ArrayList<>();
        
        // 1. 자기 자신의 이름 검사
        if (this.name.contains(keyword)) {
            result.add(this);
        }
        
        // 2. 자식 요소들 재귀 탐색 및 결과 병합
        for (Entry entry : directory) {
            result.addAll(entry.search(keyword)); 
        }
        
        return result;
    }
}