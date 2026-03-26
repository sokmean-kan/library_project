package com.library.library_project.component

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

fun sortPagination(
    sortBy: List<String>?,
    currentPage: Int,
    pageSize: Int): PageRequest{
    val order = sortBy?.sortByMulti()?: listOf(Sort.Order.asc("id"))
    val sort = Sort.by(order)
    return PageRequest.of(currentPage - 1, pageSize, sort)
}