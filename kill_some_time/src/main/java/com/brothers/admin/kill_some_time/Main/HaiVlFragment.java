package com.brothers.admin.kill_some_time.Main;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brothers.admin.kill_some_time.R;
import com.brothers.admin.kill_some_time.helper.ui.DynamicHeightImageView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HaiVlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HaiVlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HaiVlFragment extends Fragment {

    public HaiVlFragment() {

    }



    public static HaiVlFragment newInstance() {
        HaiVlFragment fragment = new HaiVlFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hai_vl, container, false);
    }



    @Override
    public void onDetach() {
        super.onDetach();

    }



    private ArrayAdapter<Card> listCardAdapter;
    private ListView listView;
    private String preUrl = "http://haivlz.com/vote";
    private String pre9pag = "http://9gag.com/";


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);



        ArrayList<Card> cards = new ArrayList<>();


        try {
            cards = new MyTask(getActivity()).execute(preUrl).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("cards", "onViewCreated: "+cards.size() );
        this.listCardAdapter = new MyAdapter<Card>(getActivity(), cards);
        this.listView.setAdapter(this.listCardAdapter);



        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int preLast = 0;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (firstVisibleItem == 0) {
                    // check if we reached the top or bottom of the list
                    View v = listView.getChildAt(0);
                    int offset = (v == null) ? 0 : v.getTop();
                    if (offset == 0) {

                    }
                } else {
                    if (totalItemCount - visibleItemCount == firstVisibleItem) {
                        if (preLast != lastItem) {
                            preLast = lastItem;
                            View v = listView.getChildAt(totalItemCount - 1);

                            int offset = (v == null) ? 0 : v.getTop();
                            if (offset == 0) {
                                Toast.makeText(getActivity(), preUrl, Toast.LENGTH_SHORT).show();
                                ArrayList<Card> cards = new ArrayList<>();
                                try {
                                    cards = new MyTask(getActivity()).execute(preUrl).get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                listCardAdapter.addAll(cards);
                                listCardAdapter.notifyDataSetChanged();
                                return;
                            }
                        }

                    } else {
                        preLast = 0;
                    }
                }
            }
        });



    }

    public class MyTask extends AsyncTask<String, Void, ArrayList<Card>> {

        Activity contextCha;

        //constructor này được truyền vào là MainActivity
        public MyTask(Activity ctx) {
            contextCha = ctx;
        }

        //hàm này sẽ được thực hiện đầu tiên
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }


        @Override
        protected ArrayList<Card> doInBackground(String... arg0) {
            ArrayList<Card> cards = new ArrayList<Card>();
            Document doc;
            Document doc2;
            try {
                doc = Jsoup.connect(arg0[0]).userAgent("Mozilla").get();

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
                    Card card = new Card(title, urlImg,"", href);
                    cards.add(card);

                }
                ;
                preUrl = doc.select("a.older").first().attr("href");
                return cards;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * ta cập nhập giao diện trong hàm này
         */
        @Override
        protected void onProgressUpdate(Void... result) {
            // TODO Auto-generated method stub
            super.onProgressUpdate();

        }


    }

    public class Card {
        private String title;
        private String urlImg;

        public Card(String title, String urlImg, String urlImgPlay, String href) {
            this.title = title;
            this.urlImg = urlImg;
            this.urlImgPlay = urlImgPlay;
            this.href = href;
        }

        private String urlImgPlay;
        private String href;

        public Card(String title, String urlImg, String href) {
            this.title = title;
            this.urlImg = urlImg;
            this.href = href;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrlImg() {
            return urlImg;
        }

        public void setUrlImg(String urlImg) {
            this.urlImg = urlImg;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public Card() {

        }

        public Card(String title, String urlImg) {

            this.title = title;
            this.urlImg = urlImg;
        }

        public String getUrlImgPlay() {
            return urlImgPlay;
        }

        public void setUrlImgPlay(String urlImgPlay) {
            this.urlImgPlay = urlImgPlay;
        }
    }

    public class MyAdapter<C> extends ArrayAdapter<Card> {
        int mResource = R.layout.card_layout;
        ArrayList<Card> cards = null;
        LayoutInflater mInflater;

        public MyAdapter(Context context, ArrayList<Card> cards) {
            this(context, R.layout.card_layout, cards);

        }

        public MyAdapter(Context context, int resource, ArrayList<Card> cards) {
            super(context, resource, cards);
            mInflater = LayoutInflater.from(context);
            this.cards = cards;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            try {
                view = createViewFromResource(mInflater, position, convertView, parent, mResource);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return view;
        }

        private View createViewFromResource(LayoutInflater inflater, int position, View convertView,
                                            ViewGroup parent, int resource) throws ExecutionException, InterruptedException {
            final View view;
            TextView text;
            DynamicHeightImageView img;

            final Card card;
            if (convertView == null) {
                view = inflater.inflate(resource, parent, false);
            } else {
                view = convertView;
            }
            text = (TextView) view.findViewById(R.id.text);
            img = (DynamicHeightImageView) view.findViewById(R.id.img);

            card = getItem(position);
            if (card.getTitle() != null) {
                text.setText(card.getTitle());
            }
            if (card.getUrlImg() != null) {

                Picasso.with(getContext()).load(card.getUrlImg()).into(img);
            }
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String src="";
                    try {
                        src = new MyTaskGetHref(getActivity()).execute(card.getHref()).get();
                        //TheApplication.href = src;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //startActivity(new Intent(getActivity(),YoutubeActivity.class));
                    //watchYoutubeVideo(TheApplication.href);
                }
            });
            return view;
        }

    }
    public class MyTaskGetHref extends AsyncTask<String, Void, String> {

        Activity contextCha;

        //constructor này được truyền vào là MainActivity
        public MyTaskGetHref(Activity ctx) {
            contextCha = ctx;
        }

        //hàm này sẽ được thực hiện đầu tiên
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        //sau đó tới hàm doInBackground
        //tuyệt đối không được cập nhật giao diện trong hàm này
        @Override
        protected String doInBackground(String... arg0) {

            Document doc;

            String url = "";//"http://haivlz.com/vui/73275/chiec-may-atm-nay-lam-dieu-gi-ma-ai-cung-bat-ngo,-tham-chi-bat-khoc?.html";
            url = arg0[0];
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla").get();
                String src = doc.select("iframe").attr("src");
                //Toast.makeText(contextCha, src, Toast.LENGTH_SHORT).show();
                return src;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * ta cập nhập giao diện trong hàm này
         */
        @Override
        protected void onProgressUpdate(Void... result) {
            // TODO Auto-generated method stub
            super.onProgressUpdate();

        }


    }

}
