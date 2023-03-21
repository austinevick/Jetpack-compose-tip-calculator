package com.example.tipsy.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipsy.ui.theme.primaryColor

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(value = value, onValueChange = onValueChange,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
        leadingIcon = { Icon(imageVector = Icons.Default.AttachMoney, contentDescription = "")},
       )
}

@Composable
fun HintText(text: String) {
    Text(text, fontSize = 20.sp, color = Color.Gray)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BuildTipChip(text: String, isSelected: String, onTap: (String) -> Unit) {
    Chip(
        onClick = {onTap(text)},
        colors = if (isSelected==text) ChipDefaults.chipColors(backgroundColor =
        Color(0xff16a464)) else
            ChipDefaults.chipColors(backgroundColor = Color(0xff1d212b)),
        modifier = Modifier
            .height(50.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp),

        ) {
        Row {
            AnimatedVisibility(visible = isSelected==text) {
                Icon(imageVector = Icons.Default.Check,
                    contentDescription = "check icon", tint = Color.White)
            }
            Text(
                text = text, fontSize = 18.sp,
                color = if (isSelected.contains(text)) Color.White else Color.Green,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}


@Composable
fun CustomIconButton(
    incrementValue: () -> Unit,
    decrementValue: () -> Unit,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically

    ) {
        IconButton(
            onClick = decrementValue
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove, contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = primaryColor
            )
        }
        Text(
            text = value, textAlign = TextAlign.Center,
            color = primaryColor,
            fontSize = 30.sp, fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = incrementValue
        ) {
            Icon(
                imageVector = Icons.Rounded.Add, contentDescription = "",
                modifier = Modifier.size(30.dp), tint = primaryColor

            )
        }
    }
}

@Composable
fun BuildResultContainer(
    totalBillPerPerson: Double=0.0,
    bill: String,
    tip:String
) {
    var formattedNumber = "%.2f".format(totalBillPerPerson)
    Card(
        modifier = Modifier
            .fillMaxHeight(),
        shape = RoundedCornerShape(
            topEnd = 16.dp, topStart = 16.dp),
        backgroundColor = Color(0xffcef5e6)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Total per person",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = "$$formattedNumber",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
            Spacer(modifier = Modifier.height(100.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text("bill", fontSize = 20.sp, fontWeight = FontWeight.W600, color = Color.Gray)
                Text("tip", fontSize = 20.sp, fontWeight = FontWeight.W600, color = Color.Gray)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("$$bill", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = primaryColor)
                Text("$$tip", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = primaryColor)
            }
        }
    }
}


