package com.geoacircle.todonow;

public class ToDoData {

    public ToDoData() {

    }

    public ToDoData(int id, String title, String descript) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public ToDoData(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String toString(){
        return "ToDo [ id=" + id +", title=" + title + ", desc=" + desc + "]";
    }

}
