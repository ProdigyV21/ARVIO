package com.arvio.tv.settings

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class BufferingConfig(
    val minBufferSec: Int,
    val maxBufferSec: Int,
    val maxMemoryMb: Int
)

@Composable
fun BufferingSettingsScreen(
    uiState: BufferingConfig,
    onSave: (config: BufferingConfig) -> Unit
) {
    var minBuffer by remember { mutableFloatStateOf(uiState.minBufferSec.toFloat()) }
    var maxBuffer by remember { mutableFloatStateOf(uiState.maxBufferSec.toFloat()) }
    var maxMemory by remember { mutableFloatStateOf(uiState.maxMemoryMb.toFloat()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Buffering Settings",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Configuration Presets",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            PresetButton("Default", 15f, 50f, 64f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
            PresetButton("High-Speed (Fiber/5G)", 5f, 30f, 128f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
            PresetButton("Unstable (Mobile/WiFi)", 30f, 120f, 64f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            PresetButton("IPTV", 10f, 30f, 32f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
            PresetButton("Debrid", 20f, 90f, 128f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
            PresetButton("Low-Memory TV", 10f, 30f, 32f) { min, max, mem -> 
                minBuffer = min; maxBuffer = max; maxMemory = mem 
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Custom Overrides",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        SettingSlider(
            label = "Min Buffer",
            value = minBuffer,
            valueRange = 3f..30f,
            unit = "s",
            onValueChange = { minBuffer = it }
        )

        SettingSlider(
            label = "Max Buffer",
            value = maxBuffer,
            valueRange = 20f..120f,
            unit = "s",
            onValueChange = { maxBuffer = it }
        )

        SettingSlider(
            label = "Max Memory Buffer",
            value = maxMemory,
            valueRange = 32f..128f,
            unit = "MB",
            onValueChange = { maxMemory = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onSave(
                    BufferingConfig(
                        minBufferSec = minBuffer.toInt(),
                        maxBufferSec = maxBuffer.toInt(),
                        maxMemoryMb = maxMemory.toInt()
                    )
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Configuration")
        }
    }
}

@Composable
private fun PresetButton(
    label: String,
    minBuffer: Float,
    maxBuffer: Float,
    maxMemory: Float,
    onClick: (Float, Float, Float) -> Unit
) {
    Button(
        onClick = { onClick(minBuffer, maxBuffer, maxMemory) },
        modifier = Modifier.focusable()
    ) {
        Text(label)
    }
}

@Composable
private fun SettingSlider(
    label: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    unit: String,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label: ${value.toInt()}$unit",
            style = MaterialTheme.typography.bodyLarge
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            modifier = Modifier.focusable()
        )
    }
}