package com.kal.brawlstatz;

import org.jetbrains.annotations.Contract;

//model class for custom ArrayList.
public class BrawlerModelClass {
     String bname,brare,bpro,babout,bmodel,c1,c1n,c2,c2n,c3,c3n,g1,g1t,g2,g2t,s1,s1t,s2,s2t;
    BrawlerModelClass(){
    }

    public BrawlerModelClass(String bname, String brare, String bpro, String babout, String bmodel, String c1, String c1n, String c2, String c2n, String c3, String c3n, String g1, String g1t, String g2, String g2t, String s1, String s1t, String s2, String s2t) {
        this.bname = bname;
        this.brare = brare;
        this.bpro = bpro;
        this.babout = babout;
        this.bmodel = bmodel;
        this.c1 = c1;
        this.c1n = c1n;
        this.c2 = c2;
        this.c2n = c2n;
        this.c3 = c3;
        this.c3n = c3n;
        this.g1 = g1;
        this.g1t = g1t;
        this.g2 = g2;
        this.g2t = g2t;
        this.s1 = s1;
        this.s1t = s1t;
        this.s2 = s2;
        this.s2t = s2t;
    }

    public String getBabout() {return babout; }
    public void setBabout(String babout) {
        this.babout = babout;
    }

    public String getBmodel() {
        return bmodel;
    }
    public void setBmodel(String bmodel) {
        this.bmodel = bmodel;
    }

    public String getC1() {
        return c1;
    }
    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC1n() {
        return c1n;
    }
    public void setC1n(String c1n) {
        this.c1n = c1n;
    }

    public String getC2() {
        return c2;
    }
    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC2n() {
        return c2n;
    }
    public void setC2n(String c2n) {
        this.c2n = c2n;
    }

    public String getC3() {
        return c3;
    }
    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC3n() {
        return c3n;
    }
    public void setC3n(String c3n) {
        this.c3n = c3n;
    }

    public String getG1() {
        return g1;
    }
    public void setG1(String g1) {
        this.g1 = g1;
    }

    public String getG1t() {
        return g1t;
    }
    public void setG1t(String g1t) {
        this.g1t = g1t;
    }

    public String getG2() {
        return g2;
    }
    public void setG2(String g2) {
        this.g2 = g2;
    }

    public String getG2t() {
        return g2t;
    }
    public void setG2t(String g2t) {
        this.g2t = g2t;
    }

    public String getS1() {
        return s1;
    }
    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS1t() {
        return s1t;
    }
    public void setS1t(String s1t) {
        this.s1t = s1t;
    }

    public String getS2() {
        return s2;
    }
    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS2t() {
        return s2t;
    }
    public void setS2t(String s2t) {
        this.s2t = s2t;
    }

    public String getBpro() {
        return bpro;
    }
    public void setBpro(String bpro) {
        this.bpro = bpro;
    }

    public String getBname() {
        return bname;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBrare() {
        return brare;
    }
    public void setBrare(String brare) {
        this.brare = brare;
    }
}
