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
package com.vinhsang.vivmall.data.entity.mapper;


import com.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.vinhsang.vivmall.domain.ItemProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
@Singleton
public class ProductEntityDataMapper {

  @Inject
  public ProductEntityDataMapper() {}

  /**
   * Transform a {@link ItemProductEntity} into an {@link ItemProduct}.
   *
   * @param entity Object to be transformed.
   * @return {@link ItemProduct} if valid {@link ItemProductEntity} otherwise null.
   */
  public ItemProduct transform(ItemProductEntity entity) {
    ItemProduct itemProduct = null;
    if (entity != null) {
      itemProduct = new ItemProduct(entity.getProduct_id());
      itemProduct.setProduct_image(entity.getProduct_image());
      itemProduct.setProduct_name(entity.getProduct_name());
      itemProduct.setProduct_des(entity.getProduct_des());
      itemProduct.setMore_information(entity.getMore_information());
      itemProduct.setProduct_price(entity.getProduct_price());
    }

    return itemProduct;
  }

  /**
   * Transform a List of {@link ItemProductEntity} into a Collection of {@link ItemProduct}.
   *
   * @param entityCollection Object Collection to be transformed.
     * @return {@link ItemProduct} if valid {@link ItemProductEntity} otherwise null.
   */
  public List<ItemProduct> transform(Collection<ItemProductEntity> entityCollection) {
    List<ItemProduct> itemProducts = new ArrayList<>(20);
    ItemProduct itemProduct;
    for (ItemProductEntity userEntity : entityCollection) {
      itemProduct = transform(userEntity);
      if (itemProduct != null) {
        itemProducts.add(itemProduct);
      }
    }

    return itemProducts;
  }
}
