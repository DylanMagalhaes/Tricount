import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.vm.GroupViewModel


@Composable
fun UserDropdownMenu(vmGroup: GroupViewModel) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedUnit by rememberSaveable { mutableStateOf(0) }
    val groupState = vmGroup.uiState.collectAsState()
    Box(
        modifier = Modifier
            .height(25.dp)
            .clickable(onClick = { expanded = !expanded })
    ) {
        Row() {
            Text(
                text = groupState.value.listUser[selectedUnit].name,
                modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.padding(0.dp)
            )
        }
    }
    if (expanded) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            groupState.value.listUser.forEachIndexed {index, user ->
                if (index != selectedUnit) {
                    DropdownMenuItem(
                        onClick = {
                            selectedUnit = index
                            expanded = false
                        }
                    ) {
                        Text(
                            text = user.name,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}



