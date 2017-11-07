package com.seven.rxkotlintest.netWork

import io.reactivex.Observable
import com.seven.rxkotlintest.model.Subject
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Seven on 2017/9/29.
 */
interface ApiServers {
    companion object {
        val BASE_URL: String = "https://api.douban.com/v2/movie/"
    }

    @GET("top250")
    fun getMovies(@Query("page") page: Int, @Query("size") size: Int): Observable<Response<List<Subject>>>
}