package components.list.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.style.LocalAreaColors
import io.kanro.compose.jetbrains.expui.style.LocalInactiveAreaColors

@Composable
internal fun DefaultListItemBody(
    title: String,
    subtitle: String?
) {
    Column {
        Label(
            text = title,
            fontSize = 14.sp
        )

        subtitle?.let { subtitle ->
            Label(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = subtitle,
                fontSize = 12.sp,
                color = LocalInactiveAreaColors.current.text
            )
        }
    }
}
