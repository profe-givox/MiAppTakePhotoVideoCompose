package net.ivanvega.miapptakephotovideocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import net.ivanvega.miapptakephotovideocompose.ui.theme.MiAppTakePhotoVideoComposeTheme
import java.io.File
import java.net.URI

class MainActivity : ComponentActivity() {

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = ComposeFileProvider.getImageUri(applicationContext)
        setContent {
            MiAppTakePhotoVideoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ImagePicker()

                    //val fi = uri.toFile()
                    GrabarAudioScreen(
                        onClickStGra = {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }

                        },
                        onClickSpGra = {recorder.stop()},
                        onClickStRe = { audioFile?.let { player.start(it) } },
                        onClickSpRe = {player.stop()}

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiAppTakePhotoVideoComposeTheme {
        Greeting("Android")
    }
}