package fr.univtln.nmartinez016;

/**
 * Created by marti on 17/05/2016.
 */


public class Team {
    private int mId;
    private String mColor;

    public Team(){}

    public Team(int pId, String pColor){
        mId = pId;
        mColor = pColor;
    }

    public int getId(){
        return mId;
    }

    public void setId(int pId){
        mId = pId;
    }

    public String getColor(){
        return mColor;
    }

    public void setColor(String pColor){
        mColor = pColor;
    }
}
