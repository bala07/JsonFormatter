package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class JsonSimpleNodeFormatter implements JsonNodeFormatter {
    public List<String> format(JsonNode node) {
        String result = node.getTextValue();

        return newArrayList(result);
    }
}
