package com.kal.brawlstatz;

public class MetaModelClass {
    String bname,brare,bpro,tier;


    MetaModelClass(){

    }

    public MetaModelClass(String bname, String brare, String bpro, String tier) {
        this.bname = bname;
        this.brare = brare;
        this.bpro = bpro;
        this.tier = tier;

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

    public String getBpro() {
        return bpro;
    }

    public void setBpro(String bpro) {
        this.bpro = bpro;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

}
