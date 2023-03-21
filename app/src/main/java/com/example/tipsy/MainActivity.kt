package com.example.tipsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Flip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipsy.components.*
import com.example.tipsy.ui.theme.TipsyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipsyTheme {
                TipsyApp()
            }
        }
    }
}

@Composable
fun TipsyApp() {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color(0xffcef5e6)
        ) {
            Text(
                text = "Tipsy", fontWeight = FontWeight.Bold,
                color = Color(0xff013d29), fontSize = 24.sp
            )
        }
    }) {
        it.calculateTopPadding()
        Body()
    }
}

@Composable
fun Body() {
    val chips = listOf("5", "10", "15", "20")
    val totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    val totalBillValue = remember {
        mutableStateOf("0")
    }
    val tipPercentage = remember {
        mutableStateOf("0")
    }

    val splitValue = remember {
        mutableStateOf(1)
    }
    val tipAmountState = remember {
        mutableStateOf(0.0)
    }


    fun calculateTotalTip(): Double {
        return if (totalBillValue.value.toDouble() > 1 &&
            totalBillValue.value.isNotEmpty()
        )
            (totalBillValue.value.toDouble() * tipPercentage.value.toInt()) / 100 else 0.0
    }

    fun calculateTotalPerPerson(

    ): Double {
        val bill = calculateTotalTip() / totalBillValue.value.toDouble()
        return (bill / splitValue.value)
    }


    Column(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)

    ) {
        HintText("Enter bill")
        InputField(value = totalBillValue.value, onValueChange = {
            totalBillValue.value = it

        })
        Spacer(modifier = Modifier.height(14.dp))
        HintText(text = "Choose tip (%)")

        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            chips.forEach {
                BuildTipChip(text = it, isSelected = tipPercentage.value, onTap = { value ->
                    tipPercentage.value = value
                    tipAmountState.value = calculateTotalTip()
                    totalPerPerson.value = calculateTotalPerPerson()
                })
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        HintText("Split")
        Spacer(modifier = Modifier.height(6.dp))

        CustomIconButton(
            incrementValue = {
                splitValue.value++
                totalPerPerson.value = calculateTotalPerPerson()
            },
            decrementValue = {
                if (splitValue.value == 1) return@CustomIconButton
                splitValue.value--
                totalPerPerson.value = calculateTotalPerPerson()
            }, value = "${splitValue.value}"
        )
        Spacer(modifier = Modifier.height(10.dp))

        BuildResultContainer(
            totalBillPerPerson = totalPerPerson.value,
            bill = totalBillValue.value,
            tip = tipAmountState.value.toString()
        )
    }
}


