package com.kal.brawlstatz;

public class MapModelClass {
    String mthumb,mname,menv,mlayout,mtype,mabout;

    MapModelClass(){
    }

    public MapModelClass(String mthumb, String mname, String menv, String mlayout, String mtype,String mabout) {
        this.mthumb = mthumb;
        this.mname = mname;
        this.menv = menv;
        this.mlayout = mlayout;
        this.mtype = mtype;
        this.mabout = mabout;
    }

    public String getMabout() {
        return mabout;
    }

    public void setMabout(String mabout) {
        this.mabout = mabout;
    }

    public String getMthumb() {
        return mthumb;
    }

    public void setMthumb(String mthumb) {
        this.mthumb = mthumb;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMenv() {
        return menv;
    }

    public void setMenv(String menv) {
        this.menv = menv;
    }

    public String getMlayout() {
        return mlayout;
    }

    public void setMlayout(String mlayout) {
        this.mlayout = mlayout;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }
}
