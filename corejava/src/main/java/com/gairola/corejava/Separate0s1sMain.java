package com.gairola.corejava;

public class Separate0s1sMain {

    public static void main(String[] args) {
        int arr[] = { 0, 1, 0, 0, 1, 1, 1, 0, 1 };
        System.out.println("Original Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("n===========================");
        System.out.println("Solution 2");
        arr = separate0s1sSolution2(arr);
        System.out.println("nArray after separating 0's and 1's : ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int[] separate0s1sSolution2(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int left = 0;
            int right = arr.length - 1;
            while (arr[left] == 0) {
                System.out.println("Left" + left);
                left++;
            }
            while (arr[right] == 1) {
                right--;
                System.out.println("right" + right);
            }
            System.out.println(left + "WWWWWWWWWWWW-" + right);

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                System.out.println(arr[left] + "**********" + arr[right]);
                arr[right] = temp;
                System.out.println(arr[left] + "###########" + temp);
            }
            
        }
        return arr;
    }

}
