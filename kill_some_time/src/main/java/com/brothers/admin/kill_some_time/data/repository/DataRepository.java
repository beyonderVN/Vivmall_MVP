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
package com.brothers.admin.kill_some_time.data.repository;


import com.brothers.admin.kill_some_time.data.repository.datasource.DataStoreFactory;
import com.brothers.admin.kill_some_time.data.repository.datasource.ItemDataStore;
import com.brothers.admin.kill_some_time.domain.Item;
import com.brothers.admin.kill_some_time.domain.repository.Repositoty;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link Repositoty} for retrieving user data.
 */
@Singleton
public class DataRepository implements Repositoty {

  private final DataStoreFactory dataStoreFactory;

  @Inject
  public DataRepository(DataStoreFactory dataStoreFactory) {
    this.dataStoreFactory = dataStoreFactory;
  }


  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<Item>> iListObservable() {
    //we always get all users from the cloud
    final ItemDataStore itemDataStore = this.dataStoreFactory.createCloudDataStore();
    return itemDataStore.iEntityListObservable();

  }

}
