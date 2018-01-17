package com.blanke.purebook_android.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 书籍model
 * @author chrischen
 */

@AVClassName("Book")
public class  Book extends AVObject implements Parcelable{



    private String BookName;
    private int BookID;
    private String Author;
    private String AuthorIntro;
    private String Price;
    private String Intro;
    private String Cover;
    private String Publisher;
    @SerializedName("ISBN")
    private String ISBNX;

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int BookID) {
        this.BookID = BookID;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getAuthorIntro() {
        return AuthorIntro;
    }

    public void setAuthorIntro(String AuthorIntro) {
        this.AuthorIntro = AuthorIntro;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String Intro) {
        this.Intro = Intro;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public String getISBNX() {
        return ISBNX;
    }

    public void setISBNX(String ISBNX) {
        this.ISBNX = ISBNX;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            Book Book = new Book();
            Book.setBookName(source.readString());
            Book.setBookID(source.readInt());
            Book.setAuthor(source.readString());
            Book.setAuthorIntro(source.readString());
            Book.setPrice(source.readString());
            Book.setIntro(source.readString());
            Book.setCover(source.readString());
            Book.setPublisher(source.readString());
            Book.setISBNX(source.readString());
            return Book;
        }
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
      public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(BookName);
        dest.writeInt(BookID);
        dest.writeString(Author);
        dest.writeString(AuthorIntro);
        dest.writeString(Price);
        dest.writeString(Intro);
        dest.writeString(Cover);
        dest.writeString(Publisher);
        dest.writeString(ISBNX);
    }
}