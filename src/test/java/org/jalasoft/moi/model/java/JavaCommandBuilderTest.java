/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.java;

import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Parameters;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaCommandBuilderTest {

    /*
    @Test
    public void givenParamsWhenBuildCommandThenReceiveTheExpectedComand() {
        //given
        Parameters testParam = getParams(".\\temp\\java\\test");
        JavaCommandBuilder buildThisCommand = new JavaCommandBuilder();
        String expectedCommand = "cd .\\temp\\java\\test && javac *.java && java MainClass";
        //when
        String currentCommand = buildThisCommand.buildCommand(testParam.getFilesPath());
        //then
        assertEquals(expectedCommand, currentCommand);
    }

    private Parameters getParams(String paramTest) {
        Parameters params = new Params();
        params.setFilesPath(Paths.get(paramTest));
        params.setLanguage(Language.JAVA);
        return params;
    }

     */
}
