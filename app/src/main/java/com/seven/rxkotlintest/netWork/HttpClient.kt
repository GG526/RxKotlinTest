package com.seven.rxkotlintest.netWork

import com.seven.rxkotlintest.extension.toSchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Seven on 2017/9/30.
 */
class HttpClient {
    private val SEFAILT_TIMEOUT: Long = 20
    private var retrofit: Retrofit
    private var apiServers: ApiServers

    init {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(SEFAILT_TIMEOUT, TimeUnit.SECONDS)
        retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiServers.BASE_URL)
                .build()
        apiServers = retrofit.create(ApiServers::class.java)
    }

    fun getAPIServers(): ApiServers {
        return  apiServers
    }

    fun <T>request(observable: Observable<Response<T>>): Observable<T>{
        return observable.toSchedulers().map(object : Function<Response<T>, T> {
            override fun apply(t: Response<T>): T {
                if (t.subjects == null) {
                    throw Exception("数据返回错误")
                }
                // 这里要是有code相关错误的可以统一处理
                return  t.subjects!!
            }
        }).doOnEach(object : Observer<T>{
            override fun onNext(t: T) {

            }

            override fun onError(e: Throwable) {
                // 处理请求过程中错误
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {

            }
        })
    }
}