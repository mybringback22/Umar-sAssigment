package com.umar.assigment.presentation.landing.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umar.assigment.domain.model.AssociatedDrug
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.ui.theme.Typography


@Composable
fun ProblemItem(
    problem: Problem,
    onClick: (Problem) -> Unit

) {
    Box(
        modifier = Modifier
            .clickable {
                onClick(problem)
            }
            .padding(10.dp)
            .fillMaxWidth()
            .border(
                color = Color.Gray.copy(alpha = 0.5f),
                width = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
    ) {

        Column(modifier = Modifier.fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally) {
            Text("${problem.problemName}" , style = Typography.bodyLarge.copy(color= Color.Black , fontWeight = FontWeight.Bold))

            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("ClassName - ")
                Text(if(problem.className !=null) "${problem.className}" else "N/A"  , style = Typography.bodyLarge.copy(color= Color.Black , fontSize = 14.sp))
            }


            if(problem.associatedDrugs.isNotEmpty()){
                problem.associatedDrugs.forEach {drug ->
                    DrugItemView(drug)
                }
            }else{
                Text("No Associated Drugs")
            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProblemItemPreview() {
    ProblemItem(
        Problem(id = 0, problemName = "Asthma", className = "ClassName2"),
        onClick = {

        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProblemItemPreviewWithDrugs() {
    val associatedDrugs = mutableListOf<AssociatedDrug>()
    val associatedDrug = AssociatedDrug()
    associatedDrug.id = "AssociatedDrug#1"
    associatedDrug.dose = "2"
    associatedDrug.name = "Asprin"
    associatedDrug.strength= "600 mg"
    associatedDrugs.add(associatedDrug)
    val associatedDrugTwo = AssociatedDrug()
    associatedDrugTwo.id = "AssociatedDrug#2"
    associatedDrugTwo.dose = "3"
    associatedDrugTwo.name = "Telenol"
    associatedDrugTwo.strength= "600 mg"
    associatedDrugs.add(associatedDrugTwo)
    ProblemItem(Problem(id = 0, problemName = "Asthma", className = "ClassName2" , associatedDrugs = associatedDrugs) , onClick = {})
}