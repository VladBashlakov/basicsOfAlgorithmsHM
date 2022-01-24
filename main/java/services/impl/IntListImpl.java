package services.impl;

import exceptions.ArrayIsEmptyException;
import exceptions.IndexNotFoundException;
import exceptions.IntNotFoundException;
import services.IntList;

import java.util.Arrays;
import java.util.Objects;

public class IntListImpl implements IntList {
    private int size;
    private int[] ints;

    public IntListImpl() {
        ints = new int[size];
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static int[] sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    @Override
    public void print() {
        if (size != 0) {
            System.out.println("Печатаем массив");
            for (int i = 0; i < size; i++) {
                System.out.println(ints[i]);
            }
        } else {
            System.out.println("Пустой массив");
        }
    }


    @Override
    public void grow() {
        size = size + 1;
        ints = Arrays.copyOf(ints, size);
    }

    @Override
    public void reduction() {
        int[] copy = new int[size - 1];
        for (int i = 0, j = 0; i < size(); i++) {
            if (ints[i] != 0) {
                copy[j++] = ints[i];
            }
        }
        size = size - 1;
        ints = Arrays.copyOf(copy, size);
    }

    @Override
    public int add(int item) {
        grow();
        ints[size - 1] = item;
        return item;
    }

    @Override
    public int add(int index, int item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        int[] copy = Arrays.copyOf(ints, size);
        grow();
        if (size - 1 - index >= 0) System.arraycopy(copy, index, ints, index + 1, size - 1 - index);
        ints[index] = item;
        return item;
    }

    @Override
    public int set(int index, int item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        ints[index] = item;
        return item;
    }

    @Override
    public int remove(int item) {
        if (!contains(item)) {
            throw new IntNotFoundException();
        }
        for (int i = 0; i < size; i++) {
            if (ints[i] == item) {
                ints[i] = 0;
                reduction();
                return item;
            }
        }
        return item;
    }

    @Override
    public int removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        int number = ints[index];
        ints[index] = 0;
        reduction();
        return number;
    }

    @Override
    public boolean contains(int element) {
        int[] sortedInts = Arrays.copyOf(sortBubble(ints), size);
        int min = 0;
        int max = sortedInts.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == sortedInts[mid]) {
                return true;
            }

            if (element < sortedInts[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }


    @Override
    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (ints[i] == (item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = size - 1; i > 0; i--) {
            if (ints[i] == (item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        return ints[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList.isEmpty()) {
            throw new ArrayIsEmptyException();
        }
        return Arrays.equals(ints, otherList.toArray());
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
        ints = Arrays.copyOf(ints, 0);
        size = 0;
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(ints, size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntListImpl intList = (IntListImpl) o;
        return size == intList.size && Arrays.equals(ints, intList.ints);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(ints);
        return result;
    }
}
