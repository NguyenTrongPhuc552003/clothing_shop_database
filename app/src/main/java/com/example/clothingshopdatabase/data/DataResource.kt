package com.example.clothingshopdatabase.data

import com.example.clothingshopdatabase.R
import com.example.clothingshopdatabase.model.Product
import com.example.clothingshopdatabase.model.Size

object DataResource {
    val products = listOf(
        Product(1, "T1 World Champions 2024 T-Shirt", "Description", R.drawable.t1_2024_t_shirt_wchampions,1000000,
            listOf(Size.small,Size.medium,Size.large,Size.xlarge,Size.x2large,Size.x3large)
        ),
        Product(2, "T1 World Champions 2024 Jacket", "Description", R.drawable.jackett_t1_2024,3000000,
            listOf(Size.small,Size.medium,Size.large,Size.xlarge,Size.x2large,Size.x3large)
        ),
        Product(3, "T1 World Champions 2024 Jersey", "Description", R.drawable.jersey_t1_2024,2900000,
            listOf(Size.small,Size.medium,Size.large,Size.xlarge,Size.x2large,Size.x3large)
        )
    )
}