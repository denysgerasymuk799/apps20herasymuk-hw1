package ua.edu.ucu.tempseries;


import lombok.Getter;
import ua.edu.ucu.tempseries.comparators.ComparatorGreaterArrayItem;
import ua.edu.ucu.tempseries.comparators.ComparatorLessArrayItem;
import ua.edu.ucu.tempseries.comparators.MyComparator;

import java.util.InputMismatchException;

@Getter
public class TemperatureSeriesAnalysis {
    static final double MIN_TEMP = -273;

    private double[] temperatureArray;
    private double averageOfArray, deviationOfArray;
    private double minOfArray, maxOfArray;

    public TemperatureSeriesAnalysis() {
        temperatureArray = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < MIN_TEMP) {
                throw new InputMismatchException("Value less than -273C");
            }
        }
        temperatureArray = temperatureSeries;
    }

    public double average() {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        double sum = 0;
        for (double v : temperatureArray) {
            sum += v;
        }

        averageOfArray = sum / temperatureArray.length;
        return averageOfArray;
    }

    /**
      * formula was taken from here
     * https://www.mathsisfun.com/data/standard-deviation-formulas.html
     **/
    public double deviation() {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        averageOfArray = average();

        double sumForDeviation = 0;
        for (double v : temperatureArray) {
            sumForDeviation += (v - averageOfArray) * (v - averageOfArray);
        }

        sumForDeviation /= temperatureArray.length;
        deviationOfArray = Math.sqrt(sumForDeviation);
        return deviationOfArray;
    }

    /**
     * Additional function
     */
    public double findSpecialValue(double specialValue,
                                   MyComparator comparator) {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        double value = specialValue;
        for (double v : temperatureArray) {
            if (comparator.compare(value, v)) {
                value = v;
            }
        }
        return value;
    }

    public double min() {
        minOfArray = findSpecialValue(Double.POSITIVE_INFINITY,
                new ComparatorLessArrayItem());
        return minOfArray;
    }
    
    public double max() {
        maxOfArray = findSpecialValue(Double.NEGATIVE_INFINITY,
                new ComparatorGreaterArrayItem());
        return maxOfArray;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        double closestValueToValue = Double.POSITIVE_INFINITY;
        double closestLenToTempValue = Double.POSITIVE_INFINITY;
        for (double t : temperatureArray) {
            if (Math.abs(t - tempValue) < closestLenToTempValue) {
                closestValueToValue = t;
                closestLenToTempValue = Math.abs(t - tempValue);
            } else if (Math.abs(t - tempValue) == closestLenToTempValue) {
                if (t > closestValueToValue) {
                    closestValueToValue = t;
                    closestLenToTempValue = Math.abs(t - tempValue);
                }
            }
        }

        return closestValueToValue;
    }

    /**
     * I checked my idea and found how dynamical
     * arrays made in python here
     * https://medium.com/@yasufumy/data-structure-dynamic-array-3370cd7088ec
     */
    public double[] compareItemsWithValue(double tempValue,
                                          MyComparator comparator) {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        // make minimum array of two elements, such as in python
        double[] valuesArray = new double[2];
        int positionValuesArray = 0;
        for (double t : temperatureArray) {
            if (comparator.compare(tempValue, t)) {
                if (positionValuesArray == valuesArray.length) {
                    valuesArray = MyArray.changeArrayLen(valuesArray,
                            0, valuesArray.length * 2);
                }

                valuesArray[positionValuesArray++] = t;
            }
        }

        // make slice
        if (valuesArray.length > positionValuesArray) {
            valuesArray = MyArray.changeArrayLen(valuesArray,
                    0, positionValuesArray);
        }

        return valuesArray;
    }

    public double[] findTempsLessThen(double tempValue) {
        return compareItemsWithValue(tempValue,
                new ComparatorLessArrayItem());
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return compareItemsWithValue(tempValue,
                new ComparatorGreaterArrayItem());
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }
        
        return new TempSummaryStatistics(averageOfArray,
                deviationOfArray, minOfArray, maxOfArray);
    }

    public double addTemps(double... temps) {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        int positionValuesArray = temperatureArray.length;
        double sumTemperatures = 0;
        for (double temp : temps) {
            if (temp < MIN_TEMP) {
                continue;
            }

            if (positionValuesArray == temperatureArray.length) {
                temperatureArray = MyArray.changeArrayLen(temperatureArray, 0,
                        temperatureArray.length * 2);
            }

            temperatureArray[positionValuesArray++] = temp;
            sumTemperatures += temp;
        }
        
        return sumTemperatures;
    }
}
