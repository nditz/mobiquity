package com.mobiquity.filereaders;

import com.mobiquity.exception.APIException;
import com.mobiquity.packages.Package;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {

    static Map<String, IFileReader<Path, List<Package>>> fileExtensionRegistry = new HashMap<>();

    public static void register(String extension, IFileReader<Path, List<Package>> fileReader) {
        fileExtensionRegistry.put(extension, fileReader);
    }

    public static IFileReader<Path, List<Package>> getRegistry(String extension) throws APIException {
        if (fileExtensionRegistry.containsKey(extension)) {
            return fileExtensionRegistry.get(extension);
        }
        throw new APIException("File extension is not registered yet", new UnsupportedOperationException());
    }
}