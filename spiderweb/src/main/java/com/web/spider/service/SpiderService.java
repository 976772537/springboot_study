package com.web.spider.service;

import com.web.spider.entity.Web;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

@Slf4j
@Service
public class SpiderService {
    public ArrayList<Web> run(String str_url) throws IOException {
        URLConnection conn = getConnection (str_url);
        String page = getPage (conn);
        Elements tags = analysis (page);
        return getWebs (tags);
    }

    private Elements analysis(String page) {
        Document parse = Jsoup.parse (page);
        Elements tags = parse.getElementsByTag ("a");
        return tags;
    }

    private ArrayList<Web> getWebs(Elements tags) {
        ArrayList<Web> webs = new ArrayList<> (20);
//        Pattern pattern = Pattern.compile ("(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)" +
//                "([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?");
        for (Element tag : tags) {
//            Matcher matcher = pattern.matcher (tag.toString ());
//            if (matcher.find ()) {
                webs.add (new Web ().builder ()
                        .href (tag.attr ("abs:href"))
                        .title (tag.text ())
                        .build ());
//            }
        }
        return webs;
    }

    private String getPage(URLConnection conn) throws IOException {
        StringBuilder stringBuffer = new StringBuilder (128);
        BufferedReader br = new BufferedReader (new InputStreamReader (conn.getInputStream ()));
        String len = null;
        while ((len = br.readLine ()) != null) {
            if (len != null && !len.equals ("")) {
                stringBuffer.append (len);
            }
        }
        br.close ();
        return stringBuffer.toString ();
    }

    private URLConnection getConnection(String str_url) throws IOException {
        URL url = new URL (str_url);
        return url.openConnection ();
    }
}
