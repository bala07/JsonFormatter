package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonArrayNodeFormatterTest {
    @Test
    public void formatsTheArrayNode() {
        JsonNode arrayNode = mock(JsonNode.class);
        Iterator<JsonNode> jsonNodes = mock(Iterator.class);
        JsonNode textNode = JsonNodeFactory.instance.textNode("value1");

        when(jsonNodes.hasNext()).thenReturn(true, false);
        when(arrayNode.getElements()).thenReturn(jsonNodes);
        when(jsonNodes.next()).thenReturn(textNode);

        List<String> result = new JsonArrayNodeFormatter().format(arrayNode);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("[value1]"));
    }

    @Test
    public void formatsTheArrayNodeContainingMultipleElements() {
        JsonNode arrayNode = mock(JsonNode.class);
        Iterator<JsonNode> jsonNodes = mock(Iterator.class);
        JsonNode textNode1 = JsonNodeFactory.instance.textNode("value1");
        JsonNode textNode2 = JsonNodeFactory.instance.textNode("value2");

        when(jsonNodes.hasNext()).thenReturn(true, true, false);
        when(arrayNode.getElements()).thenReturn(jsonNodes);
        when(jsonNodes.next()).thenReturn(textNode1, textNode2);

        List<String> result = new JsonArrayNodeFormatter().format(arrayNode);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("[value1, value2]"));
    }
}