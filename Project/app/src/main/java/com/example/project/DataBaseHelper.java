package com.example.project;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Rental(ID String PRIMARY KEY,Name TEXT,Password TEXT,Country TEXT, City TEXT, Phone TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE Tenant(ID String PRIMARY KEY,firstName TEXT,lastName TEXT,Gender TEXT,Password TEXT,Nationality TEXT,Occupation TEXT,Size TEXT, Country TEXT, City TEXT, Phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertTenant(Tenants tenant) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", tenant.getTenantId());
        contentValues.put("firstName", tenant.getTenantFirstName());
        contentValues.put("lastName", tenant.getTenantLastName());
        contentValues.put("Gender", tenant.getTenantGender());
        contentValues.put("Password", tenant.getTenantPassword());
        contentValues.put("Nationality", tenant.getTenantNationality());
        contentValues.put("Occupation", tenant.getTenantOccupation());
        contentValues.put("Size", tenant.getTenantSize());
        contentValues.put("Country", tenant.getTenantCountry());
        contentValues.put("City", tenant.getTenantCity());
        contentValues.put("Phone", tenant.getTenantPhone());
        sqLiteDatabase.insert("Tenant", null, contentValues);
    }
    public void insertRental(Rentals rentals) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", rentals.getRentalId());
        contentValues.put("Name", rentals.getRentalName());
        contentValues.put("Password", rentals.getRentalPassword());
        contentValues.put("Country", rentals.getRentalCountry());
        contentValues.put("City", rentals.getRentalCity());
        contentValues.put("Phone", rentals.getRentalPhone());
        sqLiteDatabase.insert("Rental", null, contentValues);
    }
    public Cursor getAllRentals() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Rental", null);
    }
    public Cursor getAllTenants() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Tenant", null);
    }
}
