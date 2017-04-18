package xoapit.controldev;

/**
 * Created by Xoapit on 10/17/2016.
 */
//lop nay su dung de map du lieu giua cac keys trong json toi object su dung json
public class Device {
    String D01;
    String D02;
    String D03;
    String D04;
    String user;
    String pass;
    String stateAll;
    public String getUser(){
        return user;
    }

    public void setUser(String User){
        user=User;
    }

    public String getPass(){
        return pass;
    }

    public void setPass(String Pass){
        pass=Pass;
    }

    public String getD01() {
        return D01;//doan ni mi bam phim tat chi cho no tao tu dong ra ruâạo ta mà dùng lag do
    }

    public void setD01(String d01) {
        D01 = d01;
    }

    public String getD02() {
        return D02;
    }

    public void setD02(String d02) {
        D02 = d02;
    }

    public String getD03() {
        return D03;
    }

    public void setD03(String d03) {
        D03 = d03;
    }

    public String getD04() {
        return D04;
    }

    public void setD04(String d04) {
        D04 = d04;
    }

}
