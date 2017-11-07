package com.seven.rxkotlintest.viewModel

import android.app.Activity
import com.seven.rxkotlintest.model.MoviesModel
import com.seven.rxkotlintest.model.MoviewActivityBindingModel
import com.seven.rxkotlintest.model.Subject
import com.seven.rxkotlintest.netWork.Result
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * Created by Seven on 2017/9/30.
 */
class MovieViewModel(val activity: Activity) {

    private val model: MoviesModel by lazy { MoviesModel() }
    private var bindingData: List<MoviewActivityBindingModel> = listOf()

    fun loadData(isRefresh: Boolean): Observable<Result> {
        return model.getData(isRefresh).doOnNext { t: Result ->
            bindingData = modelTransformation(model.data)
        }
    }

    private fun modelTransformation(list: List<Subject>): List<MoviewActivityBindingModel> {
        val bingList: MutableList<MoviewActivityBindingModel> = mutableListOf()
        Observable
                .fromIterable(list)
                .subscribe(object : Consumer<Subject>{
                    override fun accept(t: Subject) {
                        bingList.add(MoviewActivityBindingModel(t.title))
                    }
                })
        return bingList
    }

    fun getList(): List<MoviewActivityBindingModel> {
        return bindingData
    }
}