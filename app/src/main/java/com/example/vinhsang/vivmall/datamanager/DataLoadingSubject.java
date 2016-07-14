/*
 * Copyright 2015 Google Inc.
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

package com.example.vinhsang.vivmall.datamanager;

import com.example.vinhsang.vivmall.activity.mainactivity.allfragment.AllPresentationModel;

/**
 * An interface for classes offering data loading state to be observed.
 */
public interface DataLoadingSubject {
    boolean isDataLoading();
    void registerCallback(DataLoadingCallbacks callbacks);
    void unregisterCallback(DataLoadingCallbacks callbacks);
    AllPresentationModel getAllPresentationMOdel();
    interface DataLoadingCallbacks {
        void dataStartedLoading();
        void dataFinishedLoading();
    }
}
