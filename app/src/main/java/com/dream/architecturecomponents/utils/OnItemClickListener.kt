package com.dream.architecturecomponents.utils

import com.dream.architecturecomponents.data.model.BaseObject

interface OnItemClickListener<T: BaseObject> {

    fun onItemClick(item: T)

    fun onItemLongClick(item: T): Boolean

}