package com.vinhsang.vivmall.data.bourbon;


import com.vinhsang.vivmall.data.bourbon.model.Comment;
import com.vinhsang.vivmall.data.bourbon.model.Shot;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface BourbonService {

    /**
     * Retrieve a list of shots
     */
    @GET("shots")
    Single<List<Shot>> getShots(@Query("access_token") String accessToken,
                                @Query("per_page") int perPage,
                                @Query("page") int page);

    /**
     * Retrieve a list of comments for a given shot
     */
    @GET("shots/{shot_id}/comments")
    Single<List<Comment>> getComments(@Path("shot_id") int shotId,
                                      @Query("access_token") String accessToken,
                                      @Query("per_page") int perPage,
                                      @Query("page") int page);

}
