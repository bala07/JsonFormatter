package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonSimpleNodeFormatterTest {
    @Test
    public void formatsTheSimpleJsonObject() {
        JsonNode textNode = mock(JsonNode.class);

        when(textNode.getTextValue()).thenReturn("value1");

        List<String> formattedJson = new JsonSimpleNodeFormatter().format(textNode);

        assertThat(formattedJson.size(), is(1));
        assertThat(formattedJson.get(0), is("value1"));
    }
}