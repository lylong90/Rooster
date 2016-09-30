package com.blikoon.rooster;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gakwaya on 4/16/2016.
 */
public class ContactModel {

    private static ContactModel sContactModel;
    private List<Contact> mContacts;

    public static ContactModel get(Context context)
    {
        if(sContactModel == null)
        {
            sContactModel = new ContactModel(context);
        }
        return  sContactModel;
    }

    private ContactModel(Context context)
    {
        mContacts = new ArrayList<>();
        populateWithInitialContacts(context);

    }

    private void populateWithInitialContacts(Context context)
    {
        //Create the Foods and add them to the list;
        Contact contact1 = new Contact("zoza@izuba.tech");
        mContacts.add(contact1);
        Contact contact2 = new Contact("lylong1@xmpp.jp");
        mContacts.add(contact2);
        Contact contact3 = new Contact("User3@server.com");
        mContacts.add(contact3);
        Contact contact4 = new Contact("User4@server.com");
        mContacts.add(contact4);
    }

    public List<Contact> getContacts()
    {
        return mContacts;
    }

}
