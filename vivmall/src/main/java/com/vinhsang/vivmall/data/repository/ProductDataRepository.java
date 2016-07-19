/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vinhsang.vivmall.data.repository;

import com.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.vinhsang.vivmall.data.entity.mapper.ProductEntityDataMapper;
import com.vinhsang.vivmall.data.repository.datasource.DataStoreFactory;
import com.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.vinhsang.vivmall.presentation.mapper.ItemProductModelDataMapper;
import com.vinhsang.vivmall.data.repository.datasource.ItemProductDataStore;
import com.vinhsang.vivmall.domain.ItemProduct;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * {@link ProductRepositoty} for retrieving user data.
 */
@Singleton
public class ProductDataRepository implements ProductRepositoty {

  private final DataStoreFactory dataStoreFactory;
  private final ProductEntityDataMapper entityDataMapper;

  /**
   * Constructs a {@link ProductRepositoty}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param productEntityDataMapper {@link ItemProductModelDataMapper}.
   */
  @Inject
  public ProductDataRepository(DataStoreFactory dataStoreFactory,
                               ProductEntityDataMapper productEntityDataMapper) {
    this.dataStoreFactory = dataStoreFactory;
    this.entityDataMapper = productEntityDataMapper;
  }


  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<ItemProduct>> iListObservable() {
    //we always get all users from the cloud
    final ItemProductDataStore productDataStore = this.dataStoreFactory.createCloudDataStore();
    return productDataStore.iEntityListObservable()
            .map(new Func1<List<ItemProductEntity>, List<ItemProduct>>() {
              @Override
              public List<ItemProduct> call(List<ItemProductEntity> itemProductEntities) {
                return entityDataMapper.transform(itemProductEntities);
              }
            });
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<ItemProduct> itemProductObservable(int itemId) {
    final ItemProductDataStore productDataStore = this.dataStoreFactory.create(itemId);
    return productDataStore.itemEntityDetails(itemId)
            .map(new Func1<ItemProductEntity, ItemProduct>() {
              @Override
              public ItemProduct call(ItemProductEntity itemProductEntity) {
                return entityDataMapper.transform(itemProductEntity);
              }
            });
  }
}
