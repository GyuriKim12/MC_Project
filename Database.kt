package com.example.waru

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class Database {
    object DBContract {
        object Entry : BaseColumns {
            const val table_name1 = "Dairy_table"
            const val table_name2 = "texts_table"
            const val table_name3 = "comment_table"
            const val year = "year"
            const val date = "date"
            const val score = "score"
            const val texts = "texts"
            const val comment = "comments"
            const val t_id = "t_id"
            const val c_id = "c_id"
        }
    }

    class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "myDBfile.db"
        }

        override fun onCreate(db: SQLiteDatabase) {
            val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${DBContract.Entry.table_name1} (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                        "${DBContract.Entry.year} INTEGER," +
                        "${DBContract.Entry.date} INTEGER," +
                        "${DBContract.Entry.score} INTEGER)"

            val TEXTS_TABLE =
                "CREATE TABLE ${DBContract.Entry.table_name2} (" +
                        "${DBContract.Entry.texts} TEXT," +
                        "${DBContract.Entry.t_id} INTEGER," +
                        "FOREIGN KEY (${DBContract.Entry.t_id}) REFERENCES ${DBContract.Entry.table_name1}(${BaseColumns._ID}))"

            val COMMENT_TABLE =
                "CREATE TABLE ${DBContract.Entry.table_name3} (" +
                        "${DBContract.Entry.comment} TEXT," +
                        "${DBContract.Entry.c_id} INTEGER," +
                        "FOREIGN KEY (${DBContract.Entry.c_id}) REFERENCES ${DBContract.Entry.table_name1}(${BaseColumns._ID}))"

            db.execSQL(SQL_CREATE_ENTRIES)
            db.execSQL(TEXTS_TABLE)
            db.execSQL(COMMENT_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBContract.Entry.table_name1}"

            db.execSQL(SQL_DELETE_ENTRIES)
            onCreate(db)
        }


        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(db, oldVersion, newVersion)
        }
    }
}
