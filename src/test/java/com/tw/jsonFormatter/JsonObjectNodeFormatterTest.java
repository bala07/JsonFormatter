package com.tw.jsonFormatter;

import org.codehaus.jackson.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JsonObjectNodeFormatterTest {
    @Mock
    private JsonFormatterFactory formatterFactory;
    @Mock
    private JsonSimpleNodeFormatter simpleObjectFormatter;
    @Mock
    private JsonArrayNodeFormatter arrayFormatter;
    @Mock
    private JsonObjectNodeFormatter complexObjectFormatter;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void formatsObjectWithSingleKeyValuePair() {
        JsonNode node = mock(JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = mock(Iterator.class);
        Map.Entry<String, JsonNode> entry = mock(Map.Entry.class);
        JsonNode textNode = mock(JsonNode.class);

        when(node.getFields()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(entry);
        when(entry.getKey()).thenReturn("key");
        when(entry.getValue()).thenReturn(textNode);
        when(formatterFactory.getFormatter(textNode.getClass())).thenReturn(simpleObjectFormatter);
        when(simpleObjectFormatter.format(textNode)).thenReturn(newArrayList("formattedValue"));

        List<String> formattedJson = new JsonObjectNodeFormatter(formatterFactory).format(node);

        assertThat(formattedJson.size(), is(3));
        assertThat(formattedJson.get(0), is("{\n"));
        assertThat(formattedJson.get(1), is("\tkey: formattedValue\n"));
        assertThat(formattedJson.get(2), is("}"));
    }

    @Test
    public void formatsObjectWithArray() {
        JsonNode node = mock(JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = mock(Iterator.class);
        Map.Entry<String, JsonNode> entry = mock(Map.Entry.class);
        JsonNode arrayNode = mock(JsonNode.class);

        when(node.getFields()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(entry);
        when(entry.getKey()).thenReturn("arrayKey");
        when(entry.getValue()).thenReturn(arrayNode);
        when(formatterFactory.getFormatter(arrayNode.getClass())).thenReturn(arrayFormatter);
        when(arrayFormatter.format(arrayNode)).thenReturn(newArrayList("[value1, value2]"));

        List<String> formattedJson = new JsonObjectNodeFormatter(formatterFactory).format(node);

        assertThat(formattedJson.size(), is(3));
        assertThat(formattedJson.get(0), is("{\n"));
        assertThat(formattedJson.get(1), is("\tarrayKey: [value1, value2]\n"));
        assertThat(formattedJson.get(2), is("}"));
    }

    @Test
    public void formatsObjectWithSimpleKeyValuePairAndArray() {
        JsonNode node = mock(JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = mock(Iterator.class);
        Map.Entry<String, JsonNode> entry1 = mock(Map.Entry.class);
        Map.Entry<String, JsonNode> entry2 = mock(Map.Entry.class);
        JsonNode arrayNode = mock(JsonNode.class);
        JsonNode textNode = mock(JsonNode.class);

        when(node.getFields()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, true, false);
        when(iterator.next()).thenReturn(entry1, entry2);
        when(entry1.getKey()).thenReturn("key");
        when(entry1.getValue()).thenReturn(textNode);
        when(formatterFactory.getFormatter(textNode.getClass())).thenReturn(simpleObjectFormatter);
        when(arrayFormatter.format(textNode)).thenReturn(newArrayList("value"));
        when(entry2.getKey()).thenReturn("arrayKey");
        when(entry2.getValue()).thenReturn(arrayNode);
        when(formatterFactory.getFormatter(arrayNode.getClass())).thenReturn(arrayFormatter);
        when(arrayFormatter.format(arrayNode)).thenReturn(newArrayList("[value1, value2]"));

        List<String> formattedJson = new JsonObjectNodeFormatter(formatterFactory).format(node);

        assertThat(formattedJson.size(), is(4));
        assertThat(formattedJson.get(0), is("{\n"));
        assertThat(formattedJson.get(1), is("\tkey: value,\n"));
        assertThat(formattedJson.get(2), is("\tarrayKey: [value1, value2]\n"));
        assertThat(formattedJson.get(3), is("}"));
    }

    @Test
    public void formatsObjectContainingAnotherObject() {
        JsonNode node = mock(JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = mock(Iterator.class);
        Map.Entry<String, JsonNode> entry = mock(Map.Entry.class);
        JsonNode objectNode = mock(JsonNode.class);

        when(node.getFields()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(entry);
        when(entry.getKey()).thenReturn("objectKey");
        when(entry.getValue()).thenReturn(objectNode);
        when(formatterFactory.getFormatter(objectNode.getClass())).thenReturn(complexObjectFormatter);
        when(complexObjectFormatter.format(objectNode)).thenReturn(newArrayList("{\n\tkey1: value1,\n\tkey2:value2\n}"));

        List<String> formattedJson = new JsonObjectNodeFormatter(formatterFactory).format(node);

        assertThat(formattedJson.size(), is(3));
        assertThat(formattedJson.get(0), is("{\n"));
        assertThat(formattedJson.get(1), is("\tobjectKey: {\n" +
                "\tkey1: value1,\n" +
                "\tkey2:value2\n" +
                "}\n"));
        assertThat(formattedJson.get(2), is("}"));
    }
}