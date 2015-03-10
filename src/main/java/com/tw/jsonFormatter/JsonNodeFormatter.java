package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;

import java.util.List;

public interface JsonNodeFormatter {
    List<String> format(JsonNode node);
}
