package com.huida.zsxs.bean;

/**
 * Created by lenovo on 2017/6/23.
 */

public class SlidesBean {
    /**
     * pic : http://www.chinaplat.com/imgzt/IMG-20170124/20170124140783108310.png
     * title : 名校课程
     * picURL : 112
     * pictype : app
     */

    private String pic;
    private String title;
    private String picURL;
    private String pictype;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getPictype() {
        return pictype;
    }

    public void setPictype(String pictype) {
        this.pictype = pictype;
    }

    @Override
    public String toString() {
        return "SlidesBean{" +
                "pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", picURL='" + picURL + '\'' +
                ", pictype='" + pictype + '\'' +
                '}';
    }
}