package com.example.project;

public class Answer {
    String description;
    int flag; // 0 sau 1

    int i;//0-5

    public Answer(String description, int flag) {
        this.description = description;
        this.flag = flag;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


}

