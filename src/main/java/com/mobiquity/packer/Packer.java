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

        Path theFilePath = Validator.validateFile(filePath);
        List<Package> strategyResult = Packer.executeStrategy(theFilePath, FileType.DEFAULT);
        return runAlgorithm(strategyResult);
    }

    public static String pack(String filePath, FileType fileType) throws APIException {
        Path packer = Validator.validateFile(filePath);
        List<Package> strategyResult = Packer.executeStrategy(packer, fileType.getExtensionType());
        return runAlgorithm(strategyResult);
    }


    private static String runAlgorithm(List<Package> packages) {
        IProcess<List<Package>, String> process = new Knapsack();
        return process.execute(packages);
    }


    

    private static List<Package> executeStrategy(Path path, String fileType) throws APIException {
        IFileReader<Path, List<Package>> ifileReader = Registry.getRegistry(fileType);
        return ifileReader.readFile(path);
    }
    
   

    
//     public static void main(String[] args) throws APIException {
//        System.out.println(Packer.pack("F:\\Code\\Java\\mobiquity\\src\\main\\test\\resources\\example_input"));
//    }
}

