package com.ar.treedi.ui.theme

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.ar.treedi.models.TreeData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class SharedViewModel : ViewModel() {
    var treeData: TreeData? by mutableStateOf(null)
}