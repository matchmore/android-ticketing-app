package io.matchmore.ticketing.views

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.SeekBar
import io.matchmore.ticketing.R
import kotlinx.android.synthetic.main.view_radius.view.*

class LabelSeekBar(context: Context, attr: AttributeSet) : LinearLayout(context, attr) {

    var value: Int
        get() = seekBar.progress + minValue
        set(value) {
            seekBar.progress = value - minValue
        }

    @StringRes
    var labelRes: Int = 0
        set(value) {
            field = value
            updateLabel()
        }

    var minValue: Int = 1
        set(value) {
            field = value
            updateLabel()
        }

    var maxValue: Int = 100
        set(value) {
            field = value
            updateLabel()
        }


    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_radius, this, true)
    }

    fun initValues(@StringRes labelRes: Int, maxValue: Int) {
        this.maxValue = maxValue
        this.labelRes = labelRes
        this.value = maxValue / 2
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateLabel()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        updateLabel()
    }

    private fun updateLabel() {
        seekBar.max = maxValue - minValue
        label.text = if (labelRes == 0) "" else context.getString(labelRes, value)
    }
}