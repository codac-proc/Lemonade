package jp.math.anti.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.math.anti.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeScreen() {
    var steps by remember { mutableStateOf(1) }
    var squeezes by remember { mutableStateOf((2..4).random()) }
    Column(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFFf9e44c)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Lemonade",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val imageResource = when(steps){
                1 -> R.drawable.lemon_tree
                2 -> R.drawable.lemon_squeeze
                3 -> R.drawable.lemon_drink
                4 -> R.drawable.lemon_restart
                else -> R.drawable.lemon_tree
            }
            val textResource = when(steps){
                1 -> R.string.lemon_tree
                2 -> R.string.lemon
                3 -> R.string.glass_of_lemonade
                4 -> R.string.empty_glass
                else -> R.string.lemon_tree
            }
            Image(
                painter = painterResource(imageResource),
                contentDescription = ""+steps,
                modifier = Modifier.clickable(
                    onClick = {
                        if (steps >= 4 || steps <= 0) {
                            steps = 1
                        } else if(steps != 2){
                            if(steps == 1){
                                squeezes = (2..4).random()
                            }
                            steps++
                        } else{
                            squeezes--
                            if(squeezes == 0){
                                steps++
                            }
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(stringResource(textResource))
        }
    }
}