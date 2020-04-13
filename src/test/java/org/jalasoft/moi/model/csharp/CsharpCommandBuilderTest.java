/*
 *   Copyright (c) 2020 Jalasoft.
 *
 *   This software is the confidential and proprietary information of Jalasoft.
 *   ("Confidential Information"). You shall not disclose such Confidential
 *   Information and shall use it only in accordance with the terms of the
 *   license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.csharp;

import org.jalasoft.moi.model.core.ICommandBuilder;
import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Parameters;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CsharpCommandBuilderTest {

    /*
    @Test
    public void cSharpCmdBuilderTest() {
        //given
        Parameters codeParams = getParams(".\\temp\\cplusplus\\test");
        ICommandBuilder cSharpCommBuilder = new CsharpCommandBuilder();
        String expectedCommand = "cd .\\temp\\cplusplus\\test && " +
                "C:/Windows/Microsoft.NET/Framework64/v4.0.30319/csc.exe -optimize -out:Output.exe *.cs && Output";
        //when
        String currentCommand = cSharpCommBuilder.buildCommand(codeParams.getFilesPath());
        //then
        assertEquals(expectedCommand, currentCommand);
    }

     */

    private Parameters getParams(String paramTest) {
        Parameters params = new Params();
        params.setFilesPath(Paths.get(paramTest));
        params.setLanguage(Language.CSHARP);
        return params;
    }
}
