package services.impl;

import exceptions.ArrayIsEmptyException;
import exceptions.IndexNotFoundException;
import exceptions.StringNotFoundException;
import services.StringList;

import java.util.Arrays;
import java.util.Objects;


public class StringListImpl implements StringList {


    private int size;
    private String[] strings;

    public StringListImpl() {
        strings = new String[size];
    }

    @Override
    public void print() {
        if (size != 0) {
            System.out.println("Печатаем массив");
            for (int i = 0; i < size; i++) {
                System.out.println(strings[i]);
            }
        } else {
            System.out.println("Пустой массив");
        }
    }

    @Override
    public void grow() {
        size = size + 1;
        strings = Arrays.copyOf(strings, size);
    }

    @Override
    public void reduction() {
        String[] copy = new String[size - 1];
        for (int i = 0, j = 0; i < size(); i++) {
            if (strings[i] != null) {
                copy[j++] = strings[i];
            }
        }
        size = size - 1;
        strings = Arrays.copyOf(copy, size);
    }

    @Override
    public String add(String item) {
        grow();
        strings[size - 1] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        String[] copy = Arrays.copyOf(strings, size);
        grow();
        for (int i = size - 1; i > index; i--) {
            strings[i] = copy[i - 1];
        }
        strings[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        strings[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (!contains(item)) {
            throw new StringNotFoundException();
        }
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                strings[i] = null;
                reduction();
                return item;
            }
        }
        return item;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        String str = strings[index];
        strings[index] = null;
        reduction();
        return str;
    }

    @Override
    public boolean contains(String item) {
        for (int i = size - 1; i > 0; i--) {
            if (strings[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i > 0; i--) {
            if (strings[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        return strings[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList.isEmpty()) {
            throw new ArrayIsEmptyException();
        }
        return Arrays.equals(strings, otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        strings = Arrays.copyOf(strings, 0);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(strings, size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringListImpl that = (StringListImpl) o;
        return size == that.size && Arrays.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(strings);
        return result;
    }
}
