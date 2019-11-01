package com.example.demo.test;

final class deno2 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 1, 3, 5, 7, 9};
        int[] indexes = new int[arr.length];
        int count = findAllIndexes(arr, indexes, 1);
        for (int i = 0; i < count; i++) {
            System.out.println(indexes[i]);
        }
    }


    public static int findAllIndexes(int[] arr, int[] indexes, int find) {
        // 参数合法性判断
        if (null == arr || arr.length == 0 || null == indexes
                || indexes.length == 0 || indexes.length < arr.length) {
            System.out.println("Input parameter is invalid!");
            return -1;
        }


        int count = 0;

        // 2. 遍历数组，从指定的查询数组中，找出数据
        for (int i = 0; i < arr.length; i++) {
            // 3. if判断 如果find == arr[i] 需要保存i值
            if (find == arr[i]) {
                indexes[count] = i;
                count++;
            }
        }

        return count;
    }
}
