package to.make.habitholic

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import to.make.habitholic.ui.theme.HabitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //LayoutMain()
                    Conversation(messages = getSampleMessage())
                }
            }

            /*HabitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Mus")
                    //Text("Hello world!")
                }
            }*/
        }
    }
}

fun getSampleMessage() : List<Message> {
    //create sample messages
    var messages : MutableList<Message> = mutableListOf()
    for (i in 0 until 10) {
        val msg = Message("Sender $i", "Hello My message $i")
        messages.add(msg)
    }
    return messages
}




@Composable
fun Greeting(name: String) {
    HabitTheme {
        Text(text = "Hello $name")
    }
}

@Composable
fun LayoutMain() {
    MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great"))
    /*HabitTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Mus")
        }
    }*/
}

@Composable
fun MessageCard(msg : Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.onSecondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)

        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
            val expandedString = "-expanded"
            when(isExpanded) {
                true -> {
                    msg.body += expandedString
                }
                else -> {
                    if (msg.body.contains(expandedString)) {
                        msg.body = msg.body.replace(expandedString, "")
                    }
                }
            }
            Log.d("TEST", "click message card")
        }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium,
                tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.titleSmall
                )
            }

        }
    }

}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn() {
        items(messages) {
            MessageCard(it)
        }
    }
}

data class Message(val author: String, var body: String)


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewConversation() {
    HabitTheme() {
        Conversation(messages = getSampleMessage())
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DefaultPreview() {
    HabitTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great"))
        }
    }
}