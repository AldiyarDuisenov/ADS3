public class quickimp {

    static void swap(int[] x, int i, int j)
    {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
    private void quickSort(int[] x, int start, int end){
        if(start < end){
            int pi = partition(x, start, end);
            quickSort(x, start, pi - 1);
            quickSort(x, pi + 1, end);
        }
    }
    private int partition(int[] x, int start, int end){
        int pivot = x[end];
        int i = (start - 1);
        for(int j = start; j <= end - 1; j++){
            if(x[j] < pivot){
                i++;
                swap(x, i, j);
            }
        }
        swap(x, i + 1, end);
        return (i + 1);
    }
    static void printArray(int[] x, int size) {
        for(int i = 0; i < size; i++)
            System.out.print(x[i] + " ");

        System.out.println();
    }
    public void main(String[] args)
    {
        int[] x = { 10, 7, 8, 9, 1, 5 };
        int n = x.length;

        quickSort(x, 0, n - 1);
        System.out.println("Sorted array: ");
        printArray(x, n);
    }
    }



