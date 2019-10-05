package com.example.dutch.ListViewFunctionClass;

//data transfer object : 계층 간 데이터 교환을 위한 클래스
public class CustomDTO {

    private int resId;
    private String title;
    private String content;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
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

}
