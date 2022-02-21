package com.example.project;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;


public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "Houses";
    public static final int DATABASE_VERSION = 1;
    public static int houses = 0;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Rental(ID String PRIMARY KEY,Name TEXT,Password TEXT,Country TEXT, City TEXT, Phone TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE Tenant(ID String PRIMARY KEY,firstName TEXT,lastName TEXT,Gender TEXT,Password TEXT,Nationality TEXT,Occupation TEXT,Size TEXT, Country TEXT, City TEXT, Phone TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE tenantHouses (houseID INTEGER,email TEXT, status TEXT,Sdate TEXT,Edate TEXT,Notificaiton TEXT,FOREIGN KEY (houseID) REFERENCES Houses (ID) ON DELETE CASCADE, FOREIGN KEY (email) REFERENCES Tenant (ID) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE Houses(ID INTEGER  PRIMARY KEY AUTOINCREMENT,City TEXT,address TEXT,area TEXT,year TEXT, bedrooms TEXT,price TEXT,status TEXT,garden TEXT, balcony Text,date TEXT, description TEXT ,Owner TEXT,OwnName TEXT, bitmap TEXT,FOREIGN KEY (Owner) REFERENCES Rental (ID) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void insertTenant(Tenants tenant) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
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
        long l = sqLiteDatabase.insert("Tenant", null, contentValues);
        if (l != -1) {
            Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertRental(Rentals rentals) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", rentals.getRentalId());
        contentValues.put("Name", rentals.getRentalName());
        contentValues.put("Password", rentals.getRentalPassword());
        contentValues.put("Country", rentals.getRentalCountry());
        contentValues.put("City", rentals.getRentalCity());
        contentValues.put("Phone", rentals.getRentalPhone());
        sqLiteDatabase.insert("Rental", null, contentValues);

    }

    public void editRental(Rentals rentals) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", rentals.getRentalId());
        contentValues.put("Name", rentals.getRentalName());
        contentValues.put("Password", rentals.getRentalPassword());
        contentValues.put("Country", rentals.getRentalCountry());
        contentValues.put("City", rentals.getRentalCity());
        contentValues.put("Phone", rentals.getRentalPhone());
        long l = sqLiteDatabase.update("Rental", contentValues, "ID = ?", new String[]{rentals.getRentalId()});
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void editTenant(Tenants tenant) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
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
        long l = sqLiteDatabase.update("Tenant", contentValues, "ID = ?", new String[]{tenant.getTenantId()});
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Edited successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllRentals() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT ID,Password FROM Rental", null);
    }

    public Cursor getAllTenants() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Tenant", null);
    }

    public Cursor getAllHouses() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Houses ORDER BY ID", null);
        return cursor;
    }

    public Cursor getRequests() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tenantHouses", null);
        return cursor;
    }

    public Boolean checkT(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        System.out.println(email + password);
        Cursor cursor = MyDB.rawQuery("SELECT * from Tenant where ID  = ? and password = ?", new String[]{email, Encrypter.EncrypterData512(password)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            SignIn.tenant = new Tenants(cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("firstName")),
                    cursor.getString(cursor.getColumnIndex("lastName")),
                    cursor.getString(cursor.getColumnIndex("Gender")),
                    cursor.getString(cursor.getColumnIndex("Password")),
                    cursor.getString(cursor.getColumnIndex("Nationality")),
                    cursor.getString(cursor.getColumnIndex("Occupation")),
                    cursor.getString(cursor.getColumnIndex("Size")),
                    cursor.getString(cursor.getColumnIndex("Country")),
                    cursor.getString(cursor.getColumnIndex("City")),
                    cursor.getString(cursor.getColumnIndex("Phone")));
            String l = SignIn.tenant.getTenantId();
            Cursor c = MyDB.rawQuery("Select Notificaiton from tenantHouses", null);
            if (l.length() < 3) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;

    }

    public Boolean checkA(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from Rental where ID  = ? and password = ?", new String[]{email, Encrypter.EncrypterData512(password)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            SignIn.rental = new Rentals(cursor.getString(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Password")),
                    cursor.getString(cursor.getColumnIndex("Country")),
                    cursor.getString(cursor.getColumnIndex("City")),
                    cursor.getString(cursor.getColumnIndex("Phone")));
            return true;
        } else
            return false;
    }


    //    ID String PRIMARY KEY, City TEXT,address TEXT,area TEXT,year TEXT," +
//            "bedrooms TEXT,price TEXT,status TEXT,date TEXT, description TEXT ,Owner String,Tenant String)
    public boolean insertHouse(House house) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String garden;
        String balcony;
        if (house.isHasgarden()) {
            garden = "true";
        } else {
            garden = "false";
        }
        if (house.isHasbalcone()) {
            balcony = "true";
        } else {
            balcony = "false";
        }
        contentValues.put("City", house.getCity());
        contentValues.put("address", house.getAddress());
        contentValues.put("area", house.getSurface());
        contentValues.put("year", house.getYear());
        contentValues.put("bedrooms", house.getNumOfbedrooms());
        contentValues.put("price", house.getPrice());
        contentValues.put("status", house.getStatus());
        contentValues.put("garden", garden);
        contentValues.put("balcony", balcony);
        contentValues.put("date", house.getDate());
        contentValues.put("description", house.getDescription());
        contentValues.put("Owner", house.getOwner());
        contentValues.put("OwnName", house.getOwnerName());
        contentValues.put("bitmap", house.getBitmap());

        long l = MyDB.insert("Houses", null, contentValues);
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(context, "Inserted successfully", Toast.LENGTH_SHORT).show();
        return true;

    }

    public void editHouse(House house) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("City", house.getCity());
        contentValues.put("address", house.getAddress());
        contentValues.put("area", house.getSurface());
        contentValues.put("year", house.getYear());
        contentValues.put("bedrooms", house.getNumOfbedrooms());
        contentValues.put("price", house.getPrice());
        contentValues.put("status", house.getStatus());
        contentValues.put("garden", house.getStatus());
        contentValues.put("balcony", house.getStatus());
        contentValues.put("date", house.getDate());
        contentValues.put("OwnName", house.getOwnerName());
        long l = sqLiteDatabase.update("Houses", contentValues, "ID =" + house.getId(), null);
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Edited successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteHouse(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Houses", "ID = ?", new String[]{id + ""});
        sqLiteDatabase.delete("tenantHouses", "houseID = ?", new String[]{id + ""});

    }

    public Cursor get5recentHouses() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT h.ID FROM Houses h , tenantHouses t WHERE h.ID = t.houseID AND t.status = 'false' ORDER BY ID DESC LIMIT 5", null);
        return cursor;
    }

    public Cursor getMyHouses(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Houses where Owner  = ? ", new String[]{email});
        return cursor;
    }

    public Cursor request(int id, String Sdate, String Edate, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tenantHouses where houseID = " + id, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("status", "wait");
        contentValues.put("Sdate", Sdate);
        contentValues.put("Edate", Edate);
        contentValues.put("Notificaiton", "ten");
        contentValues.put("email", email);
        contentValues.put("houseID", id);


        long l = sqLiteDatabase.update("tenantHouses", contentValues, "houseID =" + id + " AND status = ?", new String[]{"false"});
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "House Rented", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    public void updater(int id, String email, String status) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);
        if (status.equals("true"))
            contentValues.put("Notificaiton", "ren");
        else
            contentValues.put("Notificaiton", "false");
        long l = sqLiteDatabase.update("tenantHouses", contentValues, "houseID =" + id + " and email = ?", new String[]{email});
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Edited successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean insertHousePrp(House newHouse) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tenantHouses Where houseID  =" + newHouse.getId(), null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("status", "false");
        contentValues.put("Notificaiton", "false");
        contentValues.put("houseID", newHouse.getId());

        long l = sqLiteDatabase.insert("tenantHouses", null, contentValues);
        if (l == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "House inserted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public Cursor getAvHouses() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT houseID FROM tenantHouses  WHERE status = 'false' ORDER BY houseID", null);
        return cursor;
    }

    public Cursor getNotification(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tenantHouses, Houses WHERE Houses.Owner = ? AND Houses.ID =tenantHouses.houseID AND tenantHouses.Notificaiton = 'ten' AND tenantHouses.status = 'wait'", new String[]{email});
        return cursor;
    }

    public void setNotification(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor1 = getNotification(email);

        if (cursor1.moveToFirst())
            sqLiteDatabase.execSQL("UPDATE tenantHouses SET Notificaiton = 'false' WHERE houseID = " + cursor1.getInt(cursor1.getColumnIndex("houseID")) + "");

        while (cursor1.moveToNext()) {
            sqLiteDatabase.execSQL("UPDATE tenantHouses SET Notificaiton = 'false' WHERE houseID = " + cursor1.getInt(cursor1.getColumnIndex("houseID")) + "");
        }

    }

    public Cursor getNotificationTen(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tenantHouses WHERE email = ? AND tenantHouses.Notificaiton = 'ren' AND tenantHouses.status = 'true'", new String[]{email});
        return cursor;
    }

    public void setNotificationTen(String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor1 = getNotificationTen(email);

        if (cursor1.moveToFirst())
            sqLiteDatabase.execSQL("UPDATE tenantHouses SET Notificaiton = 'false' WHERE houseID = " + cursor1.getInt(cursor1.getColumnIndex("houseID")) + "");

        while (cursor1.moveToNext()) {
            sqLiteDatabase.execSQL("UPDATE tenantHouses SET Notificaiton = 'false' WHERE houseID = " + cursor1.getInt(cursor1.getColumnIndex("houseID")) + "");
        }

    }


    public Cursor getAllHousesAva() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT h.ID,h.City,h.address,h.area,h.year,h.bedrooms,h.price,h.status,h.garden,h.balcony,h.date,h.description,h.Owner,h.OwnName,h.bitmap FROM Houses h, tenantHouses t WHERE t.houseID = h.ID AND t.status ='false' ORDER BY h.ID",null );
    return cursor;
    }
}
