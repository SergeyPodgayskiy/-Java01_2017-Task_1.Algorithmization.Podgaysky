package com.epam.task1.main;

import com.epam.task1.LinkedList;
import com.epam.task1.List;

/**
 * @author sergey
 *
 */
public class Main {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.addFirst("1");
        list.addLast("2");
        list.add(1,"new 2");
        System.out.println(list.getElement(1));
        list.remove("1");
    }
}
