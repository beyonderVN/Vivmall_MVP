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
package com.vinhsang.vivmall.presentation.navigation;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.vinhsang.vivmall.presentation.model.ItemProductModel;
import com.vinhsang.vivmall.presentation.ui.activity.detailactivity.ItemDetailActivity;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model.ShotModel;
import com.vinhsang.vivmall.presentation.ui.activity.shotdetail.ShotActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the userModel list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToUserList(Context context) {
    if (context != null) {
//      Intent intentToLaunch = UserListActivity.getCallingIntent(context);
//      context.startActivity(intentToLaunch);
    }
  }

  /**
   * Goes to the item details screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToItemDetails(Context context, ItemProductModel itemId) {
    if (context != null) {
      Intent intentToLaunch = ItemDetailActivity.getCallingIntent(context, itemId);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToShotDetails(Context context, ShotModel shotModel) {
    if (context != null) {
      Intent intentToLaunch = ShotActivity.getCallingIntent(context, shotModel);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToShotDetails(Context context, ShotModel shotModel, ActivityOptions ops) {
    if (context != null) {

      Intent intentToLaunch = ShotActivity.getCallingIntent(context, shotModel);
      context.startActivity(intentToLaunch,ops.toBundle());
    }
  }
}
