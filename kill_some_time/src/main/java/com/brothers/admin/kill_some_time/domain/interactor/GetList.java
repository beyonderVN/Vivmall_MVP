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
package com.brothers.admin.kill_some_time.domain.interactor;


import com.brothers.admin.kill_some_time.domain.Item;
import com.brothers.admin.kill_some_time.domain.executor.PostExecutionThread;
import com.brothers.admin.kill_some_time.domain.executor.ThreadExecutor;
import com.brothers.admin.kill_some_time.domain.repository.Repositoty;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Item}.
 */
public class GetList extends UseCase {

  private final Repositoty repositoty;

  @Inject
  public GetList(Repositoty repositoty, ThreadExecutor threadExecutor,
                 PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.repositoty = repositoty;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.repositoty.iListObservable();
  }
}
