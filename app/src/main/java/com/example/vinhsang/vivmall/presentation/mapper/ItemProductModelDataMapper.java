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
package com.example.vinhsang.vivmall.presentation.mapper;

import com.example.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.example.vinhsang.vivmall.domain.ItemProduct;
import com.example.vinhsang.vivmall.presentation.model.ItemProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ItemProductEntity} (in the data layer) to {@link ItemProduct} in the
 * domain layer.
 */
@Singleton
public class ItemProductModelDataMapper {

  @Inject
  public ItemProductModelDataMapper() {}

  /**
   * Transform a {@link ItemProductEntity} into an {@link ItemProduct}.
   *
   * @param itemProduct Object to be transformed.
   * @return {@link ItemProduct} if valid {@link ItemProductEntity} otherwise null.
   */
  public ItemProductModel transform(ItemProduct itemProduct) {
    ItemProductModel itemProductModel = null;
    if (itemProduct != null) {
      itemProductModel = new ItemProductModel(itemProduct.getProduct_id());
      itemProductModel.setProduct_image(itemProduct.getProduct_image());
      itemProductModel.setProduct_name(itemProduct.getProduct_name());
      itemProductModel.setProduct_des(itemProduct.getProduct_des());
      itemProductModel.setMore_information(itemProduct.getMore_information());
      itemProductModel.setProduct_price(itemProduct.getProduct_price());
    }

    return itemProductModel;
  }

  /**
   * Transform a List of {@link ItemProductEntity} into a Collection of {@link ItemProduct}.
   *
   * @param itemProducts Object Collection to be transformed.
   * @return {@link ItemProduct} if valid {@link ItemProductEntity} otherwise null.
   */
  public Collection<ItemProductModel> transform(Collection<ItemProduct> itemProducts) {
    Collection<ItemProductModel> itemProductModelCollection;

    if (itemProducts != null && !itemProducts.isEmpty()) {
      itemProductModelCollection = new ArrayList<>();
      for (ItemProduct itemProduct : itemProducts) {
        itemProductModelCollection.add(transform(itemProduct));
      }
    } else {
      itemProductModelCollection = Collections.emptyList();
    }

    return itemProductModelCollection;
  }
}
