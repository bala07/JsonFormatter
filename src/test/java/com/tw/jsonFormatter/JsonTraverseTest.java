package com.tw.jsonFormatter;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class JsonTraverseTest {
    @Test
    public void formatJson() throws IOException, URISyntaxException {
        URI uri = ClassLoader.getSystemResource("sample.json").toURI();

        List<String> formattedJson = new JsonFormatter().format(uri.getPath());

        for(String jsonLine : formattedJson) {
            System.out.print(jsonLine);
        }
    }
}
