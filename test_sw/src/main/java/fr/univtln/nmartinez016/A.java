package fr.univtln.nmartinez016;

import java.io.Serializable;

/**
 * Created by marti on 17/05/2016.
 */
public class A implements Serializable {
    private int mId;
    private String mName;
    private int mEnergy;

    public A(){}

    public A(ABuilder pBuilder){
        mId = pBuilder.mId;
        mName = pBuilder.mName;
        mEnergy = pBuilder.mEnergy;
    }

    public int getId(){
        return mId;
    }

    public String getName(){
        return mName;
    }

    public void setId(int pId){
        mId = pId;
    }

    public void setName(String pName){
        mName = pName;
    }

    public int getEnergy(){
        return mEnergy;
    }

    public void setEnergy(int pEnergy){
        mEnergy = pEnergy;
    }

    public void receiveAttack(){
        mEnergy = mEnergy - 5;
    }

    public static class ABuilder{
        private int mId;
        private String mName;
        private int mEnergy;

        public ABuilder(int pId){
            mId = pId;
        }

        public ABuilder name(String pName){
            mName = pName;
            return this;
        }

        public ABuilder energy(int pEnergy){
            mEnergy = pEnergy;
            return this;
        }

        public A build(){
            return new A(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        return mId == a.mId;

    }

    @Override
    public int hashCode() {
        return mId;
    }

    @Override
    public String toString() {
        return "A{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mEnergy=" + mEnergy +
                '}';
    }
}
