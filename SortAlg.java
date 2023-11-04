import java.util.Random;
import java.util.Scanner;
//import java.util.Arrays;

public class SortAlg {

    static int mergeComp = 0;
    static int mergeIteractionCount = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o algoritmo de ordenação: ");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Insertion Sort");
        System.out.println("3 - Merge Sort");
        System.out.println("4 - Shell Sort");
        int choice = scanner.nextInt();
        scanner.close();
        int[] sizes = {50, 500, 1000, 5000, 10000};

        for (int size : sizes) {
            int[] arr = new int[size];
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt(1000);
            }

            long startTime, endTime;
            int swapCount = 0;
            int iterationCount = 0;

            switch (choice) {
                case 1:
                    startTime = System.nanoTime();
                    int[] bubbleSortResult = bubbleSort(arr);
                    endTime = System.nanoTime();
                    swapCount = bubbleSortResult[0];
                    iterationCount = bubbleSortResult[1];
                    break;
                case 2:
                    startTime = System.nanoTime();
                    int[] insertionSortResult = insertionSort(arr);
                    endTime = System.nanoTime();
                    swapCount = insertionSortResult[0];
                    iterationCount = insertionSortResult[1];
                    break;
                case 3:
                    startTime = System.nanoTime();
                    mergeSort(arr, arr.length);
                    endTime = System.nanoTime();
                    break;
                case 4:
                    startTime = System.nanoTime();
                    int[] shellResult = shellSort(arr);
                    swapCount = shellResult[0];
                    iterationCount = shellResult[1];
                    endTime = System.nanoTime();
                    break;
                default:
                    System.out.println("Escolha inválida. Saindo.");
                    return;
            }

            long executionTime = endTime - startTime;

            System.out.println("Tamanho do array: " + size);
            System.out.println("Tempo de execução: " + executionTime + " ns");

            if(choice ==1 || choice == 2 || choice == 4){
            System.out.println("Numero de trocas: " + swapCount);
            System.out.println("Numero de iterações: " + iterationCount);
            }
            else{
            System.out.println("Numero de Comparações: " + mergeComp);
            System.out.println("Numero de iterações: " + mergeIteractionCount);
            }
            System.out.println();

            //Para printar o Array ordenado a fim de verificações
            //mude a condição para o tamanho de array desejado para verificá-lo
            //if(arr.length == 1000){ 
            //   System.out.println(Arrays.toString(arr));
            //}
        }
    }

    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int swapCount = 0;
        int iterationCount = 0;

        for (int i = 0; i < n - 1; i++) {
            iterationCount++;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
        }
        int[] result = {swapCount, iterationCount};
        return result;
    }

    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        int swapCount = 0;
        int iterationCount = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            iterationCount++;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                swapCount++;
            }

            arr[j + 1] = key;
        }
        int[] result = {swapCount, iterationCount};
        return result;
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        
        merge(a, l, r, mid, n - mid);

        mergeIteractionCount++;
    }
    
    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left && j < right) {
            mergeComp++;
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
    
    public static int[] shellSort(int[] arr) {
        int incr, j, k, span, y;
        int iterationCount = 0;
        int swapCount = 0;
        
        
        int[] incrementos = {5, 3, 1};
        
        for (incr = 0; incr < incrementos.length; incr++) {
            span = incrementos[incr];
            
            for (j = span; j < arr.length; j++) {
                y = arr[j];
                
                for (k = j - span; k >= 0 && y < arr[k]; k -= span) {
                    arr[k + span] = arr[k];
                    swapCount++;
                }
                
                arr[k + span] = y;
                iterationCount++;
            }
        }
        int[] result = {swapCount, iterationCount};
        return result;
    }
}