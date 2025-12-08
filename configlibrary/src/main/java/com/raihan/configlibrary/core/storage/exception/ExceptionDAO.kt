package com.raihan.configlibrary.core.storage.exception

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.raihan.configlibrary.core.model.exception.ExceptionType

@Dao
interface ExceptionDAO {
    @Query("SELECT * FROM tb_exception ORDER BY date_millis DESC")
    fun getAll(): List<ExceptionEntity>

    @Query("SELECT * FROM tb_exception WHERE type in (:type) ORDER BY date_millis DESC")
    fun getAllMainApp(type: String = ExceptionType.MainApp.type): List<ExceptionEntity>

    @Query("SELECT * FROM tb_exception WHERE date_millis BETWEEN (:start) AND (:end)")
    fun getAllBetweenDate(start: Long, end: Long): List<ExceptionEntity>

    @Insert
    fun insert(ex: ExceptionEntity)

    @Query("DELETE FROM tb_exception WHERE id IN (:ids)")
    fun delete(ids: List<Long>)
}