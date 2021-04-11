package PROGRAMMERS._12917;

public class Solution {
    public String quickSort(String s) {
        char[] arr = s.toCharArray();
        sort(arr, 0, arr.length-1);
        return String.valueOf(arr);
    }

    public void sort(char[] arr, int low, int high) {
        if(low < high) {
            int mid = partition(arr, low, high);

            sort(arr, low, mid-1);
            sort(arr, mid, high);
        }
    }

    public int partition(char[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2];

        while(low <= high) {
            while(arr[low] > pivot) low++;
            while(arr[high] < pivot) high--;
            if(low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    public void swap(char[] arr, int a, int b) {
        char c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }

    public String solution(String s) {
        return quickSort(s);
    }
}
