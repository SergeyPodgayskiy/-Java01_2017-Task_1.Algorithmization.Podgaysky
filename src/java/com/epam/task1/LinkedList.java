package com.epam.task1;

import java.util.NoSuchElementException;

/**
 * @author sergey
 * created on 21.03.2017
 */
public class LinkedList<E> implements List<E> {
    private ListElement<E> firstElement;
    private ListElement<E> lastElement;
    private int size = 0;

    public LinkedList() {
    }

    @Override
    public void addFirst(E element) {
        linkFirst(element);
    }

    @Override
    public void addLast(E element) {
        linkLast(element);
    }

    @Override
    public boolean add(E element) {
        linkLast(element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkElementIndex(index);
        if (index == size) {
            linkLast(element);
        } else if (index == 0) {
            linkFirst(element);
        } else {
            linkBefore(element, listElement(index));
        }
    }

    @Override
    public E getElement(int index) {
        checkElementIndex(index);
        return listElement(index).value;
    }

    @Override
    public E getFirstElement() {
        ListElement<E> thisElement = firstElement.nextElement;
        if (thisElement == null) {
            throw new NoSuchElementException();
        }
        return firstElement.value;
    }

    @Override
    public E getLastElement() {
        ListElement<E> thisElement = lastElement.prevElement;
        if (thisElement == null) {
            throw new NoSuchElementException();
        }
        return lastElement.value;
    }

    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (ListElement<E> thisElement = firstElement;
                 thisElement != null; thisElement = thisElement.nextElement) {
                if (thisElement.value == null) {
                    unlinkElement(thisElement);
                    return true;
                }
            }
        } else {
            for (ListElement<E> thisElement = firstElement;
                 thisElement != null; thisElement = thisElement.nextElement) {
                if (element.equals(thisElement.value)) {
                    unlinkElement(thisElement);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (getElement(i).equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void unlinkElement(ListElement<E> element) {
        ListElement<E> next = element.nextElement;
        ListElement<E> prev = element.prevElement;

        if (prev == null) {
            firstElement = next;
        } else {
            prev.nextElement = next;
            element.prevElement = null;
        }
        if (next == null) {
            lastElement = prev;
        } else {
            next.prevElement = prev;
            element.nextElement = null;
        }
        element.value = null;
        size--;
    }

    private void linkLast(E element) {
        ListElement<E> last = lastElement; //prev
        ListElement<E> newElement = new ListElement<E>(element, null, last);
        lastElement = newElement;
        if (last == null) {
            firstElement = newElement;
        } else {
            last.nextElement = newElement;
        }
        size++;
    }

    private void linkFirst(E element) {
        ListElement<E> first = firstElement; // next
        ListElement<E> newElement = new ListElement<E>(element, first, null);
        firstElement = newElement;
        if (first == null) {
            lastElement = newElement;
        } else {
            first.prevElement = newElement;
        }
        size++;
    }

    private void linkBefore(E element, ListElement<E> listElement) {
        ListElement<E> prevElement = listElement.prevElement;
        ListElement<E> newListElement = new ListElement<E>(element, listElement, prevElement);
        listElement.prevElement = newListElement;
        if (prevElement == null) {
            firstElement = newListElement;
        } else {
            prevElement.nextElement = newListElement;
        }
        size++;
    }

    private ListElement<E> listElement(int index) {
        ListElement<E> element = firstElement;
        for (int i = 0; i < index; i++) {
            element = element.nextElement;
        }
        return element;
    }

    private class ListElement<E> {
        private ListElement<E> nextElement;
        private ListElement<E> prevElement;
        private E value;

        public ListElement(E value, ListElement<E> nextElement, ListElement<E> prevElement) {
            this.value = value;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }
    }
}
