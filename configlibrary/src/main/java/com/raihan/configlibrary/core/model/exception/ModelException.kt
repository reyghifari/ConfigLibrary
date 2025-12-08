package com.raihan.configlibrary.core.model.exception

data class ModelException(
    var fileName: String = "",
    var className: String = "",
    var methodName: String = "",
    var lineNumber: String = "",
    var message: String = "",
) {
    override fun toString(): String {
        return """
            fileName = $fileName
            className = $className
            methodName = $methodName
            lineNumber = $lineNumber
            message :
            $message
        """.trimIndent()
    }
}
