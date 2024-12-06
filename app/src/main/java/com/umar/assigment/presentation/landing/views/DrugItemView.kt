package com.umar.assigment.presentation.landing.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umar.assigment.domain.model.AssociatedDrug

@Composable
fun DrugItemView(drug : AssociatedDrug){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text("${drug.id}")
        }
        Box(modifier = Modifier.height(5.dp)) {  }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Name")
            Text("${drug.name}")
        }

        Box(modifier = Modifier.height(5.dp)) {  }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Strength")
            Text(if(drug.strength!=null )  "${drug.strength}" else "N/A")
        }

        Box(modifier = Modifier.height(5.dp)) {  }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Dose")
            Text(if(drug.dose!=null)  "${drug.dose}" else "N/A")
        }

        Box(modifier = Modifier.height(5.dp)) {  }

        Spacer( modifier = Modifier.fillMaxWidth().background(color = Color.Gray.copy(alpha = 0.5f)))

        Box(modifier = Modifier.height(5.dp)) {  }
    }
}



@Preview
@Composable
fun DrugItemViewPreview(){
    val associatedDrug = AssociatedDrug()
    associatedDrug.id = "AssociatedDrug#1"
    associatedDrug.dose = null
    associatedDrug.name = "Asprin"
    associatedDrug.strength= "600 mg"
    DrugItemView(associatedDrug)
}



