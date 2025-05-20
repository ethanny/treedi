package com.ar.treedi.network

import com.ar.treedi.models.TreeData
import retrofit2.http.GET
import retrofit2.http.Path

interface TreeService {
    @GET("api/trees/qr/{qr_code}/")
    suspend fun getTreeData(@Path("qr_code") qrCode: String): TreeData
}