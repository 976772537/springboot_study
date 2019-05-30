package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
    private BufferedReader bufferedReader= null;
    private final String LINESEPARATOR = System.getProperty("line.separator", "\n");

    public HashMap<String,String> run(String path,String keyWord) throws IOException {
        bufferedReader = new BufferedReader (new InputStreamReader (getUrl (path).getInputStream ()));
        StringBuffer stringBuffer = new StringBuffer (64);
        String len;
        while((len=bufferedReader.readLine ())!=null){
            //沒調用一行解析一行獲取href
            stringBuffer.append (len + LINESEPARATOR);
        }
        iOClose ();//关闭流
        return  analysisHref (stringBuffer.toString ());
    }

    private URLConnection getUrl(String str_url) throws IOException {
        URL url = new URL (str_url);
        return url.openConnection ();
    }

    private HashMap<String,String> analysisHref(String len) {
        Document doc = Jsoup.parse (len);
        Elements aTags = doc.getElementsByTag ("a");
        HashMap<String,String> map = new HashMap<> ();
        String str=null;
        for(Element a : aTags){
                   str= match(a);
                   if(str!=null){
                       map.put (str,a.text ());//把连接作为key值防止重复
                   }
        }
        return map;
    }

    public void showMap(HashMap<String,String> map) {
        map.forEach ((k,v) -> System.out.println (v +" : " + k));
    }

    private String match(Element a) {
        Pattern pattern = Pattern
                .compile ("(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)" +
                        "([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&amp;%\\$#_]*)?");
        Matcher matcher = pattern.matcher (a.toString ());
        if(matcher.find ()){
            return matcher.group ();
        }
        return null;
    }

    private void iOClose(){
       if(bufferedReader!=null){
           try {
               bufferedReader.close ();
           } catch (IOException e) {
               e.printStackTrace ();
           }
       }
    }
}
