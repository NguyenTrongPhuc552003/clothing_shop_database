package com.example.clothingshopdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create Users Table
        db.execSQL(
            """CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_NAME TEXT NOT NULL,
                $COLUMN_USER_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_USER_PASSWORD TEXT NOT NULL,
                $COLUMN_USER_PHONE TEXT UNIQUE,
                $COLUMN_USER_ADDRESS TEXT
            )"""
        )

        // Create Categories Table
        db.execSQL(
            """CREATE TABLE $TABLE_CATEGORIES (
                $COLUMN_CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CATEGORY_NAME TEXT UNIQUE NOT NULL,
                $COLUMN_CATEGORY_DESCRIPTION TEXT
            )"""
        )

        // Create Products Table
        db.execSQL(
            """CREATE TABLE $TABLE_PRODUCTS (
                $COLUMN_PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PRODUCT_NAME TEXT NOT NULL,
                $COLUMN_PRODUCT_PRICE REAL NOT NULL,
                $COLUMN_PRODUCT_QUANTITY INTEGER NOT NULL,
                $COLUMN_PRODUCT_CATEGORY_ID INTEGER NOT NULL,
                $COLUMN_PRODUCT_IMAGE_URL TEXT,
                $COLUMN_PRODUCT_DESCRIPTION TEXT,
                FOREIGN KEY ($COLUMN_PRODUCT_CATEGORY_ID) REFERENCES $TABLE_CATEGORIES($COLUMN_CATEGORY_ID)
            )"""
        )

        // Create Orders Table
        db.execSQL(
            """CREATE TABLE $TABLE_ORDERS (
                $COLUMN_ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ORDER_USER_ID INTEGER NOT NULL,
                $COLUMN_ORDER_DATE TEXT NOT NULL,
                $COLUMN_ORDER_STATUS TEXT DEFAULT 'Pending',
                $COLUMN_ORDER_TOTAL_PRICE REAL NOT NULL,
                FOREIGN KEY ($COLUMN_ORDER_USER_ID) REFERENCES $TABLE_USERS($COLUMN_USER_ID)
            )"""
        )

        // Create Order Details Table
        db.execSQL(
            """CREATE TABLE $TABLE_ORDER_DETAILS (
                $COLUMN_ORDER_DETAIL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ORDER_DETAIL_ORDER_ID INTEGER NOT NULL,
                $COLUMN_ORDER_DETAIL_PRODUCT_ID INTEGER NOT NULL,
                $COLUMN_ORDER_DETAIL_QUANTITY INTEGER NOT NULL,
                $COLUMN_ORDER_DETAIL_PRICE REAL NOT NULL,
                FOREIGN KEY ($COLUMN_ORDER_DETAIL_ORDER_ID) REFERENCES $TABLE_ORDERS($COLUMN_ORDER_ID),
                FOREIGN KEY ($COLUMN_ORDER_DETAIL_PRODUCT_ID) REFERENCES $TABLE_PRODUCTS($COLUMN_PRODUCT_ID)
            )"""
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER_DETAILS")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "clothing_shop.db"
        const val DATABASE_VERSION = 1

        // Table Names
        const val TABLE_USERS = "users"
        const val TABLE_CATEGORIES = "categories"
        const val TABLE_PRODUCTS = "products"
        const val TABLE_ORDERS = "orders"
        const val TABLE_ORDER_DETAILS = "order_details"

        // Users Table Columns
        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_EMAIL = "email"
        const val COLUMN_USER_PASSWORD = "password"
        const val COLUMN_USER_PHONE = "phone"
        const val COLUMN_USER_ADDRESS = "address"

        // Categories Table Columns
        const val COLUMN_CATEGORY_ID = "id"
        const val COLUMN_CATEGORY_NAME = "name"
        const val COLUMN_CATEGORY_DESCRIPTION = "description"

        // Products Table Columns
        const val COLUMN_PRODUCT_ID = "id"
        const val COLUMN_PRODUCT_NAME = "name"
        const val COLUMN_PRODUCT_PRICE = "price"
        const val COLUMN_PRODUCT_QUANTITY = "quantity"
        const val COLUMN_PRODUCT_CATEGORY_ID = "category_id"
        const val COLUMN_PRODUCT_IMAGE_URL = "image_url"
        const val COLUMN_PRODUCT_DESCRIPTION = "description"

        // Orders Table Columns
        const val COLUMN_ORDER_ID = "id"
        const val COLUMN_ORDER_USER_ID = "user_id"
        const val COLUMN_ORDER_DATE = "order_date"
        const val COLUMN_ORDER_STATUS = "status"
        const val COLUMN_ORDER_TOTAL_PRICE = "total_price"

        // Order Details Table Columns
        const val COLUMN_ORDER_DETAIL_ID = "id"
        const val COLUMN_ORDER_DETAIL_ORDER_ID = "order_id"
        const val COLUMN_ORDER_DETAIL_PRODUCT_ID = "product_id"
        const val COLUMN_ORDER_DETAIL_QUANTITY = "quantity"
        const val COLUMN_ORDER_DETAIL_PRICE = "price"
    }
}
