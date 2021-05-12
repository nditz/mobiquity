package com.mobiquity.filereaders;

import com.mobiquity.exception.APIException;

public interface IFileReader<T, U> {
    U readFile(T t) throws APIException;

    static void initializeRegistry() {
        Registry.register("txt", new TextFileReader());
        Registry.register("xml", new XmlFileReader());
        Registry.register("csv", new CsvFileReader());
    }
}
