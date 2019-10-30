package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static String databaseName = "library.db";

    private String studentTable = "studentTable";
    private String bookTable = "bookTable";
    private String facultyTable = "facultyTable";
    private String historyTable = "historyTable";
    private String loginTable = "loginTable";
    private String adminTable = "adminTable";

    private String studentTableSID = "sID";
    private String studentTableNAME = "name";
    private String studentTablePHONE = "phone";
    private String studentTableEMAILID = "email_id";
    private String studentTableDEPARTMENT = "department";

    private String facultyTableFID = "fID";
    private String facultyTableNAME = "name";
    private String facultyTablePHONE = "phone";
    private String facultyTableEMAILID = "email_id";
    private String facultyTableDEPARTMENT = "department";

    private String bookTableID = "bID";
    private String bookTableTITLE = "title";
    private String bookTableAUTHOR = "author";
    private String bookTableEDITION = "edition";
    private String bookTablePUBLICATION = "publication";
    private String bookTableGENRE = "genre";
    private String bookTableYEAR = "year";
    private String bookTablePRICE = "price";
    private String bookTableISSUED = "issued";

    private String historyTableISSUERID = "issuer_id";
    private String historyTableBID = "bid";
    private String historyTableISSUEDATE = "issue_date";
    private String historyTableRETURNDATE = "return_date";
    private String historyTableFINEAMT = "fine_amt";

    private String adminTableID = "aID";
    private String adminTableNAME = "name";
    private String adminTableNUMBER= "phone" ;

    private String loginTableUID = "uID";
    private String loginTablePASSWORD = "password";

    private Context mcontext;


    public DatabaseHelperClass(@Nullable Context context) {
        super(context, databaseName, null, 1);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+studentTable+"("+studentTableSID+" varchar(10) primary key, "+studentTableNAME +" varchar(10), "
                + studentTableEMAILID+" varchar(20), "+ studentTablePHONE+" varchar(10), "+studentTableDEPARTMENT+"  varchar(10))");

        db.execSQL("create table "+facultyTable+"("+facultyTableFID+" varchar(10) primary key, "+facultyTableNAME +" varchar(10), "
                + facultyTableEMAILID+" varchar(20), "+ facultyTablePHONE+" varchar(10), "+facultyTableDEPARTMENT+"  varchar(10))");

        db.execSQL("create table "+bookTable+"("+bookTableID+" varchar(4) primary key, "+ bookTableTITLE +" varchar(10), "
                + bookTableAUTHOR+" varchar(20), "+ bookTableEDITION+" varchar(10), "+ bookTableGENRE+"  varchar(10),"+ bookTablePRICE+" varchar(5),"
                + bookTableISSUED+"  varchar(3)," + bookTablePUBLICATION+"  varchar(10),"  + bookTableYEAR+"  varchar(4))");


        db.execSQL("create table "+adminTable+"("+adminTableID+" varchar(10) primary key, "+adminTableNAME +" varchar(10), "
                +adminTableNUMBER+" varchar(10))");

        db.execSQL("create table "+loginTable+"("+loginTableUID+" varchar(10) primary key, "+ loginTablePASSWORD +" varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+ studentTable);
        db.execSQL("drop table if exists "+ facultyTable);
        db.execSQL("drop table if exists "+ bookTable);
        db.execSQL("drop table if exists "+ adminTable);
        db.execSQL("drop table if exists "+ loginTable);
        onCreate(db);
    }

    public boolean insertIntoSignUp(String name,String emailid,String number,String password)
    {
        SQLiteDatabase db = DatabaseHelperClass.this.getWritableDatabase();

        ContentValues valuesIntoAdminTable = new ContentValues();
        valuesIntoAdminTable.put(adminTableID,emailid);
        valuesIntoAdminTable.put(adminTableNAME,name);
        valuesIntoAdminTable.put(adminTableNUMBER,number);

        ContentValues valuesIntoLoginTable = new ContentValues();
        valuesIntoLoginTable.put(loginTableUID,emailid);
        valuesIntoLoginTable.put(loginTablePASSWORD,password);

        long result = db.insert(adminTable,null,valuesIntoAdminTable);
        long result1 = db.insert(loginTable,null,valuesIntoLoginTable);

        if(result==-1 || result1 == -1) {

            Toast.makeText(mcontext, "Insertion Failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            Toast.makeText(mcontext, "Insertion Successful", Toast.LENGTH_SHORT).show();
            return true;

        }
    }

}
