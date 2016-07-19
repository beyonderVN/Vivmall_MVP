package com.brothers.admin.kill_some_time.datamanager;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 16/07/2016.
 */

public class HaiVl implements HaiVlApi{
    private static volatile HaiVl singleton;
    public static HaiVl get() {
        if (singleton == null) {
            synchronized (HaiVl.class) {
                singleton = new HaiVl();
            }
        }
        return singleton;
    }

    private String url = "http://haivlz.com/vote";
    @Override
    public List<HaiVLItem> getSource() {

        List<HaiVLItem> items = new ArrayList<>();
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
                HaiVLItem item = new HaiVLItem(title, urlImg,"", href);
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
