package com.ar.treedi.network

import com.ar.treedi.models.TreeData

suspend fun fetchTreeData(qrCode: String): TreeData {
    val service = ApiClient.retrofit.create(TreeService::class.java)
    return service.getTreeData(qrCode)
}