package com.example.techdrive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBVehicle extends SQLiteOpenHelper {
    public DBVehicle(Context context) {
        super(context, "Vehicle.db", null, 8);
    }


    @Override
    public void onCreate(SQLiteDatabase DBVehicle) {
        DBVehicle.execSQL("create Table Vehicle(vehiclenumber TEXT primary key, vehicletype TEXT, vehiclemodel TEXT, chassisnumber TEXT, enginenumber TEXT, fueltype TEXT)");
        DBVehicle.execSQL("create Table Maintain(vehiclenum TEXT primary key, vehicleMtype TEXT, maintaindate TEXT, nextmaintaindate TEXT, whatismaintain TEXT, itemofmaintain TEXT, maintanedprice TXT)");
        DBVehicle.execSQL("create Table Repaire(vehiclenumb TEXT primary key, vehicleRtype TEXT, repairedate TEXT, nextrepairedate TEXT, whatisrepaire TEXT, itemofrepair TEXT, repaireprice TXT)");
        DBVehicle.execSQL("create Table Fuel(vehiclenb TEXT primary key, vehicleFtype TEXT, fueltype TEXT, dateoffuel TEXT, miloffueltime TEXT, litergo TEXT, avgdis TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DBVehicle, int i, int i1) {
        DBVehicle.execSQL("drop Table if exists Vehicle");
        DBVehicle.execSQL("drop Table if exists Maintain");
        DBVehicle.execSQL("drop Table if exists Repaire");
        DBVehicle.execSQL("drop Table if exists Fuel");
        onCreate(DBVehicle);
    }


    public Boolean insertvehicledata(String vehiclenumber,String vehicletype,String vehiclemodel,String enginenumber,String chassisnumber,String fueltype)
    {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehiclenumber", vehiclenumber);
        contentValues.put("vehicletype", vehicletype);
        contentValues.put("vehiclemodel", vehiclemodel);
        contentValues.put("chassisnumber", chassisnumber);
        contentValues.put("enginenumber", enginenumber);
        contentValues.put("fueltype", fueltype);
        long result=DBVehicle.insert("Vehicle", null, contentValues);
        if (result == -1){
            return false;
        }
        else
        {
            return true;
        }
    }


    public Boolean deletevehicledata(String vehiclenumber) {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        Cursor cursor = DBVehicle.rawQuery("Select * from Vehicle where vehiclenumber=?", new String[]{vehiclenumber});
        if (cursor.getCount() > 0)
        {
            long result = DBVehicle.delete("Vehicle", "vehiclenumber=?", new String[]{vehiclenumber});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Vehicle", null);
        return  cursor;
    }

    public Boolean insertMaintaindata(String vehiclenum, String vehicleMtype, String maintaindate, String nextmaintaindate, String whatismaintain, String itemofmaintain, String maintanedprice) {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehiclenum", vehiclenum);
        contentValues.put("vehicleMtype", vehicleMtype);
        contentValues.put("maintaindate", maintaindate);
        contentValues.put("nextmaintaindate", nextmaintaindate);
        contentValues.put("whatismaintain", whatismaintain);
        contentValues.put("itemofmaintain", itemofmaintain);
        contentValues.put("maintanedprice", maintanedprice);
        long result = DBVehicle.insert("Maintain", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean deletemaintaindata(String vehiclenum) {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        Cursor cursor = DBVehicle.rawQuery("Select * from Maintain where vehiclenum=?", new String[]{vehiclenum});
        if (cursor.getCount() > 0)
        {
            long result = DBVehicle.delete("Maintain", "vehiclenum=?", new String[]{vehiclenum});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getmaintaindata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Maintain", null);
        return  cursor;
    }

    public Boolean insertrepairedata(String vehiclenumb,String vehicleRtype,String repairedate,String nextrepairedate,String whatisrepaire,String itemofrepair,String repaireprice)
    {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehiclenumb", vehiclenumb);
        contentValues.put("vehicleRtype", vehicleRtype);
        contentValues.put("repairedate", repairedate);
        contentValues.put("nextrepairedate", nextrepairedate);
        contentValues.put("whatisrepaire", whatisrepaire);
        contentValues.put("itemofrepair", itemofrepair);
        contentValues.put("repaireprice", repaireprice);
        long result=DBVehicle.insert("Repaire", null, contentValues);
        if (result == -1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean deleterepairedata(String vehiclenumb) {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        Cursor cursor = DBVehicle.rawQuery("Select * from Repaire where vehiclenumb=?", new String[]{vehiclenumb});
        if (cursor.getCount() > 0)
        {
            long result = DBVehicle.delete("Repaire", "vehiclenumb=?", new String[]{vehiclenumb});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getrepairedata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * from Repaire", null);
        return  cursor;
    }

    public Boolean insertfueldata(String vehiclenb,String vehicleFtype,String fuelFtype,String dateoffuel,String miloffueltime,String litergo,String avgdis)
    {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehiclenb", vehiclenb);
        contentValues.put("vehicleFtype", vehicleFtype);
        contentValues.put("fueltype", fuelFtype);
        contentValues.put("dateoffuel", dateoffuel);
        contentValues.put("miloffueltime", miloffueltime);
        contentValues.put("litergo", litergo);
        contentValues.put("avgdis", avgdis);
        long result=DBVehicle.insert("Fuel", null, contentValues);
        if (result == -1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean deletefueldata(String vehiclenb) {
        SQLiteDatabase DBVehicle = this.getWritableDatabase();
        Cursor cursor = DBVehicle.rawQuery("Select * from Fuel where vehiclenb=?", new String[]{vehiclenb});
        if (cursor.getCount() > 0) {
            long result = DBVehicle.delete("Fuel", "vehiclenb=?", new String[]{vehiclenb});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

        public Cursor getfueldata() {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor= DB.rawQuery("Select * from Fuel", null);
            return  cursor;
        }

}
