package com.example.library;

public class History {

    private String mIssuerId;
    private String mBookId;
    private String mreturnDate;
    private String mIssueDate;
    private String mFineAmount;

    public History(String IssuerId, String BookId,String IssueDate, String ReturnDate, String FineAmount)
    {
        mBookId=BookId;
        mFineAmount = FineAmount;
        mreturnDate = ReturnDate;
        mIssueDate = IssueDate;
        mIssuerId = IssuerId;
    }

    public String getmIssuerId() {
        return mIssuerId;
    }

    public String getmBookId() {
        return mBookId;
    }

    public String getMreturnDate() {
        if (mreturnDate == null || mreturnDate.length() == 0)
            return "-------";
        else
            return mreturnDate;


    }

    public String getmIssueDate() {
        return mIssueDate;
    }

    public String getmFineAmount() {
        return mFineAmount;
    }
}
