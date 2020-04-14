/*
 *   Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.cpp;

import org.jalasoft.moi.model.core.ICommandBuilder;
import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Parameters;
import org.jalasoft.moi.model.cplusplus.CppCommandBuilder;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CppCommandBuilderTest {
/*
    @Test
    public void cppCommandBuilderTest() {
        //given
        Parameters params = getParams(".\\temp\\cplusplus\\test\\test.cpp");
        ICommandBuilder cppCommandBuilder = new CppCommandBuilder();
        String expectedCommand = "c++ .\\temp\\cplusplus\\test\\*.cpp -o .\\temp\\cplusplus\\test\\test.exe && " +
                ".\\temp\\cplusplus\\test\\test.exe";
        //when
        String currentCommand = cppCommandBuilder.buildCommand(params.getFilesPath());
        //then
        assertEquals(expectedCommand, currentCommand);
    }

 */

    private Parameters getParams(String paramTest) {
        Parameters params = new Params();
        params.setFilesPath(Paths.get(paramTest));
        params.setLanguage(Language.CPP);
        return params;
    }
}
