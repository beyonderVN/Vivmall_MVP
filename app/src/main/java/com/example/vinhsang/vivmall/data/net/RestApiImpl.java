package com.example.vinhsang.vivmall.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.example.vinhsang.vivmall.data.entity.mapper.ProductEntityJsonMapper;
import com.example.vinhsang.vivmall.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Long on 7/18/2016.
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final ProductEntityJsonMapper productEntityJsonMapper;

    public RestApiImpl(Context context, ProductEntityJsonMapper productEntityJsonMapper) {
        this.context = context;
        this.productEntityJsonMapper = productEntityJsonMapper;
    }


    @Override
    public Observable<List<ItemProductEntity>> itemEntityListObservable() {
        return Observable.create(new Observable.OnSubscribe<List<ItemProductEntity>>() {
                                     @Override
                                     public void call(Subscriber<? super List<ItemProductEntity>> subscriber) {

                                         if (isThereInternetConnection()) {
                                             try {
                                                 String responseProductEntities = getProductEntitiesFromApi();
                                                 if (responseProductEntities != null) {
                                                     subscriber.onNext(productEntityJsonMapper.transformUserEntityCollection(responseProductEntities));
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
