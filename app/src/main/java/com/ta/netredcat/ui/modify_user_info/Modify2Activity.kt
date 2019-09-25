package com.ta.netredcat.ui.modify_user_info

import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import com.ta.netredcat.R
import com.ta.netredcat.ui.BaseTitleActivity
import kotlinx.android.synthetic.main.activity_modify2.*

class Modify2Activity : BaseTitleActivity() {
    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_HINT = "extra_hint"
        const val EXTRA_MAX_LENGTH = "extra_max_length"
    }

    override fun getTitleText(): String = intent.extras?.getString(EXTRA_TITLE).toString()

    override fun getContentLayoutId(): Int = R.layout.activity_modify2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        et_input.hint = intent.extras?.getString(EXTRA_HINT, "").toString()
        intent.extras?.getInt(EXTRA_MAX_LENGTH)?.let { setInputMaxLength(it) }
    }

    private fun setInputMaxLength(length: Int) {
        val filters = arrayOf<InputFilter>(NameLengthFilter(length))
        et_input.filters = filters
    }

    class NameLengthFilter(mAx_length: Int) : InputFilter {

        private var MAX_LENGTH: Int = mAx_length

        override fun filter(
            source: CharSequence?,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence {
            val destCount: Int = dest.toString().length
            val sourceCount: Int = source.toString().length
            return if (destCount + sourceCount > MAX_LENGTH) {
                ""
            } else {
                source.toString()
            }
        }
    }
}
