package com.raihan.configlibrary.ui.component.codeeditor

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.blacksquircle.ui.editorkit.plugin.autoindent.autoIndentation
import com.blacksquircle.ui.editorkit.plugin.base.PluginSupplier
import com.blacksquircle.ui.editorkit.plugin.delimiters.highlightDelimiters
import com.blacksquircle.ui.editorkit.plugin.dirtytext.OnChangeListener
import com.blacksquircle.ui.editorkit.plugin.dirtytext.changeDetector
import com.blacksquircle.ui.editorkit.plugin.linenumbers.lineNumbers
import com.blacksquircle.ui.editorkit.plugin.pinchzoom.pinchZoom
import com.blacksquircle.ui.editorkit.plugin.textscroller.textScroller
import com.blacksquircle.ui.editorkit.utils.EditorTheme
import com.blacksquircle.ui.editorkit.widget.TextProcessor
import com.blacksquircle.ui.editorkit.widget.TextScroller
import com.blacksquircle.ui.language.json.JsonLanguage
import com.blacksquircle.ui.language.kotlin.KotlinLanguage
import com.raihan.configlibrary.R
import org.json.JSONException
import org.json.JSONObject

class CodeEditorView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes
) {

    private var _onTextChangeListener: (String) -> Unit = {}

    init {
        inflate(context, R.layout.view_json_viewer, this)
        setLayout()
    }

    fun setText(text: String) {
        findViewById<TextProcessor>(R.id.tvEditor)?.setTextContent(format(text))
    }

    fun setOnTextChangeListener(callback: (String) -> Unit) {
        _onTextChangeListener = callback
    }

    fun setStyleJSON() {
        findViewById<TextProcessor>(R.id.tvEditor)?.language = JsonLanguage()
    }

    fun setStyleKotlin() {
        findViewById<TextProcessor>(R.id.tvEditor)?.language = KotlinLanguage()
    }

    override fun setFocusable(focusable: Boolean) {
        super.setFocusable(focusable)
        findViewById<TextProcessor>(R.id.tvEditor)?.isFocusable = focusable
    }

    private fun setLayout() {
        val tvEditor: TextProcessor = findViewById(R.id.tvEditor)
        val scrollerMain: TextScroller = findViewById(R.id.scroller)

        tvEditor.colorScheme = EditorTheme.OBSIDIAN
        tvEditor.setHorizontallyScrolling(true)
        val plugin = PluginSupplier.create {
            lineNumbers {
                lineNumbers = false
                highlightCurrentLine = true
            }
            pinchZoom()
            highlightDelimiters()
            autoIndentation()
            textScroller {
                scroller = scrollerMain
            }
            changeDetector {
                onChangeListener = OnChangeListener {
                    _onTextChangeListener(tvEditor.text.toString())
                }
            }
        }
        tvEditor.plugins(plugin)
        tvEditor.abortFling()
        tvEditor.scrollTo(0, 0)
        tvEditor.setTextContent("")
        //tvEditor.doOnPreDraw(View::requestFocus)
    }

    private fun format(code: String): String {
        if (code.isEmpty()) return code
        return try {
            JSONObject(code).toString(4)
        } catch (_: JSONException) {
            code
        }
    }
}