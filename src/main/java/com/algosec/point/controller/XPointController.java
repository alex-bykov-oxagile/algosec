package com.algosec.point.controller;

import com.algosec.point.model.Pair;
import com.algosec.point.service.XPointService;
import io.swagger.annotations.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alexander on 09.12.2017.
 */

@RestController
@RequestMapping(value = "/api")
@Api(value = "/api", description = "REST XPoint Service")
public class XPointController {
    private static final Logger LOGGER = LogManager.getLogger(XPointController.class);

    private final String jsonExample = "[\n" +
            " {\n" +
            "  \"startPoint\": -12,\n" +
            "  \"endPoint\": 5\n" +
            " },\n" +
            " {\n" +
            "  \"startPoint\": 2,\n" +
            "  \"endPoint\": 33\n" +
            " },\n" +
            " {\n" +
            "  \"startPoint\": 6,\n" +
            "  \"endPoint\": 15\n" +
            " },\n" +
            " {\n" +
            "  \"startPoint\": 3,\n" +
            "  \"endPoint\": 5\n" +
            " }\n" +
            "]";

    @Autowired
    private XPointService xPointService;

    @ApiOperation(value = " find the first X point", response = Integer.class)
    @RequestMapping(method = RequestMethod.POST, path = "/find/xpoint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Integer.class)
    })
    public Integer processXPointPairs(HttpServletResponse response, @RequestBody @ApiParam(value = "Define Xs and Xe points like a pair. For jsonExample:\n\n" + jsonExample) List<Pair<Integer>> pairs) throws IOException {
        LOGGER.info("Incoming pairs: " + pairs);

        if (!validatePairs(pairs)) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Please, check JSON in request body. Fields are incorrect.");
            return null;
        }

        return xPointService.findFirstXPoint(pairs);
    }

    /**
     * Validate pairs for null values.
     * JSON might contain incorrect field names - null values will appear as result
     * Jackson might be configured to sent "Bad request" response, when unrecognized JSON fields where found.
     *
     * @param pairs
     * @return true if Pairs are valid, false otherwise
     */
    private boolean validatePairs(List<Pair<Integer>> pairs) {
        return pairs.stream().noneMatch(p -> p.getEndPoint() == null || p.getStartPoint() == null);
    }
}
