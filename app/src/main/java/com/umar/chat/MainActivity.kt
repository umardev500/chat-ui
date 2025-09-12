package com.umar.chat

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.umar.chat.presentation.theme.ChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        Log.d("FCM", "Permission granted: $isGranted")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showRationaleDialog() {
        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Enable Notifications")
            .setMessage(
                "To keep you updated, our app needs permission to send notifications. " +
                        "You can always change this later in app settings."
            )
            .setPositiveButton("OK") { _, _ ->
                // User agreed, request the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
            .setNegativeButton("No Thanks") { dialogInterface, _ ->
                // User declined, just dismiss dialog
                dialogInterface.dismiss()
            }
            .create()

        dialog.show()
    }


    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Already granted
                    Log.d("FCM", "Notification permission already granted")
                }

                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    Log.d("FCM", "Should show request permission rationale")
                    // Show an educational UI (AlertDialog)
                    showRationaleDialog()
                }

                else -> {
                    // Directly ask for permission
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatTheme {
                App()

                LaunchedEffect(Unit) {
                    askNotificationPermission()
                }
            }
        }
    }
}
