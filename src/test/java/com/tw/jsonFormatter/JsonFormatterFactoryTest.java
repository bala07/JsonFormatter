package com.tw.jsonFormatter;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JsonFormatterFactoryTest {

    @Test
    public void returnsAppropriateJsonFormatter() {
        assertTrue(new JsonFormatterFactory().getFormatter(JsonNodeFactory.instance.arrayNode().getClass()) instanceof JsonArrayNodeFormatter);
        assertTrue(new JsonFormatterFactory().getFormatter(JsonNodeFactory.instance.textNode("").getClass()) instanceof JsonSimpleNodeFormatter);
        assertTrue(new JsonFormatterFactory().getFormatter(JsonNodeFactory.instance.objectNode().getClass()) instanceof JsonObjectNodeFormatter);

    }

}