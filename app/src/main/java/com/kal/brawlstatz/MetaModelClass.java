package com.kal.brawlstatz;

public class MetaModelClass {
    String bname,brare,bpro,tier, bstarpower,bgadget,bgear1,bgear2,bgear3;
    MetaModelClass(){
    }

    public MetaModelClass(String bname, String brare, String bpro, String tier, String bstarpower, String bgadget, String bgear1, String bgear2, String bgear3) {
        this.bname = bname;
        this.brare = brare;
        this.bpro = bpro;
        this.tier = tier;
        this.bstarpower = bstarpower;
        this.bgadget = bgadget;
        this.bgear1 = bgear1;
        this.bgear2 = bgear2;
        this.bgear3 = bgear3;
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

    public String getBstarpower() {
        return bstarpower;
    }

    public void setBstarpower(String bstarpower) {
        this.bstarpower = bstarpower;
    }

    public String getBgadget() {
        return bgadget;
    }

    public void setBgadget(String bgadget) {
        this.bgadget = bgadget;
    }

    public String getBgear1() {
        return bgear1;
    }

    public void setBgear1(String bgear1) {
        this.bgear1 = bgear1;
    }

    public String getBgear2() {
        return bgear2;
    }

    public void setBgear2(String bgear2) {
        this.bgear2 = bgear2;
    }

    public String getBgear3() {
        return bgear3;
    }

    public void setBgear3(String bgear3) {
        this.bgear3 = bgear3;
    }
}
