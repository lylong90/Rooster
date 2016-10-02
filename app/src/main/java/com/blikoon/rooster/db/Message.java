package com.blikoon.rooster.db;

/**
 * Created by User on 30/9/2016.
 */
public class Message
{
    private long id;
    private String message;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return message;
    }
}
