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
package com.example.vinhsang.vivmall.datamanager;

import com.example.vinhsang.vivmall.datamanager.source.DataStore;
import com.example.vinhsang.vivmall.model.ItemProduct;
import com.fernandocejas.android10.sample.data.entity.mapper.UserEntityDataMapper;
import com.fernandocejas.android10.sample.data.repository.datasource.UserDataStore;
import com.fernandocejas.android10.sample.data.repository.datasource.UserDataStoreFactory;
import com.fernandocejas.android10.sample.domain.User;
import com.fernandocejas.android10.sample.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link ProductRepositoty} for retrieving user data.
 */
@Singleton
public class DataRepository implements ProductRepositoty {

  private final DataStoreFactory dataStoreFactory;
  private final EntityDataMapper entityDataMapper;

  /**
   * Constructs a {@link ProductRepositoty}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param productEntityDataMapper {@link EntityDataMapper}.
   */
  @Inject
  public DataRepository(DataStoreFactory dataStoreFactory,
                        EntityDataMapper productEntityDataMapper) {
    this.dataStoreFactory = dataStoreFactory;
    this.entityDataMapper = productEntityDataMapper;
  }


  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<ItemProduct>> iListObservable() {
    //we always get all users from the cloud
    final DataStore productDataStore = this.dataStoreFactory.createCloudDataStore();
    return productDataStore.iEntityListObservable()
            .map(itemProductEntities -> this.entityDataMapper.transform(userEntities));
  }
  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<ItemProduct> itemProductObservable(int itemId) {
    final DataStore productDataStore = this.dataStoreFactory.create(userId);
    return productDataStore.itemEntityDetails(itemId)
            .map(userEntity -> this.entityDataMapper.transform(userEntity));
  }
}
