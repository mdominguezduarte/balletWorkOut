package com.example.balletworkout


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteOpenHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {


    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_HISTORY_BALLET = ("CREATE TABLE " +
                TABLE_BALLET + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_COMPLETED_DATE
                + " TEXT" + ")")
        db.execSQL(CREATE_HISTORY_BALLET) //
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BALLET)
        onCreate(db)
    }


    fun addDate(date: String) {
        val values =
            ContentValues()
        values.put(
            COLUMN_COMPLETED_DATE,
            date
        )
        val db =
            this.writableDatabase
        db.insert(TABLE_BALLET, null, values)
        db.close()
    }

    /**
     * Function returns the list of history table data.
     */
    fun getAllCompletedDatesList(): ArrayList<String> {
        val list = ArrayList<String>() // ArrayList is initialized
        val db =
            this.readableDatabase // Create and/or open a database that will be used for reading and writing.

        val cursor = db.rawQuery("SELECT * FROM $TABLE_BALLET", null)


        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE))) // value is added in the list
        }
        cursor.close()
        return list
    }

    companion object {
        private const val DATABASE_VERSION = 1 // This DATABASE Version
        private const val DATABASE_NAME = "BalletClass.db" // Name of the DATABASE
        private const val TABLE_BALLET = "ballet" // Table Name
        private const val COLUMN_ID = "_id" // Column Id
        private const val COLUMN_COMPLETED_DATE = "completed_date"
    }
}