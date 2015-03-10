package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonFormatter {
    private JsonFormatterFactory formatterFactory;

    public JsonFormatter() {
        this.formatterFactory = new JsonFormatterFactory();
    }

    public List<String> format(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        JsonNode rootNode = mapper.readTree(reader);
        return formatterFactory.getFormatter(rootNode.getClass()).format(rootNode);
    }
}
