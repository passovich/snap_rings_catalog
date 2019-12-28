package com.blogspot.passovich.bearings;

public class Ring {
    private String name;
    private String d;
    private String d2;
    private String d3;
    private String s;

    public Ring(String name, String d, String d2, String d3, String s){
        this.name = name;
        this.d = d;
        this.d2 = d2;
        this.d3 = d3;
        this.s = s;
    }
    private Ring(){}

    public String getColumn (int i){
        String str = "";
        if (i == 0) str = name; else
            if (i == 1) str = d; else
                if(i == 2) str = d2; else
                    if(i == 3) str = d3; else
                        if(i == 4) str = s; else
                            str = "Error";
        return  str;
    }
    public String getName() {
        return name;
    }
    public String getD() {
        return d;
    }
    public String getD2() {
        return d2;
    }
    public String getD3() {
        return d3;
    }
    public String getS() {
        return s;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setD(String d) {
        this.d = d;
    }
    public void setD2(String d2) {
        this.d2 = d2;
    }
    public void setD3(String d3) {
        this.d3 = d3;
    }
    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Ring{" +
                "name='" + name + '\'' +
                ", d='" + d + '\'' +
                ", d2='" + d2 + '\'' +
                ", d3='" + d3 + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
