package service;

import java.io.IOException;
import java.util.HashMap;

public class SpiderController {
    private Spider spider = new Spider ();

    public void SpiderMain(String path) throws IOException {
        HashMap<String, String> map = spider.run (path);

    }
}
