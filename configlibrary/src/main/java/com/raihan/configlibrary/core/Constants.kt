package com.raihan.configlibrary.core

object Constants {

    object PREF {
        const val BASE_URL = "env_base_url"
        const val SETUP = "env_setup"
        const val RECEIVER = "env_receiver_v1"
    }

    object DATABASE {
        const val TB_EXCEPTION = "tb_exception"
        const val TB_MOCK_SERVICES = "tb_mock_services"
    }

    object EXCEPTIONS {
        const val PAGE_HOME = "page_home"
        const val PAGE_DETAIL = "page_detail"
    }

    object MOCK_SERVICES {
        const val HTTP_GET: String = "GET"
        const val HTTP_POST: String = "POST"
        const val HEADER_CONTENT_TYPE: String = "content-type"
        const val CONTENT_TYPE_JSON = "application/json; charset=UTF-8"
        const val RESPONSE_TYPE_JSON = "application/json"
        const val RESPONSE_TYPE_PDF = "application/pdf"
        const val RESPONSE_TYPE_TEXT = "text/plain"

        const val PAGE_HOME = "page_home"
        const val PAGE_ADD = "page_add"
        const val PAGE_CHANGE = "page_change"
    }

    object ACTIVITY_PROCESS {
        const val INTENT_PROCESS = "intent_process"
        const val PROCESS_RESET_USER_DATA = "reset_user"
    }
}