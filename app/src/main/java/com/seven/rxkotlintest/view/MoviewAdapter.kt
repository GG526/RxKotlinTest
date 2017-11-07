package com.seven.rxkotlintest.view
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seven.rxkotlintest.R
import com.seven.rxkotlintest.model.MoviewActivityBindingModel

/**
 * Created by Seven on 2017/10/10.
 */
class MoviewAdapter : RecyclerView.Adapter<MoviewAdapter.MovieViewHolder>() {

    private var items: List<MoviewActivityBindingModel> = listOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviewAdapter.MovieViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent?.context), R.layout.movie_recycle_item, parent, false)
        return MovieViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: MoviewAdapter.MovieViewHolder?, position: Int) {
        holder?.bindData(items.get(position))
    }

    class MovieViewHolder(val view: View, val binding: ViewDataBinding): RecyclerView.ViewHolder(view) {
        fun bindData(item: MoviewActivityBindingModel) {
            binding.setVariable(com.seven.rxkotlintest.BR.movieRecycleItemBInding, item)
            binding.executePendingBindings()
        }
    }

    fun setItem(list: List<MoviewActivityBindingModel>) {
        items = list
        notifyDataSetChanged()
    }

}