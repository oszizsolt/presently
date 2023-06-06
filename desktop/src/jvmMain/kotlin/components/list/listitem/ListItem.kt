package components.list.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kanro.compose.jetbrains.expui.control.Label

enum class SelectedState {
    NotSelected,
    PrimarySelected,
    SecondarySelected,
    TernarySelected,
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    title: String,
    subtitle: String?
) {
    ListItem(
        selectedState = selectedState,
        onClick = onClick,
        modifier = modifier,
    ) {
        DefaultListItemBody(
            title = title,
            subtitle = subtitle,
        )
    }
}


@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    selectedState: SelectedState = SelectedState.NotSelected,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    // TODO:
    // hover state
    // add padding


}