package ru.stultus.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.keelar.exprk.Expressions
import ru.stultus.calculator.databinding.FragmentCalculatorBinding

class CalculatorFragment : BaseFragment<FragmentCalculatorBinding>(R.layout.fragment_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalculatorBinding = FragmentCalculatorBinding.inflate(inflater, container, false)

    @SuppressLint("SetTextI18n")
    private fun setListeners() {
        with(binding) {
            oneButton.setOnClickListener { expr.text = expr.text.toString() + "1" }
            twoButton.setOnClickListener { expr.text = expr.text.toString() + "2" }
            threeButton.setOnClickListener { expr.text = expr.text.toString() + "3" }
            fourButton.setOnClickListener { expr.text = expr.text.toString() + "4" }
            fiveButton.setOnClickListener { expr.text = expr.text.toString() + "5" }
            sixButton.setOnClickListener { expr.text = expr.text.toString() + "6" }
            sevenButton.setOnClickListener { expr.text = expr.text.toString() + "7" }
            eightButton.setOnClickListener { expr.text = expr.text.toString() + "8" }
            nineButton.setOnClickListener { expr.text = expr.text.toString() + "9" }
            zeroButton.setOnClickListener { expr.text = expr.text.toString() + "0" }
            plusButton.setOnClickListener { expr.text = expr.text.toString() + "+" }
            minusButton.setOnClickListener { expr.text = expr.text.toString() + "-" }
            multButton.setOnClickListener { expr.text = expr.text.toString() + "*" }
            divideButton.setOnClickListener { expr.text = expr.text.toString() + "/" }
            dotButton.setOnClickListener { expr.text = expr.text.toString() + "." }
            CButton.setOnClickListener {
                expr.text = ""
                result.text = "0"
            }
            solveButton.setOnClickListener {
                if (!expr.text.toString().contains("/0*") and
                    !expr.text.toString().contains("/0+") and
                    !expr.text.toString().contains("/0-") and
                    !expr.text.toString().contains("/0/") and
                    (expr.text.last().toString() != "0") and
                    (expr.text.dropLast(1).last().toString() != "/")
                ) {
                    val res = Expressions().eval(expr.text.toString())
                    result.text = res.toString()
                } else {
                    result.text = "Ошибка деления на ноль"
                }
            }
        }
    }

}