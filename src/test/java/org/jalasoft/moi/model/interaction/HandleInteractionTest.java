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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HandleInteractionTest {

    private static BuilderTest builder;
    private static ProcessCacheTest processCache;
    private static HashMap<Long, List<String>> map;

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
    public void executeProcessForTwoDigitsSumTest(Path path, Language language) throws ResultException, CommandBuildException, ProcessIDException {
        String expected = "Insert number1\r\n> ";

        Params params = new Params();
        params.setLanguage(language);
        params.setFilesPath(path);

        Result result = builder.createExecution(params);
        assertEquals(expected, result.getValue());

        List<String> numbers = new ArrayList<>();
        numbers.add("4");
        numbers.add("6");
        numbers.add("10");

        map.put(result.getPid(), numbers);
    }

     */

/*
    @ParameterizedTest
    @MethodSource("pidProvider")
    @Order(2)
    public void firstInsertDataToExecutedProcessTest(Long pid) throws InputParametersException, ResultException {
        String expected = "Insert number2\r\n> ";
        String number1 = map.get(pid).get(0);

        InputParameters input = new Answer();
        input.setProcessPid(pid);
        input.setValue(number1);

        Result result = builder.buildResultWithInput(input);
        assertEquals(expected, result.getValue());
    }


 */

/*
    @ParameterizedTest
    @MethodSource("pidProvider")
    @Order(3)
    public void secondInsertDataToExecutedProcessTest(Long pid) throws InputParametersException, ResultException {
        String number2 = map.get(pid).get(1);
        String sum = map.get(pid).get(2);
        String expected = "Sum: " + sum + "\r\n";

        InputParameters input = new Answer();
        input.setProcessPid(pid);
        input.setValue(number2);

        Result result = builder.buildResultWithInput(input);
        assertEquals(expected, result.getValue());
    }

    private static Stream<Arguments> codeProvider() {
        return Stream.of(
                arguments(
                        ".\\thirdparty\\python\\local\\SumInputsTest.py",
                        Language.PYTHON_32
                ),
                arguments(
                        ".\\thirdparty\\java\\local\\SumInputsTest.java",
                        Language.JAVA
                )
        );
    }

    static Stream<Long> pidProvider() {
        return processCache.getKeys().stream();
    }

 */
}
