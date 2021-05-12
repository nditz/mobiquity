/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author wandi
 */
 public class Validator{
        public static Path validateFile(String filePath) throws APIException {
        // convert the filepath to an instance of java.nio.file.Path, and check if actual path exists on the file system. (This will normalize and return an actual path) or throw IOexception if the path does not exist.
        Path realPath;
        try {
            realPath = Paths.get(filePath).toRealPath(LinkOption.NOFOLLOW_LINKS); // explicitly dont follow symbolic links
        } catch (IOException e) {
            throw new APIException("Cannot find an actual file from specified input path", e);
        }

        if (!Files.isReadable(realPath)) {
            throw new APIException("File attributes [read] restrict access to this file");
        }
        return realPath;
    }
    }
