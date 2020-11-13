package ua.edu.ucu.tempseries;


import lombok.Getter;
import ua.edu.ucu.tempseries.comparators.ComparatorGreaterArrayItem;
import ua.edu.ucu.tempseries.comparators.ComparatorLessArrayItem;
import ua.edu.ucu.tempseries.comparators.MyComparator;

import java.util.InputMismatchException;

@Getter
public class TemperatureSeriesAnalysis {
    static final double MIN_TEMP = -273;
    static final double MIN_DOUBLE_DIFFERENCE = 0.0001;

    private double[] temperatureArray;
    private double averageOfArray = 0;
    private double deviationOfArray = 0;
    private double minOfArray = 0;
    private double maxOfArray = 0;

    public TemperatureSeriesAnalysis() {
        temperatureArray = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {

        for (double temperature : temperatureSeries) {
            if (temperature < MIN_TEMP) {
                throw new InputMismatchException("Value less than -273C");
            }
        }
        temperatureArray = new double[temperatureSeries.length];
        System.arraycopy(temperatureSeries, 0,
                temperatureArray, 0,
                temperatureSeries.length);
    }

    public double[] getTemperatureArray() {
        return temperatureArray;
    }

    /**
     *
     * Calculates the average temperature.
     * If the row is empty, generates an IllegalArgumentException
     */
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
     * Calculate the standard deviation.
     * If the row is empty, generates an IllegalArgumentException.
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

    /**
     *
     * Returns the minimum temperature.
     * If the row is empty, generates an IllegalArgumentException.
     */
    public double min() {
        minOfArray = findSpecialValue(Double.POSITIVE_INFINITY,
                new ComparatorLessArrayItem());
        return minOfArray;
    }

    /**
     *
     * Returns the maximum temperature.
     * If the row is empty, generates an IllegalArgumentException.
     */
    public double max() {
        maxOfArray = findSpecialValue(Double.NEGATIVE_INFINITY,
                new ComparatorGreaterArrayItem());
        return maxOfArray;
    }

    /**
     *
     * Returns the temperature value closest to 0
     */
    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    /**
     *
     * @param tempValue: double, value to compare
     * returns the value closest to the specified tempValue
     */
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
            } else if (Math.abs(Math.abs(t - tempValue) -
                    closestLenToTempValue) < MIN_DOUBLE_DIFFERENCE) {
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

    /**
     * Returns an array with temperature values
     * less than the specified tempValue
     */
    public double[] findTempsLessThen(double tempValue) {
        return compareItemsWithValue(tempValue,
                new ComparatorLessArrayItem());
    }

    /**
     * Returns an array with temperature values
     * greater than the specified tempValue
     */
    public double[] findTempsGreaterThen(double tempValue) {
        return compareItemsWithValue(tempValue,
                new ComparatorGreaterArrayItem());
    }

    /**
     *
     * Returns an immutable instance of the TempSummaryStatistics class
     * that contains information:
     * - double avgTemp;
     * - double devTemp;
     * - double minTemp;
     * - double maxTemp;
     */
    public TempSummaryStatistics summaryStatistics() {
        if (temperatureArray.length == 0) {
            throw new IllegalArgumentException("temperatureArray is empty");
        }

        if (averageOfArray == 0) {
            averageOfArray = average();
        }

        if (deviationOfArray == 0) {
            deviationOfArray = deviation();
        }

        if (minOfArray == 0) {
            minOfArray = min();
        }

        if (maxOfArray == 0) {
            maxOfArray = max();
        }

        return new TempSummaryStatistics(averageOfArray,
                deviationOfArray, minOfArray, maxOfArray);
    }

    /**
     *
     * Adds new temperature values to the end of
     * a series of existing data, and returns
     * the total number of temperature values.
     * The structure (array) in the class TemperatureSeriesAnalysis
     * for storage of already transferred temperatures should increase 2 times
     * if in it there is no place for storage of new values
     *
     * @return sum of tempts, that we add to our
     */
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
