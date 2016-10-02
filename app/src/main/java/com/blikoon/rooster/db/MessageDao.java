package com.blikoon.rooster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30/9/2016.
 */
public class MessageDao
{
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_MESSAGE };

    public MessageDao(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Message createMessage(String message)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_MESSAGE, message);
        long insertId = database.insert(MySQLiteHelper.TABLE_MESSAGES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES, allColumns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Message newMessage = cursorToMessage(cursor);
        cursor.close();
        return newMessage;
    }

    public void deleteMessage(Message message)
    {
        long id = message.getId();
        database.delete(MySQLiteHelper.TABLE_MESSAGES, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Message> getAllMessages()
    {
        List<Message> messages = new ArrayList<Message>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MESSAGES, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Message message = cursorToMessage(cursor);
            messages.add(message);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return messages;
    }

    private Message cursorToMessage(Cursor cursor)
    {
        Message message = new Message();
        message.setId(cursor.getLong(0));
        message.setMessage(cursor.getString(1));
        return message;
    }
}
