package com.mstar.dragdrop3kotlin

/**
 * Created by Mstar on 03-01-2022.
 */
class ViewType {

    private var dataIndex = 0
    private var type = 0

    fun ViewType(dataIndex: Int, type: Int) {
        this.dataIndex = dataIndex
        this.type = type
    }

    fun getDataIndex(): Int {
        return dataIndex
    }

    fun getType(): Int {
        return type
    }
}