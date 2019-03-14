package com.example.monitor;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lisuo
 * @Date: 2018/12/18:13:47
 */
public class TestTreeNode {
    public static void main(String[] args) {
        List<Tree> trees = new ArrayList<>();
        Tree node0 = new Tree(0, 0,null);
        Tree node1 = new Tree(0, 1,null);
        Tree node2 = new Tree(0, 2,null);
        Tree node3 = new Tree(0, 3,null);
        Tree node4 = new Tree(1, 4,null);
        Tree node5 = new Tree(1, 5,null);
        Tree node6 = new Tree(4, 6,null);
        Tree node7 = new Tree(4, 7,null);
        trees.add(node0);
        trees.add(node1);
        trees.add(node2);
        trees.add(node3);
        trees.add(node4);
        trees.add(node5);
        trees.add(node6);
        trees.add(node7);

        //trees.stream().fin

    }
}

class Tree{
    private int pid;
    private int id;
    private List<Tree> next;

    public Tree(int pid, int id, List<Tree> next) {
        this.pid = pid;
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<Tree> getNext() {
        return next;
    }

    public void setNext(List<Tree> next) {
        this.next = next;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
