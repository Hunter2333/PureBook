package com.blanke.purebook_android.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookBean implements Serializable {
   @SerializedName("BookName")
   private String bookName;
   @SerializedName("BookID")
   private int bookID;
   @SerializedName("Author")
   private String author;
   @SerializedName("AuthorInfo")
   private String authorInfo;
   @SerializedName("Price")
   private String price;
   @SerializedName("Intro")
   private String intro;
   @SerializedName("Cover")
   private String cover;
   @SerializedName("Publisher")
   private String publisher;
   @SerializedName("ISBN")
   private String iSBN;

   public String getBookName(){
       return this.bookName;
   }

   public void setBookName(String bookName){
       this.bookName = bookName;
   }

   public int getBookID(){
       return this.bookID;
   }

   public void setBookID(int bookID){
       this.bookID = bookID;
   }

   public String getAuthor(){
       return this.author;
   }

   public void setAuthor(String author){
       this.author = author;
   }

    public String getAuthorInfo(){
        return this.authorInfo;
    }

    public void setAuthorInfo(String authorInfo){
        this.authorInfo = authorInfo;
    }

   public String getPrice(){
       return this.price;
   }

   public void setPrice(String price){
       this.price = price;
   }

   public String getIntro(){
       return this.intro;
   }

   public void setIntro(String intro){
       this.intro = intro;
   }

   public String getCover(){
       return this.cover;
   }

   public void setCover(String cover){
       this.cover = cover;
   }

   public String getPublisher(){
       return this.publisher;
   }

   public void setPublisher(String publisher){
       this.publisher = publisher;
   }

   public String getiSBN(){
       return this.iSBN;
   }

   public void setiSBN(String iSBN){
       this.iSBN = iSBN;
   }
}
