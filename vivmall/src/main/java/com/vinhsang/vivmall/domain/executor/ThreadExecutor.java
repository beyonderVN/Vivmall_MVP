package com.vinhsang.vivmall.domain.executor;

/**
 * Created by Long on 7/19/2016.
 */

import com.vinhsang.vivmall.domain.interactor.ProductCase;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link ProductCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
