package com.algosec.point.service;

import com.algosec.point.model.Pair;

import java.util.List;

/**
 * Created by Alexander on 09.12.2017.
 */
public interface XPointService {
    Integer findFirstXPoint(List<Pair<Integer>> pairs);
}
