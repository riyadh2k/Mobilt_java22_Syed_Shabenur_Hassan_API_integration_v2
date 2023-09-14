package com.example.upgift3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class SecondFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val rq: RequestQueue = Volley.newRequestQueue(context)
        val url = "https://italian-jokes.vercel.app/api/jokes"

        val jokeRequest = JsonObjectRequest(
            url,
            { res ->
                val joke = res.getString("joke")
                view.findViewById<TextView>(R.id.jokeText).text = joke
            },
            { err -> Log.e("API_ERROR", err.toString()) }
        )
        rq.add(jokeRequest)

        /*view.findViewById<Button>(R.id.nextButton).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ThirdFragment())
                .addToBackStack(null)
                .commit()
        }*/

        return view
    }
}
