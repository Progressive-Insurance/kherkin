package com.progressive.sampleapp.activities.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.activities.xml.TextFieldActivity

class BasicComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SmallTopAppBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Small Top App Bar")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            var buttonVisibility by remember { mutableStateOf(true) }

            Greeting()
            BasicButton()
            HidingButton(buttonIsVisible = buttonVisibility, changeValue = { buttonVisibility = it } )
            TextField()
            TextFieldPrefilled()
            ScrollBoxes()
            NavigateButton()
        }
    }
}

@Composable
private fun Greeting() {
    Text(text = stringResource(id = R.string.hello),
        modifier = Modifier.padding(10.dp))
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
            .padding(10.dp)
            .testTag("Compose Button")
    ) {
        Text(text)
    }
}

@Composable
private fun HidingButton(buttonIsVisible: Boolean, changeValue: (Boolean) -> Unit) {
    val hiddenButtonText = stringResource(id = R.string.hide_button)
    if (buttonIsVisible) {
        Button(
            onClick = {
                changeValue(false)
            },
            contentPadding = PaddingValues(all = 20.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
            modifier = Modifier
                .padding(10.dp)
                .testTag("Hidden Button")
        ) {
            Text(text = hiddenButtonText)
        }
    }
}

@Composable
private fun TextField() {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(stringResource(id = R.string.label)) },
        placeholder = { Text(stringResource(id = R.string.placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .padding(10.dp)
            .testTag("Text Field")
    )
}

@Composable
private fun TextFieldPrefilled() {
    val focusManager = LocalFocusManager.current
    val textFieldText = stringResource(id = R.string.prefilled_text_field)
    var text by remember { mutableStateOf(textFieldText) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(stringResource(id = R.string.label)) },
        placeholder = { Text(stringResource(id = R.string.placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .padding(10.dp)
            .testTag("Prefilled Field")
    )
}

@Composable
private fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .testTag("Scroll Box")
    ) {
        repeat(10) {
            Text("Item $it",
                modifier = Modifier
                    .padding(2.dp)
                    .testTag("Scroll Item $it")
            )
        }
    }
}

@Composable
private fun NavigateButton() {
    val buttonText = stringResource(id = R.string.button_navigate)
    val context = LocalContext.current

    Button(
        onClick = {
            context.startActivity(Intent(context, TextFieldActivity::class.java))
        },
        contentPadding = PaddingValues(all = 20.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(10.dp)
            .testTag("Compose Button")
    ) {
        Text(buttonText)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    SmallTopAppBar()
}