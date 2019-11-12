package com.telran.selenium.model;

public class BoardNames {
    private  String boardName;



    public BoardNames setBoardName(String boardsName) {
        this.boardName=boardsName;
        return this;
    }

    public String getBoardName() {
        return boardName;
    }

    @Override
    public String toString() {
        return "BoardNames{" +
                "boardName='" + boardName + '\'' +
                '}';
    }
}
