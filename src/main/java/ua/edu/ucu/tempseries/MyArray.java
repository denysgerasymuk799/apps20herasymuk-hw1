package ua.edu.ucu.tempseries;

public class MyArray {
    /**
     * analogy to append and slice functions in Python
     * Idea was taken from here
     * https://medium.com/@yasufumy/data-structure-dynamic-array-3370cd7088ec
     */
    public static double[] changeArrayLen(double[] arr, int start, int end) {
        double[] newArr = new double[end - start];
        int nSteps;

        if (end > arr.length) {
            nSteps = arr.length - start;
        }
        else {
            nSteps = end - start;
        }

        if (nSteps >= 0) {
            System.arraycopy(arr, start, newArr, 0, nSteps);
        }

        return newArr;
    }
}
