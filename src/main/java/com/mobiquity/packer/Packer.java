package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.filereaders.IFileReader;
import com.mobiquity.filereaders.Registry;
import com.mobiquity.packages.Package;
import com.mobiquity.processors.IProcess;
import com.mobiquity.processors.Knapsack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.mobiquity.enums.FileType;
public class Packer {

    static {
        IFileReader.initializeRegistry();
    }

    private Packer() {
    }

    //Default filetype will be txt.
    public static String pack(String filePath) throws APIException {

        Path theFilePath = validateFile(filePath);
        List<Package> strategyResult = Packer.executeStrategy(theFilePath, FileType.DEFAULT);
        return runAlgorithm(strategyResult);
    }

    public static String pack(String filePath, FileType fileType) throws APIException {
        Path packer = validateFile(filePath);
        List<Package> strategyResult = Packer.executeStrategy(packer, fileType.getExtensionType());
        return runAlgorithm(strategyResult);
    }


    private static String runAlgorithm(List<Package> packages) {
        IProcess<List<Package>, String> process = new Knapsack();
        return process.execute(packages);
    }


    private static Path validateFile(String filePath) throws APIException {
        // convert the filepath to an instance of java.nio.file.Path, and check if actual path exists on the file system. (This will normalize and return an actual path) or throw IOexception if the path does not exist.
        Path realPath;
        try {
            realPath = Paths.get(filePath).toRealPath(LinkOption.NOFOLLOW_LINKS); // explicitly dont follow symbolic links
        } catch (IOException e) {
            throw new APIException("Cannot find an actual file from specified input path", e);
        }

        if (!Files.exists(realPath)) {
            throw new APIException("Cannot find an actual file from specified path");
        }

        if (!Files.isReadable(realPath)) {
            throw new APIException("File attributes [read] restrict access to this file");
        }
        return realPath;
    }

    private static List<Package> executeStrategy(Path path, String fileType) throws APIException {
        IFileReader<Path, List<Package>> ifileReader = Registry.getRegistry(fileType);
        return ifileReader.readFile(path);
    }

}
