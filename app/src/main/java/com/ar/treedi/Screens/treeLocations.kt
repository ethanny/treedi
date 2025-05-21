package com.ar.treedi.Screens


import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Alignment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ar.treedi.Components.IconButton
import com.ar.treedi.ui.theme.accentGreen
import com.ar.treedi.ui.theme.primaryGreen
import com.ar.treedi.ui.theme.AppTypography.b1
import com.ar.treedi.ui.theme.AppTypography.b2
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.AppTypography.h3

data class TreeLocation(
    val location: String,
    val treeName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeLocations(
    navController: NavController,
) {
    val treeLocations = listOf(
        TreeLocation("In front of DBES", "Flame tree"),
        TreeLocation("Near the tennis/volleyball court", "Kalachuchi"),
        TreeLocation("Near the oblation square", "Rain Tree"),
        TreeLocation("Near the canteen", "Golden shower tree"),
        TreeLocation("Near the Admin Building", "Tambis\nYlang-ylang")
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFB8D1CA))
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(vertical = 10.dp, horizontal = 20.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(Lucide.ArrowLeft, { navController.popBackStack() })

                    Text(
                        "Tree Locations",
                        color = primaryGreen,
                        style = h2.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                }
            }
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp)
        ) {
            items(treeLocations) { treeLocation ->
                TreeLocationItem(treeLocation)
            }
        }
    }
}

@Composable
fun TreeLocationItem(treeLocation: TreeLocation) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = treeLocation.location,
            style = h3,
            color = primaryGreen,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = treeLocation.treeName,
            style = b2,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TreeLocationsScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        Surface {
            TreeLocations(navController)
        }
    }
}