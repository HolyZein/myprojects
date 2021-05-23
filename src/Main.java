public class Main {

    public static void main(String[] args) {

        Sort<Integer> sort = new Sort();

        Integer[] a = {4, 3, 5, -100, 35, 3, 20000, 34};

        sort.QuickSort(a, 0, a.length - 1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }
}
