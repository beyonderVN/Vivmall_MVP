package com.brothers.admin.kill_some_time.domain.executor;

/**
 * Created by Long on 7/19/2016.
 */

import com.brothers.admin.kill_some_time.domain.interactor.UseCase;

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
