package com.example.pity.gloryofastudent;

import cn.bmob.v3.BmobObject;

/**
 * Created by Pity on 12/03/2017.
 */
public class UserInformation extends BmobObject {
    //用户ID
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //用户密码
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    //用户昵称
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //用户答题总数
    private Integer questionNumber;

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    //用户正确答题总数
    private Integer correctQuestionNumber;

    public Integer getCorrectQuestionNumber() {
        return correctQuestionNumber;
    }

    public void setCorrectQuestionNumber(Integer correctQuestionNumber) {
        this.correctQuestionNumber = correctQuestionNumber;
    }

    //用户天梯积分
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
