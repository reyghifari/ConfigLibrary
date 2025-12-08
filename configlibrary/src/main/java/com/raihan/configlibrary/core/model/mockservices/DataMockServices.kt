package com.raihan.configlibrary.core.model.mockservices

import androidx.lifecycle.ViewModel
import com.raihan.configlibrary.core.storage.mockservices.MockServicesEntity

data class DataMockServices(
    var selectedMockService: MockServicesEntity? = null,
) : ViewModel()
