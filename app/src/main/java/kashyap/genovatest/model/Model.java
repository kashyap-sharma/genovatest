package kashyap.genovatest.model;

import java.util.ArrayList;

public class Model {

    public static final int banner=0;
    public static final int ListHorizSquare=1;
    public static final int banner1=2;
    public static final int GridSquare=3;
    public static final int ListHorizBig=4;
    public static final int ListCarous=5;
    public static final int List2itemgrid=6;


    public String title;
    public int type;
    public ArrayList<HomeModel> datas;

    public Model(int type, String title,  ArrayList<HomeModel> datas) {
        this.title = title;
        this.type = type;
        this.datas = datas;
    }
}
