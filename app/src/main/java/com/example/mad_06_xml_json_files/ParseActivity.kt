package com.example.mad_06_xml_json_files

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import javax.xml.parsers.DocumentBuilderFactory

class ParseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse)
        var i = intent
        if (i.getStringExtra("type") == "xml") {
            val istream = assets.open("city.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            val docBuilder = builderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(istream)
//reading the tag "employee" of empdetail file
            var city_name = doc.getElementsByTagName("cityname").item(0).textContent
            var latitude = doc.getElementsByTagName("latitude").item(0).textContent
            var longitude = doc.getElementsByTagName("longitude").item(0).textContent
            var temperature = doc.getElementsByTagName("temperature").item(0).textContent
            var humidity = doc.getElementsByTagName("humidity").item(0).textContent
            var t = findViewById<View>(R.id.txtML) as TextView
            t.setText("City_Name:$city_name\nLatitude:$latitude\nLongitude:$longitude\nTemperature:$temperature\nHumidity:$humidity")
        } else {
            var json = assets.open("city.json").bufferedReader().use { it.readText() }
            val obj = JSONObject(json)
            var city_name = obj.getString("cityname")
            var latitude = obj.getString("latitude")
            var longitude = obj.getString("longitude")
            var temperature = obj.getString("temperature")
            var humidity = obj.getString("humidity")
            var t = findViewById<View>(R.id.txtJSON) as TextView
            t.setText("City_Name:$city_name\nLatitude:$latitude\nLongitude:$longitude\nTemperature:$temperature\nHumidity:$humidity")
        }
    }
}
