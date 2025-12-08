package com.raihan.configlibrary.core.storage.mockservices

import android.content.Context
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MockServicesDelegate(private val context: Context) :
    ReadOnlyProperty<Any, MockServicesDatabase> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MockServicesDatabase {
        return MockServices.getInstance(context)
    }
}