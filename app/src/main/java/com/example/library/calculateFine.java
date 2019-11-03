package com.example.library;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calculateFine {

    public  double fineForFaculty(String issuseDate,String returnDate)
    {
        Date IssueDate = null,ReturnDate = null;
        try {
             IssueDate = new SimpleDateFormat("DD-MM-YYYY").parse(issuseDate);
            ReturnDate = new SimpleDateFormat("DD-MM-YYYY").parse(returnDate);

            long res =  ReturnDate.getTime()-IssueDate.getTime() ;

            if(res/(1000*60*60*24) - 14 >0)
            {
                res  =  (res/(1000*60*60*24) - 14);
                res = res/2;
                return res;
            }
            else
            {
                return  0.00;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
