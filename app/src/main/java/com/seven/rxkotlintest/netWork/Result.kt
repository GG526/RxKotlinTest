package com.seven.rxkotlintest.netWork

/**
 * Created by Seven on 2017/10/10.
 */
data class Result(var status: ResultStauts, var errorMessage: String?)
enum class ResultStauts {
    succeed, faild
}
