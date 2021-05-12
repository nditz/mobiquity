package com.mobiquity.filereaders;

import com.mobiquity.exception.APIException;
import com.mobiquity.packages.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class IFileReaderTest {

    @Mock
    Path path;

    @InjectMocks
    TextFileReader textFileReader;

    private Stream.Builder<String> builder = Stream.builder();


    @BeforeAll
    public static void init() {
        Mockito.mockStatic(Files.class);
    }


    @Test
    public void testWhenFileProcessedByReaderListOfPackageIsReturned() throws IOException, APIException {
        builder.add("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)\n")
                .add("8 : (1,15.3,€34)\n")
                .add("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)\n")
                .add("56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");

        Stream<String> lines = builder.build();
        Mockito.when(Files.lines(path, StandardCharsets.UTF_8)).thenReturn(lines);

        List<Package> result = textFileReader.readFile(path);
        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void testWhenWrongFIleFormatIsPassedExceptionIsThrown() throws IOException {
        builder.add("81 | (1:53.38,€45) (2:88.62,€98) (3:78.48.€3) (4:72.30.€76) (5,30.18,€9) (6,46.34,€48)\n")
                .add("8 | (1,15.3,€34)\n");
        Stream<String> lines = builder.build();
        Mockito.when(Files.lines(path, StandardCharsets.UTF_8)).thenReturn(lines);

        Exception exception = assertThrows(NumberFormatException.class, () -> textFileReader.readFile(path));
        Assertions.assertTrue(exception.getMessage().contains("For input string:"));
    }
}