package com.algosec.point.service;

import com.algosec.point.model.Pair;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Alexander on 10.12.2017.
 */
public class XPointServiceImplTest {
    private XPointService xPointService = new XPointServiceImpl();

    @Test
    public void findFirstXPointTest() throws Exception {

        //[ { "startPoint": -12, "endPoint": 5 }, { "startPoint": 2, "endPoint": 33 }, { "startPoint": 6, "endPoint": 15 }, { "startPoint": 3, "endPoint": 5 } ]
        assertEquals(new Integer(3), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(-12, 5),
                new Pair<Integer>(2, 33),
                new Pair<Integer>(6, 15),
                new Pair<Integer>(3, 5)
        )));

        //[ { "startPoint": -12, "endPoint": 5 }, { "startPoint": 5, "endPoint": 33 } ]
        assertEquals(new Integer(5), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(-12, 5),
                new Pair<Integer>(5, 33)
        )));

        //[ { "startPoint": -12, "endPoint": 6 }, { "startPoint": 5, "endPoint": 33 } ]
        assertEquals(new Integer(5), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(-12, 6),
                new Pair<Integer>(5, 33)
        )));

        //[ { "startPoint": -12, "endPoint": -6 }, { "startPoint": -6, "endPoint": -1 } ]
        assertEquals(new Integer(-6), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(-12, -6),
                new Pair<Integer>(-6, -1)
        )));

        //[ { "startPoint": -6, "endPoint": -1 }, { "startPoint": -12, "endPoint": -6 } ]
        assertEquals(new Integer(-6), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(-6, -1),
                new Pair<Integer>(-12, -6)
        )));

        try {
            xPointService.findFirstXPoint(Arrays.asList(
                    new Pair<Integer>(5, 10)));
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }

        try {
            xPointService.findFirstXPoint(Arrays.asList(
                    new Pair<Integer>(5, 10),
                    new Pair<Integer>(35, 100),
                    new Pair<Integer>(25, 30)
            ));
            fail();
        } catch (IllegalArgumentException ex) {
            // expected
        }

        //[ { "startPoint": 5, "endPoint": -12 }, { "startPoint": 2, "endPoint": 33 }, { "startPoint": 15, "endPoint": 6 }, { "startPoint": 3, "endPoint": 5 } ]
        assertEquals(new Integer(3), xPointService.findFirstXPoint(Arrays.asList(
                new Pair<Integer>(5, -12),
                new Pair<Integer>(2, 33),
                new Pair<Integer>(15, 6),
                new Pair<Integer>(3, 5)
        )));
    }
}