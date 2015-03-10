package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.tw.jsonFormatter.SpecialCharacters.*;

public class JsonObjectNodeFormatter implements JsonNodeFormatter {

    private JsonFormatterFactory formatterFactory;

    public JsonObjectNodeFormatter(JsonFormatterFactory formatterFactory) {
        this.formatterFactory = formatterFactory;
    }

    public List<String> format(JsonNode node) {
        List<String> result = newArrayList();
        result.add(OBJECT_OPEN_BRACE + "\n");

        Iterator<Map.Entry<String, JsonNode>> fields = node.getFields();
        while(fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            JsonNode jsonNode = field.getValue();

            List<String> formattedJson = formatterFactory.getFormatter(jsonNode.getClass()).format(jsonNode);
            // TODO: adding the field name in the first line - refactor to remove this comment
            formattedJson.set(0, field.getKey() + COLON + SPACE + formattedJson.get(0));
            String commaString = fields.hasNext() ? COMMA : "";

            if(jsonNode instanceof ObjectNode) {
                // TODO: adding a comma at the end if required - refactor to remove this comment
                formattedJson.set(formattedJson.size() - 1, formattedJson.get(formattedJson.size() - 1) + commaString + "\n");
                for(String jsonLine : formattedJson) {
                    result.add("\t" + jsonLine);
                }
            }
            else {
                result.add("\t" + formattedJson.get(0) + commaString + "\n");
            }
        }

        result.add(OBJECT_CLOSE_BRACE);

        return result;
    }
}
