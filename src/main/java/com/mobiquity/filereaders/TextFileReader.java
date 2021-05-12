package com.mobiquity.filereaders;

import com.mobiquity.exception.APIException;
import com.mobiquity.packages.Item;
import com.mobiquity.packages.Package;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileReader implements IFileReader<Path, List<Package>> {

    @Override
    public List<Package> readFile(Path path) throws APIException {

        Stream<String> lines;
        try {
            //for this file type, explicitly read it using charset UTF-8
            lines = Files.lines(path, StandardCharsets.UTF_8);
        } catch (Exception apiexec) {
            throw new APIException("Check the charset for this file", apiexec);
        }
        Map<Integer, List<List<Item>>> parts = lines.map(s -> s.split(":"))
                .collect(Collectors.groupingBy(splitLine -> Integer.valueOf(splitLine[0].trim()), LinkedHashMap::new, Collectors.mapping(splitLine -> convertRowToItem(splitLine[1].trim().replaceAll("[()]", "").split("\\s+")), Collectors.toList())));

        return parts.entrySet().stream()
                .map(item -> new Package(item.getKey(), item.getValue().get(0)))
                .collect(Collectors.toList());

    }

    private static List<Item> convertRowToItem(String[] row) {

        return Arrays.stream(row).map(rowItem -> {
            String[] splitItem = rowItem.split(",");
            return new Item(Integer.parseInt(splitItem[0]), Double.parseDouble(splitItem[1]), Double.parseDouble(splitItem[2].replaceAll("[^a-zA-Z0-9]", "")));
        }).collect(Collectors.toList());
    }
}
