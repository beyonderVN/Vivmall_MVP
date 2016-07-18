package com.example.vinhsang.vivmall.datamanager.source;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.vinhsang.vivmall.datamanager.entity.ItemProductEntity;
import com.example.vinhsang.vivmall.datamanager.exception.NetworkConnectionException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;

/**
 * Created by Long on 7/18/2016.
 */

public class RestApiImpl implements RestApi {

    private final Context context;


    public RestApiImpl(Context context) {
        this.context = context;

    }


    @Override
    public Observable<List<ItemProductEntity>> itemEntityListObservable() {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseProductEntities = getProductEntitiesFromApi();
                    if (responseProductEntities != null) {
                        subscriber.onNext(new Gson().fromJson( responseProductEntities,new TypeToken<List<ItemProductEntity>>() {}.getType()));
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
        });
    }

    @Override
    public Observable<ItemProductEntity> itemProductEntityObservable(int userId) {
        return null;
    }

    private String getProductEntitiesFromApi() throws MalformedURLException, ExecutionException, InterruptedException {
        return new ApiConnection.MyTaskLoadProduct().execute("0").get();
    }

    private String getProductDetailsFromApi(int productId) throws MalformedURLException {

        return null;
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
