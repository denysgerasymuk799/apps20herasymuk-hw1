package ua.edu.ucu.tempseries;

public class MyArray {
    /**
     * analogy to append and slice functions in Python
     * Idea was taken from here https://medium.com/@yasufumy/data-structure-dynamic-array-3370cd7088ec
     */
    public static double[] changeArrayLength(double[] arr1, int start, int end) {
        double[] arr2 = new double[end - start];
        int nSteps;

        if (end > arr1.length)
            nSteps = arr1.length - start;
        else
            nSteps = end - start;

        if (nSteps >= 0) System.arraycopy(arr1, start, arr2, 0, nSteps);

        return arr2;
    }
}
