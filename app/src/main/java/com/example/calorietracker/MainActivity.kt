package com.example.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calorietracker.ui.theme.CalorieTrackerTheme
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalorieTracker()
                }
            }
        }
    }
}

@Composable
fun CalorieTracker(modifier: Modifier = Modifier) {
    var calorieCount by remember { mutableIntStateOf(0) }
    val percentage = ((calorieCount.toDouble()/2000)*100).toInt()
    val animatedProgress by animateFloatAsState(
        targetValue = (calorieCount.toDouble()/2000).toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
    )
    Row (verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = modifier.fillMaxWidth()
                    .height(350.dp),
                progress = animatedProgress,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$calorieCount/2000 Kcal",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "$percentage%",
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { calorieCount += 200 }) {
                Text(
                    text = "+200",
                    fontSize = 32.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalorieTrackerTheme {
        CalorieTracker()
    }
}