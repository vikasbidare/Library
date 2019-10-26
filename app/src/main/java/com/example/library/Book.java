package com.example.library;

public class Book {

    private  String mbookTitle;
    private  String mbookId;
    private  String mbookAuthor;
    private  String missued;

    public  String getMbookTitle() {
        return mbookTitle;
    }

    public  String getMbookId() {
        return mbookId;
    }

    public  String getMbookAuthor() {
        return mbookAuthor;
    }

    public  String getMissued() {
        return missued;
    }

    public Book(String booktitle, String bookId, String bookAuthor, String issued)
    {
        mbookTitle = booktitle;
        mbookId = bookId;
        mbookAuthor = bookAuthor;
        missued = issued;
    }
}
