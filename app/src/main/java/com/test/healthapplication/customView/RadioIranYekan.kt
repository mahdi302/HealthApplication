package com.test.healthapplication.customView

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.RadioButton
import com.test.healthapplication.R
import com.test.healthapplication.util.NumberTranslator

class RadioIranYekan:RadioButton {

    var fontStyle: String? = null
    var fontName: String? = null
    var convertToPersianNumber = false

    constructor(context: Context) : super(context) {
        getStyle(context, null)
        if (!isInEditMode) {
            setFont()
            if (convertToPersianNumber) text = this.text
        }
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        getStyle(context, attrs)
        if (!isInEditMode) {
            setFont()
            if (convertToPersianNumber) text = this.text
        }
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        getStyle(context, attrs)
        if (!isInEditMode) {
            setFont()
            if (convertToPersianNumber) text = this.text
        }
    }

    private fun setFont() {
        val iranYekan = getTypeFace(context, fontName)
        setTypeface(iranYekan, Typeface.NORMAL)
    }

    override fun setText(text: CharSequence, type: BufferType?) {
        var text = text
        if (convertToPersianNumber) text = NumberTranslator.toPersian(text.toString())
        super.setText(text, type)
    }

    private fun getStyle(
        context: Context,
        attrs: AttributeSet?
    ) {
        if (attrs == null) {
            fontStyle = REGULAR
            fontName = REGULAR_FONT_NAME
            return
        }
        val ta = context.obtainStyledAttributes(attrs, R.styleable.iranYekanStyle, 0, 0)
        try {
            try { //Get Font Style
                fontStyle = ta.getString(R.styleable.iranYekanStyle_customFontStyle)
                if (fontStyle == null) fontStyle =
                    REGULAR else if (fontStyle!!.isEmpty()) fontStyle =
                    REGULAR else if (!fontStyle.equals(
                        REGULAR,
                        ignoreCase = true
                    ) && !fontStyle.equals(
                        BOLD,
                        ignoreCase = true
                    ) && !fontStyle.equals(LIGHT, ignoreCase = true)
                ) fontStyle = REGULAR
                //Set Font Name
                when {
                    fontStyle.equals(
                        REGULAR,
                        ignoreCase = true
                    )
                    -> fontName =
                        REGULAR_FONT_NAME
                    fontStyle.equals(
                        BOLD,
                        ignoreCase = true
                    )
                    -> fontName = BOLD_FONT_NAME
                    fontStyle.equals(
                        LIGHT,
                        ignoreCase = true
                    )
                    -> fontName = LIGHT_FONT_NAME
                }
            } catch (t: Throwable) {
                fontStyle = REGULAR
                fontName = REGULAR_FONT_NAME
            }
            convertToPersianNumber = try {
                ta.getBoolean(R.styleable.iranYekanStyle_convertToPersianNumber, false)
            } catch (t: Throwable) {
                false
            }
        } finally {
            try {
                ta.recycle()
            } catch (t: Throwable) {
            }
        }
    }

    companion object {
        private const val REGULAR = "0"
        private const val BOLD = "1"
        private const val LIGHT = "2"
        private const val REGULAR_FONT_NAME = "font/IRANYekanMobileRegular.ttf"
        private const val BOLD_FONT_NAME = "font/IRANYekanMobileBold.ttf"
        private const val LIGHT_FONT_NAME = "font/IRANYekanMobileLight.ttf"
        fun getTypeFace(context: Context, fontName: String?): Typeface {
            return if (fontName == null || fontName.isEmpty()) Typeface.createFromAsset(
                context.assets,
                REGULAR_FONT_NAME
            ) else Typeface.createFromAsset(context.assets, fontName)
        }
    }


}