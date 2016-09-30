package com.blikoon.rooster.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 30/9/2016.
 */
public class AppPreferences
{
    private static SharedPreferences getDefaultSharedPreferences(Context _context)
    {
        return PreferenceManager.getDefaultSharedPreferences(_context);
    }

    public static boolean getBoolean(Context _context, String _key, boolean _defValue)
    {
        return getDefaultSharedPreferences(_context).getBoolean(_key, _defValue);
    }

    public static String getString(Context _context, String _key, String _defValue)
    {
        return getDefaultSharedPreferences(_context).getString(_key, _defValue);
    }

    public static void putBoolean(Context _context, String _key, boolean _value)
    {
        getDefaultSharedPreferences(_context).edit()
                .putBoolean(_key, _value)
                .apply();
    }

    public static void putString(Context _context, String _key, String _value)
    {
        getDefaultSharedPreferences(_context).edit()
                .putString(_key, _value)
                .apply();
    }
}
