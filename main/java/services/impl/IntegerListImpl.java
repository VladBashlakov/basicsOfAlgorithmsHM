package services.impl;

import exceptions.ArrayIsEmptyException;
import exceptions.IndexNotFoundException;
import exceptions.IntNotFoundException;
import exceptions.StringNotFoundException;
import services.IntegerList;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private int size = 0;
    private Integer[] ints;

    public IntegerListImpl() {
        ints = new Integer[size];
    }

    public static Integer[] sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }

    @Override
    public void grow() {
        size = size + 1;
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
        grow();
        ints[size - 1] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        grow();
        for (int i = size - 1; i > index; i--) {
            ints[i] = ints[i - 1];
        }
        ints[index] = item;
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
        if (contains(item)) {
            ints[indexOf(item)] = null;
            reduction();
            return item;
        }
        throw new IntNotFoundException();

    }

    @Override
    public Integer removeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException();
        }
        Integer number = ints[index];
        ints[index] = null;
        reduction();
        return number;
    }

    @Override
    public boolean contains(Integer element) {
        Integer[] sortedInts = Arrays.copyOf(sortInsertion(ints), size);
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
        return size == 0;
    }

    @Override
    public void clear() {
        ints = Arrays.copyOf(ints, 0);
        size = 0;
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
