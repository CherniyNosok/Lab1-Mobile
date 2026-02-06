package ru.kalinin.lab1mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.kalinin.lab1mobile.ui.theme.Lab1MobileTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1MobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)

                    CardApp(windowSize)
                }
            }
        }
    }
}

@Composable
fun InfoPanel(name: String, info: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.avatar)
    val titleSize = dimensionResource(R.dimen.title_font_size).value.sp
    val textSize = dimensionResource(R.dimen.main_font_size).value.sp
    val padding = dimensionResource(R.dimen.standard_padding)
    val avatarSize = dimensionResource(R.dimen.avatar_size)

    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.large_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = stringResource(R.string.avatar_description),
            modifier = Modifier
                .padding(padding, padding, padding)
                .size(avatarSize)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontSize = titleSize,
            lineHeight = titleSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(padding)
        )
        Text(
            text = info,
            fontSize = textSize,
            lineHeight = textSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = padding)
        )
    }
}

@Composable
fun ContactTemplate(
    icon: ImageVector,
    description: String,
    text: String,
    modifier: Modifier = Modifier
) {
    val padding = dimensionResource(R.dimen.small_padding)
    val fontSize = dimensionResource(R.dimen.main_font_size).value.sp

    Row(
        modifier = modifier
            .padding(vertical = padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            fontSize = fontSize,
            lineHeight = fontSize,
            modifier = Modifier.padding(horizontal = padding)
        )
    }
}

@Composable
fun ContactsPanel(
    phoneNumber: String,
    telegramLink: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        ContactTemplate(
            icon = Icons.Rounded.Call,
            description = stringResource(R.string.phone_number_description),
            text = phoneNumber
        )
        ContactTemplate(
            icon = Icons.AutoMirrored.Rounded.Send,
            description = stringResource(R.string.telegram_link_description),
            text = telegramLink
        )
        ContactTemplate(
            icon = Icons.Rounded.Email,
            description = stringResource(R.string.email_description),
            text = email
        )
    }
}

@Composable
fun PortraitLayout(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        InfoPanel(
            name = stringResource(R.string.author_name),
            info = stringResource(R.string.author_info)
        )
        ContactsPanel(
            phoneNumber = stringResource(R.string.phone_number),
            telegramLink = stringResource(R.string.telegram_link),
            email = stringResource(R.string.email)
        )
    }
}

@Composable
fun LandscapeLayout(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        InfoPanel(
            name = stringResource(R.string.author_name),
            info = stringResource(R.string.author_info)
        )
        ContactsPanel(
            phoneNumber = stringResource(R.string.phone_number),
            telegramLink = stringResource(R.string.telegram_link),
            email = stringResource(R.string.email)
        )
    }
}

@Composable
fun CardApp(windowSizeClass: WindowSizeClass) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            PortraitLayout()
        }

        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            LandscapeLayout()
        }
    }
}


@Preview(showBackground = true, device = Devices.TABLET)
@Composable
fun Lab1Preview() {
    Lab1MobileTheme {
        PortraitLayout()
    }
}