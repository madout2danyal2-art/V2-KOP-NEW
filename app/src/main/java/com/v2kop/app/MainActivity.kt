package com.v2kop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF0B0B18)
private val CardColor = Color(0xFF17172B)
private val Purple = Color(0xFF8B5CF6)
private val TextColor = Color(0xFFF4F1FF)
private val Muted = Color(0xFF9B98B5)
private val Green = Color(0xFF39D98A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Purple,
                    background = Background,
                    surface = CardColor
                )
            ) {
                V2KopApp()
            }
        }
    }
}

@Composable
fun V2KopApp() {
    var connected by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            NavigationBar(
                containerColor = CardColor,
                contentColor = TextColor
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("خانه") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Dns, null) },
                    label = { Text("کانفیگ‌ها") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("تنظیمات") }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.Info, null) },
                    label = { Text("درباره") }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> HomeScreen(
                connected = connected,
                onToggle = { connected = !connected },
                modifier = Modifier.padding(padding)
            )

            1 -> SimplePage(
                title = "مدیریت کانفیگ‌ها",
                description = "در نسخه‌های بعدی، افزودن و مدیریت کانفیگ‌ها را اینجا تکمیل می‌کنیم.",
                icon = Icons.Default.Dns,
                modifier = Modifier.padding(padding)
            )

            2 -> SimplePage(
                title = "تنظیمات",
                description = "تنظیمات زبان و ظاهر برنامه در این بخش قرار می‌گیرد.",
                icon = Icons.Default.Settings,
                modifier = Modifier.padding(padding)
            )

            else -> SimplePage(
                title = "درباره V2 KOP",
                description = "نسخه 1.0 • نسخه آزمایشی\n\nاین نسخه هنوز موتور VPN واقعی ندارد.",
                icon = Icons.Default.Info,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun HomeScreen(
    connected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
            .padding(top = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "V2 KOP",
                    color = TextColor,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "اتصال امن، ساده و سریع",
                    color = Muted,
                    fontSize = 13.sp
                )
            }

            Surface(
                shape = CircleShape,
                color = CardColor,
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = "محافظت",
                        tint = Purple,
                        modifier = Modifier.size(27.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Surface(
            color = CardColor,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (connected) "وضعیت نمایشی: متصل" else "وضعیت: قطع",
                    color = if (connected) Green else Muted,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    onClick = onToggle,
                    shape = CircleShape,
                    color = if (connected) Color(0xFF173B32) else Color(0xFF282044),
                    modifier = Modifier.size(170.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (connected) Green else Purple,
                            modifier = Modifier.size(126.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.PowerSettingsNew,
                                    contentDescription = "تغییر وضعیت نمایشی",
                                    tint = Color.White,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = if (connected) "برای قطع نمایشی بزن" else "برای اتصال نمایشی بزن",
                    color = TextColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "این دکمه هنوز اتصال واقعی VPN برقرار نمی‌کند.",
                    color = Muted,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "دانلود",
                value = "0 KB/s",
                icon = Icons.Default.Download,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "آپلود",
                value = "0 KB/s",
                icon = Icons.Default.Upload,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            color = CardColor,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Dns,
                    contentDescription = null,
                    tint = Purple,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "سرور انتخاب‌شده",
                        color = Muted,
                        fontSize = 12.sp
                    )
                    Text(
                        "هنوز سروری انتخاب نشده",
                        color = TextColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Muted
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "نسخه 1.0 • آزمایشی",
            color = Muted,
            fontSize = 11.sp
        )
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        color = CardColor,
        shape = RoundedCornerShape(18.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Purple,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                color = Muted,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun SimplePage(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Purple,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = title,
            color = TextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            color = Muted,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
    }
}
