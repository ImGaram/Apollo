package com.example.apollo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.example.graphql.ResultQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ./gradlew downloadApolloSchema --endpoint="https://rickandmortyapi.com/graphql" --schema="app/src/main/graphql/com/simpli/graphql/schema.json"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql/")
            .build()

        lifecycleScope.launch(Dispatchers.Main) {
            val result = apolloClient.query(ResultQuery()).execute()
            val text = findViewById<TextView>(R.id.text_view)
            text.text = result.data.toString()
        }
    }
}