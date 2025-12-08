package com.raihan.configlibrary.core.storage.mockservices

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raihan.configlibrary.core.Constants
import com.raihan.configlibrary.core.model.mockservices.ServicesHeader

@Entity(tableName = Constants.DATABASE.TB_MOCK_SERVICES)
data class MockServicesEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "url") var url: String = "",
    @ColumnInfo(name = "base_url") var baseURL: String = "",
    @ColumnInfo(name = "endpoint") var endpoint: String = "",
    @ColumnInfo(name = "method") var method: String = Constants.MOCK_SERVICES.HTTP_POST,
    @ColumnInfo(name = "http_code") var httpCode: String = "0",
    @ColumnInfo(name = "mock_request") var isMockRequest: Boolean = false,
    @ColumnInfo(name = "mock_response") var isMockResponse: Boolean = false,
    @ColumnInfo(name = "request_body") var requestBody: String = "{}",
    @ColumnInfo(name = "response_body") var responseBody: String = "{}",
    @ColumnInfo(name = "response_type") var responseType: String = "",
    @ColumnInfo(name = "file_name") var fileName: String = "",
    @ColumnInfo(name = "file_base_64") var fileBase64: String = "",
    @ColumnInfo(name = "header_request") var headerRequest: List<ServicesHeader> = listOf(),
    @ColumnInfo(name = "header_response") var headerResponse: List<ServicesHeader> = listOf(),
)