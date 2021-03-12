package ru.stultus.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.stultus.calculator.databinding.DbFragmentBinding
import ru.stultus.calculator.dblab.ManDB
import ru.stultus.calculator.dblab.ManEntity

class DBLabFragment : BaseFragment<DbFragmentBinding>(R.layout.db_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db =
            requireContext().let { Room.databaseBuilder(it, ManDB::class.java, "database") }.build()
        val dao = db.manDao()
        var users: List<ManEntity> = listOf()

        val lp = TableRow.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )
        lp.bottomMargin = 32
        lp.topMargin = 32
        val row: TableRow = TableRow(binding.tableDb.context)
        row.layoutParams = lp
        row.gravity = Gravity.CENTER
        for (i in 0..3) {
            val tv = TextView(context)
            val lpb = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT
            )
            lpb.leftMargin = 32
            lpb.rightMargin = 32
            lpb.gravity = Gravity.CENTER
            tv.layoutParams = lpb
            when (i) {
                0 -> tv.text = String.format("Имя")
                1 -> tv.text = String.format("Вес")
                2 -> tv.text = String.format("Рост")
                3 -> tv.text = String.format("Возраст")
            }
            tv.setTextColor(Color.BLACK)
            row.addView(tv)
        }
        binding.tableDb.addView(row)


        val us = mutableListOf<ManEntity>(
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
            ManEntity(name = "John", weight = 83, height = 174, age = 25),
            ManEntity(name = "Markus", weight = 73, height = 186, age = 20),
            ManEntity(name = "Sam", weight = 95, height = 196, age = 35),
            ManEntity(name = "Alex", weight = 67, height = 169, age = 18),
        )

        GlobalScope.launch { withContext(Dispatchers.IO) { us.forEach { dao.insertAll(it) } } }



        GlobalScope.launch { withContext(Dispatchers.IO) { users = dao.loadSortedDB() } }

        while (users.isEmpty()) {

        }

        users.forEach { it ->
            val lp = TableRow.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
            )
            lp.bottomMargin = 32
            lp.topMargin = 32
            var row: TableRow = TableRow(binding.tableDb.context)
            row.layoutParams = lp
            row.gravity = Gravity.CENTER
            for (i in 0..3) {
                val tv = TextView(context)
                val lpb = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
                )
                lpb.gravity = Gravity.CENTER
                lpb.leftMargin = 32
                lpb.rightMargin = 32
                tv.layoutParams = lpb
                when (i) {
                    0 -> tv.text = String.format("%s", it.name)
                    1 -> tv.text = String.format("%d", it.weight)
                    2 -> tv.text = String.format("%d", it.height)
                    3 -> tv.text = String.format("%d", it.age)
                }
                tv.setTextColor(Color.BLACK)
                row.addView(tv)
            }
            binding.tableDb.addView(row)
        }
    }


    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): DbFragmentBinding =
        DbFragmentBinding.inflate(inflater, container, false)
}