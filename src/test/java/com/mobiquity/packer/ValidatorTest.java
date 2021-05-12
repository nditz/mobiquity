package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ExtendWith(MockitoExtension.class)
public class ValidatorTest {

    private static String sampleFile = "sample.txt";

    @BeforeAll
    public static void init() throws IOException {
        Path paths = Paths.get(sampleFile);

        try {
            boolean exists = Files.exists(paths);
            System.out.println("exists = " + exists);
            Files.createFile(paths);
        } catch (FileAlreadyExistsException ex) {
            System.out.println("File already exists, proceeding");
        }
    }


    @Test
    public void testValidPath() throws APIException {
        Path valid = Validator.validateFile(Paths.get(sampleFile).toString());
        Assertions.assertNotNull(valid);
    }

}