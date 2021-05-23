public class mergeimp {

    private static void sort(int[] x, int start, int end){
        if(start < end){
            int middle = start + (end - start) / 2;
            sort(x, start, middle);
            sort(x, middle + 1, end);

            merge(x, start, end, middle);
        }
    }

    public static void merge(int[] x, int start, int end, int middle){
        int k;
        int[] a = new int[middle - start + 1];
        int[] b = new int[end - middle];

        for(int j = 0; j <= x.length/2; j++){
            a[j] = x[start + j];
        }
        for(int j = 0; j <= x.length/2; j++){
            b[j] = x[middle + 1 + j];
        }
        int i = 0;
        int j = 0;
        k = start;

        while (i < middle - start + 1 && j < end - middle)
        {
            if (a[i] <= b[j])
            {
                x[k] = a[i];
                i++;
            }
            else
            {
                x[k] = b[j];
                j++;
            }
            k++;
        }


        while (i < middle - start + 1)
        {
            x[k] = a[i];
            i++;
            k++;
        }

        while (j < end - middle)
        {
            x[k] = b[j];
            j++;
            k++;
        }
    }

    void printArray(int A[], int size)
    {
        for(int i = 0; i < size; i++)
            System.out.print("%d " + A[i]);


       System.out.println("\n");
    }

    public void main(String[] args) {
        int x[] = { 12, 11, 13, 5, 6, 7 };

        System.out.print("Given array is \n");
        printArray(x, x.length-1);

        sort(x, 0, x.length-1);

        System.out.print("\nSorted array is \n");
        printArray(x, x.length-1);
    }
    }




