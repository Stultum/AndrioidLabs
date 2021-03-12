package ru.stultus.calculator.secondlab

import android.graphics.Color
import android.icu.number.Scale
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import com.google.android.material.button.MaterialButton
import ru.stultus.calculator.BaseFragment
import ru.stultus.calculator.R
import ru.stultus.calculator.databinding.ThirdScreenFragmentBinding

class ThirdScreenFragment :
    BaseFragment<ThirdScreenFragmentBinding>(R.layout.third_screen_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var row: TableRow
        val lp = TableRow.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )
        lp.bottomMargin = 32
        lp.topMargin = 32
        for (i in 0..20) {
            row = TableRow(binding.table.context)
            row.layoutParams = lp
            row.gravity = Gravity.CENTER
            for (j in 0..2) {
                val button = MaterialButton(binding.table.context)
                val lpb = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
                )
                lpb.leftMargin = 32
                lpb.rightMargin = 32
                button.layoutParams = lpb
                var colorThis = getRandomColor()
                button.cornerRadius = 8
                button.setBackgroundColor(
                    Color.argb(
                        colorThis.first.first,
                        colorThis.first.second,
                        colorThis.second.first,
                        colorThis.second.second
                    )
                )
                colorThis = getRandomColor()
                button.text = "ЖМИ"
                button.setTextColor(
                    Color.argb(
                        colorThis.first.first,
                        colorThis.first.second,
                        colorThis.second.first,
                        colorThis.second.second
                    )
                )
                row.addView(button)
            }
            binding.table.addView(row)
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ThirdScreenFragmentBinding = ThirdScreenFragmentBinding.inflate(inflater, container, false)
}