package com.epam.task1;

/**
 * @author sergey
 * created on 21.03.2017
 */
public interface List<E> {
    void addFirst(E element);

    void addLast(E element);

    void add(int index, E element);

    boolean add(E element);

    E getElement(int index);

    E getFirstElement();

    E getLastElement();

    boolean remove(E element);

    boolean contains(E element);

    int size();
}
