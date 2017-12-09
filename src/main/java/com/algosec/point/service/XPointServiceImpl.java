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
        Integer initialStartPoint = Collections.min(pairs.stream().map(Pair::getStartPoint).collect(Collectors.toSet()));
        Integer initialEndPoint = Collections.max(pairs.stream().map(Pair::getEndPoint).collect(Collectors.toSet()));
        LOGGER.info("X plane range : Xs=" + initialStartPoint + " Xe=" + initialEndPoint.toString());

        int length = initialEndPoint - initialStartPoint + 1;

        LOGGER.info("Length = " + length);
        int[] matrixSumResult = new int[length];
        for (int row = 0; row < pairs.size(); row++) {
            int startIndex = pairs.get(row).getStartPoint() - initialStartPoint;
            int endIndex = pairs.get(row).getEndPoint() - initialStartPoint;
            for (int column = startIndex; column <= endIndex; column++) {
                matrixSumResult[column] += 1;
            }
        }

        // Find index in array with max number value
        int maxIndex = 0;
        for (int i = 1; i < length; i++) {
            if (matrixSumResult[i] > matrixSumResult[maxIndex]) {
                maxIndex = i;
            }
        }

        if (matrixSumResult[maxIndex] < 2) {
            throw new IllegalArgumentException("No intersection points");
        }

        Integer firstXPoint = maxIndex + initialStartPoint;
        LOGGER.info("First X Point = " + firstXPoint);
        return firstXPoint;
    }
}
