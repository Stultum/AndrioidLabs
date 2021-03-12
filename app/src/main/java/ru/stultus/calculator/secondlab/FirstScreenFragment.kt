package ru.stultus.calculator.secondlab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory

import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import ru.stultus.calculator.BaseFragment
import ru.stultus.calculator.R
import ru.stultus.calculator.databinding.FirstScreenFragmentBinding

class FirstScreenFragment :
    BaseFragment<FirstScreenFragmentBinding>(R.layout.first_screen_fragment) {


    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FirstScreenFragmentBinding = FirstScreenFragmentBinding.inflate(inflater, container, false)
}
