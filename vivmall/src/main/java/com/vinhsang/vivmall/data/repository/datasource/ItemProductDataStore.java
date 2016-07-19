/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vinhsang.vivmall.data.repository.datasource;

import com.vinhsang.vivmall.data.entity.ItemProductEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface ItemProductDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link ItemProductEntity}.
   */
  Observable<List<ItemProductEntity>> iEntityListObservable();

  /**
   * Get an {@link Observable} which will emit a {@link ItemProductEntity} by its id.
   *
   * @param userId The id to retrieve user data.
   */
  Observable<ItemProductEntity> itemEntityDetails(final int userId);
}
