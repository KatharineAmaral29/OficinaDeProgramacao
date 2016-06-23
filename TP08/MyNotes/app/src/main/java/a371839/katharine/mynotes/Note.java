package a371839.katharine.mynotes;

import java.util.Date;

public class Note {

    private int id;
    private String title;
    private String content;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getData(){
        return data;
    }

    public void setData(Date data){
        this.data=data;
    }

    public String toString() {
        return this.id+" - Título: "+this.title+" - "+this.content+" - "+this.data;
    }
}