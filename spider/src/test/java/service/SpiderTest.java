package service;

import org.junit.Test;

import java.io.IOException;

public class SpiderTest {
    @Test
    public void UrlTest() throws IOException {
        String path="https://www.baidu.com";
        Spider spider = new Spider ();
        spider.run (path);
    }
}
