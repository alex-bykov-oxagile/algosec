package com.algosec.point.service;

import com.algosec.point.model.Pair;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander on 09.12.2017.
 */
@Service
public class XPointServiceImpl implements XPointService {
    private static final Logger LOGGER = LogManager.getLogger(XPointServiceImpl.class);

    @Override
    public Integer findFirstXPoint(List<Pair<Integer>> pairs) {
        alignSegments(pairs);

        Integer initialStartPoint = getInitialStartPoint(pairs);
        Integer initialEndPoint = getInitialEndPoint(pairs);
        LOGGER.info("X plane range : Xs=" + initialStartPoint + " Xe=" + initialEndPoint.toString());

        int length = initialEndPoint - initialStartPoint + 1;

        LOGGER.info("Length = " + length);
        int[] matrixSumResult = getMatrixSumResult(pairs, initialStartPoint, length);

        // Find index in array with max number value
        int maxIndex = getArrayIndexWithMaxValue(length, matrixSumResult);

        if (matrixSumResult[maxIndex] < 2) {
            throw new IllegalArgumentException("No intersection points");
        }

        Integer firstXPoint = maxIndex + initialStartPoint;
        LOGGER.info("First X Point = " + firstXPoint);
        return firstXPoint;
    }

    private int getArrayIndexWithMaxValue(int length, int[] matrixSumResult) {
        int maxIndex = 0;
        for (int i = 1; i < length; i++) {
            if (matrixSumResult[i] > matrixSumResult[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int[] getMatrixSumResult(List<Pair<Integer>> pairs, Integer initialStartPoint, int length) {
        int[] matrixSumResult = new int[length];
        for (int row = 0; row < pairs.size(); row++) {
            int startIndex = pairs.get(row).getStartPoint() - initialStartPoint;
            int endIndex = pairs.get(row).getEndPoint() - initialStartPoint;
            for (int column = startIndex; column <= endIndex; column++) {
                matrixSumResult[column] += 1;
            }
        }

        return matrixSumResult;
    }

    private Integer getInitialEndPoint(List<Pair<Integer>> pairs) {
        return Collections.max(pairs.stream().map(Pair::getEndPoint).collect(Collectors.toSet()));
    }

    private Integer getInitialStartPoint(List<Pair<Integer>> pairs) {
        return Collections.min(pairs.stream().map(Pair::getStartPoint).collect(Collectors.toSet()));
    }

    private void alignSegments(List<Pair<Integer>> pairs) {
        for (int row = 0; row < pairs.size(); row++) {
            Integer startPoint = pairs.get(row).getStartPoint();
            Integer endPoint = pairs.get(row).getEndPoint();
            if (startPoint > endPoint) {
                pairs.set(row, new Pair<>(endPoint, startPoint));
            }
        }
    }
}
