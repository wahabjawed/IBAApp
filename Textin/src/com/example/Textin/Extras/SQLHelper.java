package com.example.Textin.Extras;

import com.example.Textin.Activities.IBApp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLHelper {
	static SQLiteDatabase db = IBApp.db;

	public static void SetupSQL(Context context) {

	}

	public static Cursor getContactName(String number) {

		return db.rawQuery("Select * from Contacts where TRIM(number) like '"
				+ number.trim() + "' ", null);

	}

	public static Cursor getMessages() {
		// return db.rawQuery("Select * from Messages", null);

		return db.query(true, "Messages", new String[] { "sender", "message" },
				null, null, "sender", null, null, null);
	}

	public static Cursor getMessages(String no) {
		return db.rawQuery("Select * from Messages where TRIM(receiver) like '"
				+ no.trim() + "' or TRIM(sender) like '" + no.trim() + "' ",
				null);
	}

	public static Cursor getContacts() {
		return db.rawQuery("Select DISTINCT(name),number,status,isUser from Contacts order by name",null);
	}

		
	public static String getUser() {
		Cursor c = db.rawQuery("Select * from User", null);
		if (c.getCount() > 0) {
			c.moveToFirst();

			return c.getString(c.getColumnIndex("phone"));
		} else {

			// db.execSQL("INSERT INTO User VALUES('+923322276516');");
			// return "+923322276516";

			return "";
		}
	}

}
