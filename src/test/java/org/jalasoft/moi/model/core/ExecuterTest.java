/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.core;

import org.jalasoft.moi.controller.services.ProcessService;
import org.jalasoft.moi.model.core.parameters.Result;
import org.jalasoft.moi.model.exceptions.CommandBuildException;
import org.jalasoft.moi.model.exceptions.ProcessIDException;
import org.jalasoft.moi.model.exceptions.ResultException;
import org.jalasoft.moi.model.interaction.ProcessCacheTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExecuterTest {
    private static ProcessCacheTest processCache;

    @BeforeAll
    static void initAll() {
        processCache = new ProcessCacheTest();
    }

    /*@Test
    public void givenTestParamAndHandlerWhenExecuteParamThenReceiveTheExpectedOutput() throws CommandBuildException, ResultException, ProcessIDException {
        //given
        String expectedResult = "Microsoft Windows [Versión 10.0.18363.720]";
        Result currentResult;
        Executer testExecute = new Executer(processCache);
        //when
        currentResult = testExecute.execute("VER");
        //then
        assertEquals(expectedResult, currentResult.getValue());
    }

     */

    @Test
    public void throwsExceptionWhenCommandNullTest() {
        Executer executer = new Executer(new ProcessService());
        Exception exception = assertThrows(ProcessIDException.class, () -> {
            executer.execute(null);;
        });

        String expected = "Invalid process ID; id could not be captured.";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }
}
