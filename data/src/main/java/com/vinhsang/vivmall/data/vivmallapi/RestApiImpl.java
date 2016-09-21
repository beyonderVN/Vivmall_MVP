package com.vinhsang.vivmall.data.vivmallapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.vinhsang.vivmall.data.entity.mapper.ProductEntityJsonMapper;
import com.vinhsang.vivmall.data.exception.NetworkConnectionException;
import com.vinhsang.vivmall.domain.Catalogue;

import java.net.MalformedURLException;
import java.util.ArrayList;
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
    public Observable<List<ItemProductEntity>> itemEntityListAllObservable(final int pos) {
        return Observable.create(new Observable.OnSubscribe<List<ItemProductEntity>>() {
                                     @Override
                                     public void call(Subscriber<? super List<ItemProductEntity>> subscriber) {

                                         if (isThereInternetConnection()) {
                                             try {
                                                 String responseProductEntities = getProductEntitiesFromApi(pos);
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

    @Override
    public Observable<List<Catalogue>> getListCatalogue() {
        return Observable.create(new Observable.OnSubscribe<List<Catalogue>>() {
                                             @Override
                                             public void call(Subscriber<? super List<Catalogue>> subscriber) {

                                                 if (isThereInternetConnection()) {
                                                     try {
                                                         List<Catalogue> list = new ArrayList<Catalogue>();
                                                         String[] tags = new String[]{"Điện tử", "Thiết bị thông minh", "Phụ kiện điện tử","Headphone","Kính mát","Đèn Led"};
                                                         if (tags != null) {
                                                             for (int i = 0; i < tags.length; i++) {
                                                                 list.add(new Catalogue(tags[i],i+1));
                                                             }
                                                             subscriber.onNext(list);
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
    public Observable<List<ItemProductEntity>> itemEntityListByCatalogueObservable(final int cataId, final int pos) {
        return Observable.create(new Observable.OnSubscribe<List<ItemProductEntity>>() {
                                     @Override
                                     public void call(Subscriber<? super List<ItemProductEntity>> subscriber) {

                                         if (isThereInternetConnection()) {
                                             try {
                                                 String responseProductEntities = getProductEntitiesByCatalogue(cataId, pos);
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
    private String getProductEntitiesFromApi(int pos) throws MalformedURLException, ExecutionException, InterruptedException {
        return new ApiConnection.MyTaskLoadProduct().execute(String.valueOf(pos)).get();
    }
    private String getProductEntitiesByCatalogue(int cataId,int pos) throws MalformedURLException, ExecutionException, InterruptedException {
        return new ApiConnection.MyTaskLoadProductByCatalogue().execute("0" + String.valueOf(cataId),String.valueOf(pos)).get();
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
