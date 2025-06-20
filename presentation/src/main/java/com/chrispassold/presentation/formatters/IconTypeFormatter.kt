package com.chrispassold.presentation.formatters

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.ui.graphics.vector.ImageVector
import com.chrispassold.domain.models.IconType

object IconTypeFormatter {

    fun format(iconType: IconType): ImageVector {
        return when (iconType) {
            IconType.Generic -> Icons.Default.Category
            IconType.ShoppingCart -> Icons.Default.ShoppingCart
            IconType.Food -> Icons.Default.Restaurant
            IconType.Travel -> Icons.Default.Flight
            IconType.Home -> Icons.Default.Home
            IconType.Health -> Icons.Default.MedicalServices
            IconType.Education -> Icons.Default.School
            IconType.Entertainment -> Icons.Default.Movie
            IconType.Savings -> Icons.Default.Savings
            IconType.Salary -> Icons.Default.AttachMoney
            IconType.Gift -> Icons.Default.CardGiftcard
            IconType.Subscription -> Icons.Default.Subscriptions
            IconType.Pet -> Icons.Default.Pets
        }
    }
}
