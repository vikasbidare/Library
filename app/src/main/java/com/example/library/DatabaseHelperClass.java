package com.example.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static String databaseName = "library.db";

    private String studentTable = "studentTable";
    private String bookTable = "bookTable";
    private String facultyTable = "facultyTable";
    private String studentHistoryTable = "studentHistoryTable";
    private String facultyHistoryTable = "facultyHistoryTable";
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

    private String studentHistoryTableISSUERID = "issuer_id";
    private String studentHistoryTableBID = "bid";
    private String studentHistoryTableISSUEDATE = "issue_date";
    private String studentHistoryTableRETURNDATE = "return_date";
    private String studentHistoryTableFINEAMT = "fine_amt";

    private String facultyHistoryTableISSUERID = "issuer_id";
    private String facultyHistoryTableBID = "bid";
    private String facultyHistoryTableISSUEDATE = "issue_date";
    private String facultyHistoryTableRETURNDATE = "return_date";
    private String facultyHistoryTableFINEAMT = "fine_amt";

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

        db.execSQL("create table if not exists "+studentTable+"("+studentTableSID+" varchar(10) primary key, "+studentTableNAME +" varchar(30), "
                + studentTableEMAILID+" varchar(20), "+ studentTablePHONE+" char(10), "+studentTableDEPARTMENT+"  varchar(15))");

        db.execSQL("create table if not exists "+facultyTable+"("+facultyTableFID+" varchar(4) primary key, "+facultyTableNAME +" varchar(30), "
                + facultyTableEMAILID+" varchar(20), "+ facultyTablePHONE+" char(10), "+facultyTableDEPARTMENT+"  varchar(15))");

        db.execSQL("create table if not exists "+bookTable+"("+bookTableID+" char(4) primary key, "+ bookTableTITLE +" varchar(30), "
                + bookTableAUTHOR+" varchar(20), "+ bookTableEDITION+ " varchar(2)," + bookTableGENRE+" varchar(10),"+ bookTablePRICE+" varchar(5),"
                + bookTableISSUED+"  varchar(3)," + bookTablePUBLICATION+"  varchar(20),"  + bookTableYEAR+"  char(4))");

        db.execSQL("create table if not exists " + studentHistoryTable+"("+studentHistoryTableBID+" char(4) ,"+ studentHistoryTableISSUERID +" varchar(10), "
                + studentHistoryTableISSUEDATE+" date, "+ studentHistoryTableRETURNDATE+" date, "+ studentHistoryTableFINEAMT+" varchar(5))");

        db.execSQL("create table if not exists " + facultyHistoryTable+"("+facultyHistoryTableBID+" char(4) , "+ facultyHistoryTableISSUERID +" varchar(10), "
                + facultyHistoryTableISSUEDATE+" date, "+ facultyHistoryTableRETURNDATE+" date, "+ facultyHistoryTableFINEAMT+" varchar(5))");

        db.execSQL("create table if not exists "+adminTable+"("+adminTableID+" varchar(20) primary key, "+adminTableNAME +" varchar(15), "
                +adminTableNUMBER+" char(10))");

        db.execSQL("create table if not exists "+loginTable+"("+loginTableUID+" varchar(20) primary key, "+ loginTablePASSWORD +" varchar(15))");

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

            //Toast.makeText(mcontext, "Insertion Failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            //Toast.makeText(mcontext, "Insertion Successful", Toast.LENGTH_SHORT).show();
            return true;

        }
    }

    public boolean insertIntoStudent(String sname,String emailid,String phone,String department,String ID)
    {
        SQLiteDatabase db = DatabaseHelperClass.this.getWritableDatabase();

        ContentValues valuesintostudentTable = new ContentValues();
        valuesintostudentTable.put(studentTableSID,ID);
        valuesintostudentTable.put(studentTableNAME,sname);
        valuesintostudentTable.put(studentTablePHONE,phone);
        valuesintostudentTable.put(studentTableDEPARTMENT,department);
        valuesintostudentTable.put(studentTableEMAILID,emailid);

        long result = db.insert(studentTable,null,valuesintostudentTable);

        if(result==-1 ) {

            //Toast.makeText(mcontext, "Student exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            //Toast.makeText(mcontext, "Student added", Toast.LENGTH_SHORT).show();
            return true;

        }
    }

    public boolean insertIntoFaculty(String fname,String emailid,String phone,String department,String ID)
    {
        SQLiteDatabase db = DatabaseHelperClass.this.getWritableDatabase();

        ContentValues valuesintofacultyTable = new ContentValues();
        valuesintofacultyTable.put(facultyTableFID,ID);
        valuesintofacultyTable.put(facultyTableNAME,fname);
        valuesintofacultyTable.put(facultyTablePHONE,phone);
        valuesintofacultyTable.put(facultyTableDEPARTMENT,department);
        valuesintofacultyTable.put(facultyTableEMAILID,emailid);

        long result = db.insert(facultyTable,null,valuesintofacultyTable);

        if(result==-1 ) {

            //Toast.makeText(mcontext, "Faculty exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            //Toast.makeText(mcontext, "Faculty added", Toast.LENGTH_SHORT).show();
            return true;

        }
    }

    public boolean insertIntoBook(String title,String author,String ID,String price,String edition,String genre,String publisher,String year){
        SQLiteDatabase db = DatabaseHelperClass.this.getWritableDatabase();

        ContentValues valuesintobookTable = new ContentValues();
        valuesintobookTable.put(bookTableID,ID);
        valuesintobookTable.put(bookTableTITLE,title);
        valuesintobookTable.put(bookTableAUTHOR,author);
        valuesintobookTable.put(bookTableEDITION,edition);
        valuesintobookTable.put(bookTableGENRE,genre);
        valuesintobookTable.put(bookTablePRICE,price);
        valuesintobookTable.put(bookTableISSUED,"No");
        valuesintobookTable.put(bookTablePUBLICATION,publisher);
        valuesintobookTable.put(bookTableYEAR,year);

        long result = db.insert(bookTable,null,valuesintobookTable);


        if(result==-1 ) {

            //Toast.makeText(mcontext, "Faculty exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            //Toast.makeText(mcontext, "Faculty added", Toast.LENGTH_SHORT).show();
            return true;

        }
    }

    public Cursor getFromLogin(String userID)
    {
        SQLiteDatabase db = DatabaseHelperClass.this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+loginTable +" where "+loginTableUID +" = "+ " '"+userID+"'",null);
        return res;
    }
}
