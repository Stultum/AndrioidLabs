package ru.stultus.calculator.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
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
import ru.stultus.calculator.R
import ru.stultus.calculator.SecondLabActivity

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
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

    var widgetText: MetalInfoo

    runBlocking {
        val result = api.get()
        Log.d("result", result.list[0].buy)
        widgetText = result.list[0]
    }
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.new_app_widget)
    views.setTextViewText(R.id.code, widgetText.code)
    views.setTextViewText(R.id.date, widgetText.date)
    views.setTextViewText(R.id.buy, widgetText.buy)
    views.setTextViewText(R.id.sell, widgetText.sell)
    val intent = Intent(context, SecondLabActivity::class.java)
    intent.putExtra("is", true)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.layout, pendingIntent)
//     Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}


@Xml
data class Metall(
    @Element(name = "Record")
    val list: List<MetalInfoo>
)

@Xml
data class MetalInfoo(
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