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

    val icons = mapOf<IconType, ImageVector>(
        IconType.Generic to Icons.Default.Category,
        IconType.ShoppingCart to Icons.Default.ShoppingCart,
        IconType.Food to Icons.Default.Restaurant,
        IconType.Travel to Icons.Default.Flight,
        IconType.Home to Icons.Default.Home,
        IconType.Health to Icons.Default.MedicalServices,
        IconType.Education to Icons.Default.School,
        IconType.Entertainment to Icons.Default.Movie,
        IconType.Savings to Icons.Default.Savings,
        IconType.Salary to Icons.Default.AttachMoney,
        IconType.Gift to Icons.Default.CardGiftcard,
        IconType.Subscription to Icons.Default.Subscriptions,
        IconType.Pet to Icons.Default.Pets,
    )

    fun format(iconType: IconType): ImageVector {
        return icons[iconType] ?: Icons.Default.Category
    }
}
