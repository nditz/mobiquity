package com.mobiquity.filereaders;

import com.mobiquity.exception.APIException;
import com.mobiquity.packages.Package;

import java.nio.file.Path;
import java.util.List;

public class CsvFileReader implements IFileReader<Path, List<Package>> {

    @Override
    public List<Package> readFile(Path path) throws APIException {
        throw new APIException("Not yet supported", new UnsupportedOperationException());
    }
}