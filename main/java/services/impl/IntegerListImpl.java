package services.impl;

import exceptions.ArrayIsEmptyException;
import exceptions.IndexNotFoundException;
import exceptions.IntNotFoundException;
import services.IntegerList;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private int size = 2;
    private Integer[] ints;
    private int elements = 0;

    public IntegerListImpl() {
        ints = new Integer[size];
    }

    private void mergeSort(Integer[] arr) {

        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;

        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

    @Override
    public void grow() {
        size = size * 2;
        ints = Arrays.copyOf(ints, size);
    }

    @Override
    public void reduction() {
        for (int i = 0; i < size - 1; i++) {
            if (ints[i] == null) {
                for (int j = i; j < size - 1; j++) {
                    ints[j] = ints[j + 1];
                }
            }
        }
        size = size - 1;
        ints = Arrays.copyOf(ints, size);
    }

    @Override
    public Integer add(Integer item) {
        if (size == elements) {
            grow();
        }
        for (int i = 0; i < size; i++) {
            if (ints[i] == null) {
                ints[i] = item;
                elements++;
                return item;
            }
        }
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        if (size == elements) {
            grow();
        }
        for (int i = size - 1; i > index; i--) {
            ints[i] = ints[i - 1];
        }
        ints[index] = item;
        elements++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        ints[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        for (int i = 0; i < elements; i++) {
            if (ints[i].equals(item)) {
                for (int j = i; j < elements; j++) {
                    ints[j] = ints[j + 1];
                }
                ints[elements] = null;
                elements--;
                return item;
            }
        }
        throw new IntNotFoundException();
    }

    @Override
    public Integer removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        Integer number = ints[index];
        for (int i = index; i < elements; i++) {
            ints[i] = ints[i + 1];
        }
        elements--;
        return number;
    }

    @Override
    public boolean contains(Integer element) {
        ints = Arrays.copyOf(ints, elements);
        mergeSort(ints);
        Integer[] sortedInts = Arrays.copyOf(ints, elements);
        int min = 0;
        int max = sortedInts.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element.equals(sortedInts[mid])) {
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
    public Integer indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (ints[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer lastIndexOf(Integer item) {
        for (int i = size - 1; i > 0; i--) {
            if (ints[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        return ints[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        return elements == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements; i++) {
            ints[i] = null;
        }
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(ints, size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerListImpl intList = (IntegerListImpl) o;
        return size == intList.size && Arrays.equals(ints, intList.ints);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(ints);
        return result;
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
}
