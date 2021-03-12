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
import ru.stultus.calculator.databinding.SecondScreenFragmentBinding
import kotlin.random.Random

class SecondScreenFragment :
    BaseFragment<SecondScreenFragmentBinding>(R.layout.second_screen_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client: OkHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://www.cbr.ru/scripts/")
            .client(client)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder()
                        .exceptionOnUnreadXml(false)
                        .build()
                )
            )
            .build()

        val api = retrofit.create(MetallApi::class.java)
        var result: Metall

        runBlocking {
            result = api.get()
            Log.d("result", result.list[0].buy)
        }
        binding.recycler.adapter = ItemAdapter(result)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SecondScreenFragmentBinding = SecondScreenFragmentBinding.inflate(inflater, container, false)

}

fun getRandomColor(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    return Pair(
        Pair(Random.nextInt(30, 225), Random.nextInt(30, 225)),
        Pair(Random.nextInt(30, 225), Random.nextInt(30, 225))
    )
}

@Xml
data class Metall(
    @Element(name = "Record")
    val list: List<MetalInfo>
)

@Xml
data class MetalInfo(
    @Attribute(name = "Date")
    val date: String,
    @Attribute(name = "Code")
    val code: String,
    @PropertyElement(name = "Buy")
    val buy: String,
    @PropertyElement(name = "Sell")
    val sell: String
)


interface MetallApi {
    @GET("xml_metall.asp?date_req1=01/07/2020&date_req2=13/07/2020")
    suspend fun get(): Metall
}