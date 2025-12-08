package com.raihan.configlibrary.core.storage.mockservices

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MockServicesDAO {
    @Query("SELECT * FROM tb_mock_services")
    fun getAll(): List<MockServicesEntity>

    @Query("SELECT * FROM tb_mock_services WHERE url=:url")
    fun getByURL(url: String): List<MockServicesEntity>

    @Insert
    fun insert(mock: MockServicesEntity)

    @Update
    fun update(mock: MockServicesEntity)

    @Query("DELETE FROM tb_mock_services WHERE id IN (:ids)")
    fun delete(ids: List<Long>)
}