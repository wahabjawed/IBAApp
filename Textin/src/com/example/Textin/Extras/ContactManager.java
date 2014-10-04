package com.example.Textin.Extras;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Contacts.People;
import android.provider.Contacts.Phones;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

import com.example.Textin.Activities.IBApp;

public class ContactManager {

	static SQLiteDatabase db = IBApp.db;
	static Context context = IBApp.context;

	public static String getName(String number) {

		Cursor c = SQLHelper.getContactName(number);
		if (c.getCount() > 0) {
			c.moveToFirst();
			return c.getString(c.getColumnIndex("name"));
		}
		return number;
	}

	@SuppressWarnings("deprecation")
	public static void CopyContactList() {
		String contactNumber = null;
		int contactNumberType = Phone.TYPE_MOBILE;
		String nameOfContact = null;
	//	String[] projection = new String[] { "display_name", "has_phone_number" };
		ContentResolver cr = context.getContentResolver();
		Cursor cur = cr.query(Phones.CONTENT_URI, null, null, null, null);

	
		if (cur.getCount() > 0) {
			cur.moveToFirst();
			

			// for (int i = 0; i < cur.getCount(); i++) {
			// cur.moveToNext();
			// String id = cur.getString(cur.getColumnIndex(BaseColumns._ID));
			// nameOfContact = cur
			// .getString(cur
			// .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			//
			// if (Integer
			// .parseInt(cur.getString(cur
			// .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) >
			// 0) {
			//
			// Cursor phones = cr.query(
			// ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
			// null,
			// ContactsContract.CommonDataKinds.Phone.CONTACT_ID
			// + " = ?", new String[] { id }, null);
			//
			// while (phones.moveToNext()) {
			// contactNumber = phones.getString(phones
			// .getColumnIndex(Phone.NUMBER));
			// contactNumberType = phones.getInt(phones
			// .getColumnIndex(Phone.TYPE));
			// }
			// phones.close();
			// }

			for (int i = 0; i < cur.getCount(); i++) {

				nameOfContact = cur.getString(cur.getColumnIndex(People.NAME));
				contactNumber = cur
						.getString(cur.getColumnIndex(People.NUMBER));
				Log.d("con", "contact "+nameOfContact+" "+contactNumber);
				if ((nameOfContact!=null)&&(contactNumber!=null)&&(contactNumber.toString().length() == 11 || contactNumber
						.toString().length() == 13)
						&& nameOfContact.length() > 2) {

					if (contactNumber.toString().length() == 11) {
						contactNumber = "+92" + contactNumber.substring(1);
					}
					Log.i("con", "...Contact Name ...." + nameOfContact
							+ "...contact Number..." + contactNumber + "..."
							+ contactNumberType);

					ContentValues insertValues = new ContentValues();
					insertValues.put("number", contactNumber.trim());
					insertValues.put("name", nameOfContact.trim());
					insertValues.put("status", "Invite on IBApp");
					insertValues.put("isUser",0);
					
					//... // other fields
					long rowId = db.insert("Contacts", null, insertValues);
					
					
					
					
//					db.execSQL("INSERT INTO Contacts(number, name,status) VALUES('"
//							+ contactNumber.trim()
//							+ "','"
//							+ nameOfContact.trim() + "','Invite on IBAapp');");
				}
				cur.moveToNext();
			}
		}
		cur.close();

	}

}
