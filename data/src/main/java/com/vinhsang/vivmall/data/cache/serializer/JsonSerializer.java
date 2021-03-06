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
package com.vinhsang.vivmall.data.cache.serializer;


import com.google.gson.Gson;
import com.vinhsang.vivmall.data.entity.ItemProductEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param entity {@link ItemProductEntity} to serialize.
   */
  public String serialize(ItemProductEntity entity) {
    String jsonString = gson.toJson(entity, ItemProductEntity.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link ItemProductEntity}
   */
  public ItemProductEntity deserialize(String jsonString) {
    ItemProductEntity entity = gson.fromJson(jsonString, ItemProductEntity.class);
    return entity;
  }
}
