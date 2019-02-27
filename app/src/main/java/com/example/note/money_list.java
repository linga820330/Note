package com.example.note;

public class money_list {
    private String date;
    private String item;
    private String content;
    private int price;
    private int type;




    public money_list(String date, String item, String content, int price , int type) {
        this.date = date;
        this.item = item;
        this.content = content;
        this.price = price;
        this.type = type;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
