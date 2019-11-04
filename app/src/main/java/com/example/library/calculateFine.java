package com.example.library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class calculateFine {

    public  double fineForFaculty(String issuseDate,String returnDate)
    {
        Date IssueDate = null,ReturnDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
             IssueDate = sdf.parse(issuseDate);
            ReturnDate = sdf.parse(returnDate);

            long res =  ReturnDate.getTime()-IssueDate.getTime() ;
            long res1 = TimeUnit.DAYS.convert(res,TimeUnit.MILLISECONDS);

//            if(((res/(1000*60*60*24)) - (long)14) >0)
//            {
//                res  =  ((res/(1000*60*60*24)) - (long)14);
//                res = res/2;
//                return res;
//            }
//            else
//            {
//                return  0.00;
//            }


            if(res1 - 14>0)
            {
                return (res1-14)/2;
            }
            else
            {
                return 0.00;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
