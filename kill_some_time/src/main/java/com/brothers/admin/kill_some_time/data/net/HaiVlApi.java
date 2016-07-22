package com.brothers.admin.kill_some_time.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.brothers.admin.kill_some_time.data.exception.NetworkConnectionException;
import com.brothers.admin.kill_some_time.domain.Item;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Admin on 16/07/2016.
 */

public class HaiVlApi implements Api {
    final Context context ;
    public HaiVlApi(Context context) {
        this.context = context;

    }


    @Override
    public Observable<List<Item>> getSource() {
        return Observable.create(new Observable.OnSubscribe<List<Item>>() {
                                     @Override
                                     public void call(Subscriber<? super List<Item>> subscriber) {

                                         if (isThereInternetConnection()) {
                                             try {
                                                 List<Item> responseProductEntities = getItemFromApi();
                                                 if (responseProductEntities != null) {
                                                     subscriber.onCompleted();
                                                 } else {
                                                     subscriber.onError(new NetworkConnectionException());
                                                 }
                                             } catch (Exception e) {
                                                 subscriber.onError(new NetworkConnectionException(e.getCause()));
                                             }
                                         } else {
                                             subscriber.onError(new NetworkConnectionException());
                                         }

                                     }
                                 }
        );

    }
    private List<Item> getItemFromApi() throws MalformedURLException {
        List<Item> items = HaiVLConnection.getInsant().get();
        return items;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
