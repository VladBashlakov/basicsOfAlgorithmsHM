package test;

import services.impl.IntListImpl;
public class Test {
    public static void main(String[] args) {
        int[] ints0 = {1, 3, 4, 2, 8, 9, 5, 4, 123, 41, 2, 0, 6};

        int[] ints1 = {1, 3, 4, 2, 8, 9, 5, 4, 123, 41, 2, 0, 6};

        int[] ints2 = {1, 3, 4, 2, 8, 9, 5, 4, 123, 41, 2, 0, 6};

        long start0 = System.currentTimeMillis();
        long start1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();

        sortBubble(ints0);
        System.out.println(System.currentTimeMillis() - start0);
        sortInsertion(ints1);
        System.out.println(System.currentTimeMillis() - start1);
        sortSelection(ints2);
        System.out.println(System.currentTimeMillis() - start2);
        IntListImpl ints = new IntListImpl();
        ints.add(12745);
        ints.add(145);
        ints.add(112);
        ints.add(145);
        ints.add(166);
        ints.add(16346);
        ints.add(112412);
        System.out.println(ints.contains(1667));
        System.out.println(ints.contains(166));
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

}
