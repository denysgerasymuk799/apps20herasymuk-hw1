package ua.edu.ucu.tempseries.comparators;

public class ComparatorGreaterArrayItem implements MyComparator {
    public boolean compare(double tempValue, double arrayItem)
    {
        return tempValue < arrayItem;
    }
}