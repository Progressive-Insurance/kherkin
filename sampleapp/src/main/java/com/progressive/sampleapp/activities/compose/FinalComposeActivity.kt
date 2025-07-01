package com.progressive.sampleapp.activities.compose

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressive.kherkin.sampleapp.R

class FinalComposeActivity : ComponentActivity() {
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
    Text(text = stringResource(id = R.string.final_title),
        modifier = Modifier.padding(24.dp))
}

@Composable
private fun BasicButton() {
    val buttonText = stringResource(id = R.string.basic_button)
    val buttonTextClicked = stringResource(id = R.string.basic_button_clicked)
    var text by remember { mutableStateOf(buttonText) }

    Button(
        onClick = { text = buttonTextClicked },
        contentPadding = PaddingValues(all = 20.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(20.dp)
            .testTag("Compose Button")
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SetupContent()
}