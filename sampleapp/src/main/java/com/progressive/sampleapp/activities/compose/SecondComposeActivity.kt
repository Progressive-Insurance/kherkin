package com.progressive.sampleapp.activities.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressive.kherkin.sampleapp.R

class SecondComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SetupContent()
            }
        }
    }
}

@Composable
private fun SetupContent() {
    Column {
        Greeting()
        BasicButton()
    }
}

@Composable
private fun Greeting() {
    Text(text = stringResource(id = R.string.second_title))
}

@Composable
private fun BasicButton() {
    val buttonText = stringResource(id = R.string.button_navigate_to_final)
    val context = LocalContext.current

    Button(
        onClick = { context.startActivity(Intent(context, FinalComposeActivity::class.java)) },
        contentPadding = PaddingValues(all = 20.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(20.dp)
            .testTag("Compose Button")
    ) {
        Text(buttonText)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SetupContent()
}