package com.tw.jsonFormatter;

import com.google.common.annotations.VisibleForTesting;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.TextNode;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonFormatterFactory {
    private Map<Class, JsonNodeFormatter> formatterMap;


    public JsonFormatterFactory() {
        formatterMap = newHashMap();
        formatterMap.put(TextNode.class, new JsonSimpleNodeFormatter());
        formatterMap.put(ArrayNode.class, new JsonArrayNodeFormatter());
        formatterMap.put(ObjectNode.class, new JsonObjectNodeFormatter(this));
    }

    @VisibleForTesting


    public JsonNodeFormatter getFormatter(Class nodeType) {
        return formatterMap.get(nodeType);
    }
}
