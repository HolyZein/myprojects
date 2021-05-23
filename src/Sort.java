import java.util.ArrayList;
import java.util.Scanner;

class Sort<T extends Comparable<T>> {

    public void QuickSort(T a[], int l, int r){
        if(l < r) {
            int pivot = partition(a, l, r);

            QuickSort(a, l, pivot - 1);
            QuickSort(a, pivot + 1, r);
        }
    }

    int partition(T a[], int l, int r){
        int pivot = r;

        int half = l - 1;

        for(int i = l; i < r; i++){
            if(a[i].compareTo(a[pivot]) == -1){
                half++;
                T temp = a[half];
                a[half] = a[i];
                a[i] = temp;
            }
        }
        half++;
        T temp = a[half];
        a[half] = a[pivot];
        a[pivot] = temp;

        return half;

    }

}

