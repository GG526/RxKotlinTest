package com.seven.rxkotlintest.netWork


/**
 * Created by Seven on 2017/9/30.
 */
data class Response<T>(var count: Int?,
                       var start: Int?,
                       var total: Int?,
                       var subjects: T?,
                       var title: String?)