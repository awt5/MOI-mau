package org.jalasoft.moi.model.interaction;

import org.jalasoft.moi.model.core.Language;
import org.jalasoft.moi.model.core.parameters.Answer;
import org.jalasoft.moi.model.core.parameters.InputParameters;
import org.jalasoft.moi.model.core.parameters.Params;
import org.jalasoft.moi.model.core.parameters.Result;
import org.jalasoft.moi.model.exceptions.CommandBuildException;
import org.jalasoft.moi.model.exceptions.InputParametersException;
import org.jalasoft.moi.model.exceptions.ProcessIDException;
import org.jalasoft.moi.model.exceptions.ResultException;
import org.jalasoft.moi.model.utils.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SingleInputTest {

    private static ProcessCacheTest processCache;
    private static BuilderTest builder;
    private static HashMap<Long, String> map;

    @BeforeAll
    static void initAll() {
        processCache = new ProcessCacheTest();
        builder = new BuilderTest(processCache);
        map = new HashMap<>();
    }

    /*
    @ParameterizedTest
    @MethodSource("codeProvider")
    @Order(1)
    public void askForNumberTest(Path path, Language language) throws ResultException, CommandBuildException, ProcessIDException {
        String expected = "Insert number\r\n> ";

        Params params = new Params();
        params.setLanguage(language);
        params.setFilesPath(path);

        Result result = builder.createExecution(params);
        assertEquals(expected, result.getValue());

        map.put(result.getPid(), "100");
    }

     */

    /*
    @ParameterizedTest
    @MethodSource("pidProvider")
    @Order(2)
    public void displayNumberInsertedTest(Long pid) throws InputParametersException, ResultException {
        String userInput = map.get(pid);
        String expected = "your number is: " + userInput + "\r\n";

        InputParameters input = new Answer();
        input.setProcessPid(pid);
        input.setValue(userInput);

        Result result = builder.buildResultWithInput(input);
        assertEquals(expected, result.getValue());
    }

    static Stream<Arguments> codeProvider() {
        return Stream.of(
                arguments(
                        Constant.ROOTPATH.getValue() + "\\thirdparty\\python\\local\\AskInputTest.py",
                        Language.PYTHON_32
                )
        );
    }

    static Stream<Long> pidProvider() {
        return processCache.getKeys().stream();
    }

     */
}
