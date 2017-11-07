package com.seven.rxkotlintest.model
import com.seven.rxkotlintest.netWork.HttpClient
import com.seven.rxkotlintest.netWork.Result
import com.seven.rxkotlintest.netWork.ResultStauts
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Created by Seven on 2017/9/30.
 */
class MoviesModel {
    private var mPage: Int = 1
    private val mPageSize: Int = 20
    private var mIsReFresh: Boolean = false
    private val httpClient: HttpClient = HttpClient()
    var data: MutableList<Subject> = mutableListOf()

    fun getData(isRefresh: Boolean): Observable<Result>{
        mIsReFresh = isRefresh
        if (mIsReFresh) {
            mPage = 1
            data.clear()
        }else {
            mPage += 1
        }
        return getMovie()
    }

    private fun getMovie(): Observable<Result> {
          return Observable.create(object : ObservableOnSubscribe<Result> {
            override fun subscribe(e: ObservableEmitter<Result>) {
                httpClient.request(httpClient.getAPIServers().getMovies(mPage, mPageSize)).subscribe({t: List<Subject> ->
                    data.addAll(t)
                    e.onNext(Result(ResultStauts.succeed, null))
                },{t: Throwable ->
                    e.onNext(Result(ResultStauts.faild, t.localizedMessage))
                })
            }
        })


    }
}