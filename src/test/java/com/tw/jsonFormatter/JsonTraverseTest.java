package com.tw.jsonFormatter;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class JsonTraverseTest {
    @Test
    public void formatJson() throws IOException {
        List<String> formattedJson = new JsonFormatter().format("/Users/balasubn/IdeaProjects/JsonFormatter/src/test/java/com/tw/jsonFormatter/sample.json");

        for(String jsonLine : formattedJson) {
            System.out.print(jsonLine);
        }
    }
}
