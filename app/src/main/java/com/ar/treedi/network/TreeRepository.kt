package com.ar.treedi.network

import android.util.Log
import com.ar.treedi.models.TreeData

suspend fun fetchTreeData(qrCode: String): TreeData {
    val service = ApiClient.retrofit.create(TreeService::class.java)
    Log.d("FetchTreeData", "Fetching data for QR: $qrCode")
    return service.getTreeData(qrCode)
}