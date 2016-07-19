package com.vinhsang.vivmall.domain.executor;

/**
 * Created by Long on 7/19/2016.
 */

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link com.example.vinhsang.vivmall.domain.interactor.ProductCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
