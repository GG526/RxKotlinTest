package com.seven.rxkotlintest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.seven.rxkotlintest.R
import com.seven.rxkotlintest.extension.toSchedulers
import com.seven.rxkotlintest.viewModel.MovieViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class MovieActivity : AppCompatActivity() {

    private var recycleView: RecyclerView? = null
    private var refreshLayout: SmartRefreshLayout? = null
    private val viewModel: MovieViewModel by lazy {  MovieViewModel(this) }
    private var mAdapter: MoviewAdapter = MoviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        recycleView = findViewById(R.id.recycler_view)
        recycleView?.adapter = mAdapter
        recycleView?.layoutManager = LinearLayoutManager(this)
        refreshLayoutInit()
    }

    private fun refreshLayoutInit() {
        refreshLayout = findViewById(R.id.smart_refresh_layout)
        refreshLayout?.setOnRefreshListener(object : OnRefreshListener{
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                viewModel.loadData(true).observeOn(AndroidSchedulers.mainThread())?.subscribe({ t ->
                    mAdapter.setItem(viewModel.getList())
                    refreshLayout?.finishRefresh()
                },{t ->
                    refreshLayout?.finishRefresh()
                })
            }
        })
        refreshLayout?.setOnLoadmoreListener(object : OnLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                viewModel.loadData(true).subscribe({t ->
                    mAdapter.setItem(viewModel.getList())
                    refreshLayout?.finishLoadmore()
                },{t ->
                    refreshLayout?.finishLoadmore()
                })
            }
        })
        refreshLayout?.autoRefresh()
    }

}
