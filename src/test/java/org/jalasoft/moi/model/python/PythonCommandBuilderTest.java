/*
 *  Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package org.jalasoft.moi.model.python;

import org.jalasoft.moi.model.core.ICommandBuilder;
import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.utils.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PythonCommandBuilderTest {

    private static String directoryFile;
    private static String command;

    @BeforeAll
    static void initAll() {
        directoryFile = Constant.ROOTPATH.getValue() + "\\thirdparty\\python\\local\\";
        String space = " ";
        String compileall = "-m compileall";
        String python = Constant.ROOTPATH.getValue() + "\\thirdparty\\python\\win\\python32\\Portable_Python_3.2.5.1\\App\\python.exe";
        String concatenate = " && ";

        command = python + space + compileall + space + directoryFile + "test1.py" + concatenate + python + space + directoryFile + "test1.py";
    }

    /*
    @Test
    public void builderCommandCompileTest() {
        Params params = new Params();
        params.setFilesPath(Paths.get(directoryFile + "test1.py"));
        params.setLanguage(Language.PYTHON_32);
        ICommandBuilder pythonCommandBuilder = params.getLanguage().getCommandBuilder();

        String commandResult = pythonCommandBuilder.buildCommand(params.getFilesPath());

        assertEquals(command, commandResult);
    }

     */
}
