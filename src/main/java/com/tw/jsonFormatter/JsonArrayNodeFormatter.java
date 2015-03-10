package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.tw.jsonFormatter.SpecialCharacters.*;

public class JsonArrayNodeFormatter implements JsonNodeFormatter {

    public List<String> format(JsonNode arrayNode) {
        StringBuilder result = new StringBuilder();

        result.append(ARRAY_OPEN_BRACE);
        appendArrayElements(arrayNode, result);
        result.append(ARRAY_CLOSE_BRACE);

        return newArrayList(result.toString());
    }

    private void appendArrayElements(JsonNode arrayNode, StringBuilder result) {
        Iterator<JsonNode> arrayElements = arrayNode.getElements();
        boolean firstPass = true;

        while (arrayElements.hasNext()) {
            String arrayElement = arrayElements.next().getTextValue();
            if (firstPass) {
                firstPass = false;
            } else {
                result.append(COMMA + SPACE);
            }

            result.append(arrayElement);
        }
    }
}
