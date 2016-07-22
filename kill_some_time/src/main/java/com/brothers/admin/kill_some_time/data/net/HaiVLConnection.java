package com.brothers.admin.kill_some_time.data.net;

import android.util.Log;

import com.brothers.admin.kill_some_time.domain.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 7/21/2016.
 */

public class HaiVLConnection {

    private static volatile HaiVLConnection singleton;
    private static int count = 0;
    public static HaiVLConnection getInsant() {
        if (singleton == null) {
            synchronized (HaiVlApi.class) {
                singleton = new HaiVLConnection();
            }
        }
        return singleton;
    }
    private static String url = "http://haivlz.com/vote";
    public  List<Item> get(){
        List<Item> items = new ArrayList<>();
        Document doc;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();

            Elements es = doc.select("li.gag-link");
            for (Element e : es) {

                String title;
                String urlImg = "";
                String href = "";
                if (e == null) {
                    continue;
                }
                Element element = e.select("a.jump_focus").first();
                if (element == null) {
                    continue;
                }
                title = element.text();
                href = element.attr("href");
                urlImg = e.select("div.img-wrap").select("img").first().attr("src");

                Log.i("Go", "title: " + title + ":" + urlImg + ":" + href);
                Item item = new Item(++count, title,urlImg);
                items.add(item);

            }
            ;
            url = doc.select("a.older").first().attr("href");

        } catch (IOException e) {
            e.printStackTrace();
            return items;
        }
        return items;
    }
}
