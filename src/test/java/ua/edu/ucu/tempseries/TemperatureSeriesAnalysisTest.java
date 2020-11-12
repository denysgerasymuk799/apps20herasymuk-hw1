package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    public TemperatureSeriesAnalysis seriesAnalysisOneElement1, seriesAnalysisOneElement2;
    public TemperatureSeriesAnalysis seriesAnalysisEmptyArr;
    public TemperatureSeriesAnalysis seriesAnalysisLittleSample,
            seriesAnalysisBigSample;
    public TemperatureSeriesAnalysis seriesAnalysisZeroSum;

    // assigning the values  before each test
    @Before
    public void init(){
        double[] temperatureSeries = {-1.0};
        seriesAnalysisOneElement1 = new TemperatureSeriesAnalysis(temperatureSeries);

        temperatureSeries = new double[]{0.0};
        seriesAnalysisOneElement2 = new TemperatureSeriesAnalysis(temperatureSeries);


        temperatureSeries = new double[]{};
        seriesAnalysisEmptyArr = new TemperatureSeriesAnalysis(temperatureSeries);


        temperatureSeries = new double[]{3.0, -5.0, 1.0, 5.0};
        seriesAnalysisLittleSample = new TemperatureSeriesAnalysis(temperatureSeries);

        temperatureSeries = new double[]{9, 2, 5, 4, 12, 7, 8, 11, 9, 3, 7, 4, 12, 5, 4, 10, 9, 6, 9, 4};
        seriesAnalysisBigSample = new TemperatureSeriesAnalysis(temperatureSeries);


        temperatureSeries = new double[]{3.0, -5.0, -3.0, 5.0};
        seriesAnalysisZeroSum = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructor() {
        // expect exception here
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0, -274};
        TemperatureSeriesAnalysis seriesAnalysisZeroSum = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    /** ========================= Tests for Average ========================= **/
    @Test
    public void testAverageWithOneElementArray() {
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysisOneElement1.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);


        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

//    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        // expect exception here
        seriesAnalysisEmptyArr.average();
    }

//    @Ignore
    @Test
    public void testAverageLittleSample() {
        double expResult = 1.0;

        double actualResult = seriesAnalysisLittleSample.average();
        
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testAverageBigSample() {
        double expResult = 7.0;

        double actualResult = seriesAnalysisBigSample.average();

        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testAverageZeroSum() {
        double expResult = 0.0;

        double actualResult = seriesAnalysisZeroSum.average();

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for Deviation ========================= **/
    @Test
    public void testDeviationWithOneElementArray() {
        double expResult = 0.0;

        double actualResult = seriesAnalysisOneElement1.deviation();
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        seriesAnalysisEmptyArr.deviation();
    }

    @Test
    public void testDeviationLittleSample() {
        double expResult = 3.741657;

        double actualResult = seriesAnalysisLittleSample.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviationBigSample() {
        double expResult = 2.9832;

        double actualResult = seriesAnalysisBigSample.deviation();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testDeviationZeroSum() {
        double expResult = 4.123105;

        double actualResult = seriesAnalysisZeroSum.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for Min ========================= **/
    @Test
    public void testMinWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisOneElement1.min();
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        seriesAnalysisEmptyArr.min();
    }

    @Test
    public void testMinLittleSample() {
        double expResult = -5.0;

        double actualResult = seriesAnalysisLittleSample.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinBigSample() {
        double expResult = 2.0;

        double actualResult = seriesAnalysisBigSample.min();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testMinZeroSum() {
        double expResult = -5.0;

        double actualResult = seriesAnalysisZeroSum.min();

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for Max ========================= **/
    @Test
    public void testMaxWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisOneElement1.max();
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        seriesAnalysisEmptyArr.max();
    }

    @Test
    public void testMaxLittleSample() {
        double expResult = 5.0;

        double actualResult = seriesAnalysisLittleSample.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxBigSample() {
        double expResult = 12.0;

        double actualResult = seriesAnalysisBigSample.max();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testMaxZeroSum() {
        double expResult = 5.0;

        double actualResult = seriesAnalysisZeroSum.max();

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for findTempClosestToZero ========================= **/
    @Test
    public void testTempClosestToZeroWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisOneElement1.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTempClosestToZeroWithEmptyArray() {
        seriesAnalysisEmptyArr.findTempClosestToZero();
    }

    @Test
    public void testTempClosestToZeroLittleSample() {
        double expResult = 1.0;

        double actualResult = seriesAnalysisLittleSample.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testTempClosestToZeroBigSample() {
        double expResult = 2.0;

        double actualResult = seriesAnalysisBigSample.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testTempClosestToZeroZeroSum() {
        double expResult = 3.0;

        double actualResult = seriesAnalysisZeroSum.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for findTempClosestToValue ========================= **/
    @Test
    public void testTempClosestToValueWithOneElementArray() {
        double expResult = -1.0;

        double actualResult = seriesAnalysisOneElement1.findTempClosestToValue(-2);
        assertEquals(expResult, actualResult, 0.00001);

        expResult = 0.0;

        actualResult = seriesAnalysisOneElement2.findTempClosestToValue(-2);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTempClosestToValueWithEmptyArray() {
        seriesAnalysisEmptyArr.findTempClosestToValue(-2);
    }

    @Test
    public void testTempClosestToValueLittleSample() {
        double expResult = 1.0;

        double actualResult = seriesAnalysisLittleSample.findTempClosestToValue(1.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testTempClosestToValueBigSample() {
        double expResult = 2.0;

        double actualResult = seriesAnalysisBigSample.findTempClosestToValue(-2);
        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testTempClosestToValueZeroSum() {
        double expResult = -5.0;

        double actualResult = seriesAnalysisZeroSum.findTempClosestToValue(-4.5);

        assertEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for findTempsLessThen ========================= **/
    @Test
    public void testTempsLessThenWithOneElementArray() {
        double[] expResult = {-1.0};

        double[] actualResult = seriesAnalysisOneElement1.findTempsLessThen(4);
        assertArrayEquals(expResult, actualResult, 0.00001);

        expResult = new double[]{0.0};

        actualResult = seriesAnalysisOneElement2.findTempsLessThen(4);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTempsLessThenWithEmptyArray() {
        seriesAnalysisEmptyArr.findTempsLessThen(4);
    }

    @Test
    public void testTempsLessThenLittleSample() {
        double[] expResult = {3.0, -5.0, 1.0};

        double[] actualResult = seriesAnalysisLittleSample.findTempsLessThen(4);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testTempsLessThenBigSample() {
        double[] expResult = {2.0, 3.0};

        double[] actualResult = seriesAnalysisBigSample.findTempsLessThen(4);

        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testTempsLessThenZeroSum() {
        double[] expResult = {-5.0};

        double[] actualResult = seriesAnalysisZeroSum.findTempsLessThen(-4.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }


    /** ========================= Tests for findTempsGreaterThen ========================= **/
    @Test
    public void testTempsGreaterThenWithOneElementArray() {
        double[] expResult = {};
        double[] actualResult = seriesAnalysisOneElement1.findTempsGreaterThen(4);

        assertArrayEquals(expResult, actualResult, 0.00001);

        expResult = new double[]{0.0};

        actualResult = seriesAnalysisOneElement2.findTempsGreaterThen(-1);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTempsGreaterThenWithEmptyArray() {
        seriesAnalysisEmptyArr.findTempsGreaterThen(4);
    }

    @Test
    public void testTempsGreaterThenLittleSample() {
        double[] expResult = {5.0};

        double[] actualResult = seriesAnalysisLittleSample.findTempsGreaterThen(4);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testTempsGreaterThenBigSample() {
        double[] expResult = {9.0, 5.0, 12.0, 7.0, 8.0, 11.0, 9.0, 7.0, 12.0, 5.0, 10.0, 9.0, 6.0, 9.0};

        double[] actualResult = seriesAnalysisBigSample.findTempsGreaterThen(4);

        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testTempsGreaterThenZeroSum() {
        double[] expResult = {3.0, -3.0, 5.0};

        double[] actualResult = seriesAnalysisZeroSum.findTempsGreaterThen(-4.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    /** ========================= Tests for summaryStatistics ========================= **/
    @Test
    public void testSummaryStatisticsWithOneElementArray() {
        double expAverage = -1.0;
        double expDeviation = 0.0;
        double expMin = -1.0;
        double expMax = -1.0;

        seriesAnalysisOneElement1.average();
        seriesAnalysisOneElement1.deviation();
        seriesAnalysisOneElement1.min();
        seriesAnalysisOneElement1.max();

        TempSummaryStatistics actualResult = seriesAnalysisOneElement1.summaryStatistics();

        assertArrayEquals(new double[]{expAverage, expDeviation, expMin, expMax},
                new double[]{actualResult.getAvgTemp(), actualResult.getDevTemp(),
                        actualResult.getMinTemp(), actualResult.getMaxTemp()}, 0.00001);


        /* =============== Second sample =============== */
        expAverage = 0.0;
        expDeviation = 0.0;
        expMin = 0.0;
        expMax = 0.0;

        seriesAnalysisOneElement2.average();
        seriesAnalysisOneElement2.deviation();
        seriesAnalysisOneElement2.min();
        seriesAnalysisOneElement2.max();

        actualResult = seriesAnalysisOneElement2.summaryStatistics();

        assertArrayEquals(new double[]{expAverage, expDeviation, expMin, expMax},
                new double[]{actualResult.getAvgTemp(), actualResult.getDevTemp(),
                        actualResult.getMinTemp(), actualResult.getMaxTemp()}, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        seriesAnalysisEmptyArr.summaryStatistics();
    }

    @Test
    public void testSummaryStatisticsLittleSample() {
        double expAverage = 1.0;
        double expDeviation = 3.74165;
        double expMin = -5.0;
        double expMax = 5.0;

        seriesAnalysisLittleSample.average();
        seriesAnalysisLittleSample.deviation();
        seriesAnalysisLittleSample.min();
        seriesAnalysisLittleSample.max();

        TempSummaryStatistics actualResult = seriesAnalysisLittleSample.summaryStatistics();

        assertArrayEquals(new double[]{expAverage, expDeviation, expMin, expMax},
                new double[]{actualResult.getAvgTemp(), actualResult.getDevTemp(),
                        actualResult.getMinTemp(), actualResult.getMaxTemp()}, 0.00001);
    }

    @Test
    public void testSummaryStatisticsBigSample() {
        double expAverage = 7.0;
        double expDeviation = 2.98328;
        double expMin = 2.0;
        double expMax = 12.0;

        seriesAnalysisBigSample.average();
        seriesAnalysisBigSample.deviation();
        seriesAnalysisBigSample.min();
        seriesAnalysisBigSample.max();

        TempSummaryStatistics actualResult = seriesAnalysisBigSample.summaryStatistics();

        assertArrayEquals(new double[]{expAverage, expDeviation, expMin, expMax},
                new double[]{actualResult.getAvgTemp(), actualResult.getDevTemp(),
                        actualResult.getMinTemp(), actualResult.getMaxTemp()}, 0.00001);
    }

    @Test
    public void testSummaryStatisticsZeroSum() {
        double expAverage = 0;
        double expDeviation = 4.12310;
        double expMin = -5.0;
        double expMax = 5.0;

        seriesAnalysisZeroSum.average();
        seriesAnalysisZeroSum.deviation();
        seriesAnalysisZeroSum.min();
        seriesAnalysisZeroSum.max();

        TempSummaryStatistics actualResult = seriesAnalysisZeroSum.summaryStatistics();

        assertArrayEquals(new double[]{expAverage, expDeviation, expMin, expMax},
                new double[]{actualResult.getAvgTemp(), actualResult.getDevTemp(),
                        actualResult.getMinTemp(), actualResult.getMaxTemp()}, 0.00001);
    }

    /** ========================= Tests for addTemps ========================= **/
    @Test
    public void testAddTempsWithOneElementArray() {
        double expResult = 0.8;
        double expLength = seriesAnalysisOneElement1.getTemperatureArray().length + 1;

        double actualResult = seriesAnalysisOneElement1.addTemps(0.8);

        assertEquals(expResult, actualResult, 0.0001);
        assertEquals(expLength, seriesAnalysisOneElement1.getTemperatureArray().length, 0.0001);


        expResult = 0.8;
        expLength = seriesAnalysisOneElement2.getTemperatureArray().length + 3;
        actualResult = seriesAnalysisOneElement2.addTemps(0.0, 0.0, 0.8);

        assertEquals(expResult, actualResult, 0.0001);
        assertEquals(expLength, seriesAnalysisOneElement2.getTemperatureArray().length, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTempsWithEmptyArray() {
        seriesAnalysisEmptyArr.addTemps(4);
    }

    @Test
    public void testAddTempsLittleSample() {
        double expResult = 0.8;

        int lengthTemperatureArray = seriesAnalysisLittleSample.getTemperatureArray().length;
        double expLength = (lengthTemperatureArray - (lengthTemperatureArray % 2)) * 2;

        double actualResult = seriesAnalysisLittleSample.addTemps(0.0, 0.0, 0.8);

        assertEquals(expResult, actualResult, 0.0001);
        assertEquals(expLength, seriesAnalysisLittleSample.getTemperatureArray().length, 0.0001);
    }

    @Test
    public void testAddTempsBigSample() {
        double expResult = 16.8;
        int lengthTemperatureArray = seriesAnalysisBigSample.getTemperatureArray().length;
        double expLength = (lengthTemperatureArray - (lengthTemperatureArray % 2)) * 2;

        double actualResult = seriesAnalysisBigSample.addTemps(15.0, -16.0, 17.8);

        assertEquals(expResult, actualResult, 0.0001);
        assertEquals(expLength, seriesAnalysisBigSample.getTemperatureArray().length, 0.0001);
    }

    @Test
    public void testAddTempsZeroSum() {
        double expResult = 0.0;
        double expLength = seriesAnalysisZeroSum.getTemperatureArray().length;

        double actualResult = seriesAnalysisZeroSum.addTemps(-274);

        assertEquals(expResult, actualResult, 0.00001);
        assertEquals(expLength, seriesAnalysisZeroSum.getTemperatureArray().length, 0.0001);
    }
}
