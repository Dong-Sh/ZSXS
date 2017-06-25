package com.huida.zsxs.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/6/24.
 */

public class HomeListViewBean {

    /**
     * page_all : 17
     * page_now : 1
     * Course : [{"kc_id":"140725","title":"2017年导游考试【大纲分析】讲座","img":"http://www.chinaplat.com/CourseImg/IMG-20170509/20170509142596339633.png","info":"2017年导游考试【大纲分析】讲座","money":"2900","keshi":4,"hot":676},{"kc_id":"140729","title":"TTT-培训师的修炼","img":"http://www.chinaplat.com/CourseImg/IMG-20170517/20170517095147194719.jpg","info":"TTT-培训师的修炼","money":"9900","keshi":20,"hot":616},{"kc_id":"140657","title":"2017导游从业资格--全国导游基础知识（考点精讲）","img":"http://www.chinaplat.com/CourseImg/IMG-20170218/20170218110555975597.jpg","info":"2017导游从业资格--全国导游基础知识（考点精讲）","money":"10900","keshi":63,"hot":5536},{"kc_id":"140655","title":"2017导游从业资格--政策与法律法规（考点精讲）","img":"http://www.chinaplat.com/CourseImg/IMG-20170218/20170218091855445544.jpg","info":"2017导游从业资格--政策与法律法规（考点精讲）","money":"11900","keshi":61,"hot":4501},{"kc_id":"141139","title":"2017地方导游基础知识（习题提升）","img":"http://www.chinaplat.com/CourseImg/IMG-20170526/20170526161678437843.jpg","info":"2017地方导游基础知识（习题提升）","money":"13900","keshi":1,"hot":327},{"kc_id":"141138","title":"2017地方导游基础知识（考点精讲）","img":"http://www.chinaplat.com/CourseImg/IMG-20170526/20170526161650955095.jpg","info":"2017地方导游基础知识（考点精讲）","money":"12900","keshi":1,"hot":276},{"kc_id":"140767","title":"国家普通话水平测试过关速成训练","img":"http://www.chinaplat.com/CourseImg/IMG-20170515/20170515145758375837.png","info":"国家普通话水平测试过关速成训练","money":"5900","keshi":12,"hot":124},{"kc_id":"140726","title":"2017年导游考试【报名指导】讲座","img":"http://www.chinaplat.com/CourseImg/IMG-20170509/20170509142735923592.png","info":"2017年导游考试【报名指导】讲座","money":"2900","keshi":3,"hot":338},{"kc_id":"140660","title":"2017导游从业资格--导游业务（习题提升）","img":"http://www.chinaplat.com/CourseImg/IMG-20170218/20170218113481938193.jpg","info":"2017导游从业资格--导游业务（习题提升）","money":"11900","keshi":1,"hot":1480},{"kc_id":"140659","title":"2017导游从业资格--导游业务（考点精讲）","img":"http://www.chinaplat.com/CourseImg/IMG-20170218/20170218113419301930.jpg","info":"2017导游从业资格--导游业务（考点精讲）","money":"10900","keshi":49,"hot":2410}]
     */

    private String page_all;
    private String page_now;
    private List<CourseBean> Course;

    public String getPage_all() {
        return page_all;
    }

    public void setPage_all(String page_all) {
        this.page_all = page_all;
    }

    public String getPage_now() {
        return page_now;
    }

    public void setPage_now(String page_now) {
        this.page_now = page_now;
    }

    public List<CourseBean> getCourse() {
        return Course;
    }

    public void setCourse(List<CourseBean> Course) {
        this.Course = Course;
    }

    public static class CourseBean {
        /**
         * kc_id : 140725
         * title : 2017年导游考试【大纲分析】讲座
         * img : http://www.chinaplat.com/CourseImg/IMG-20170509/20170509142596339633.png
         * info : 2017年导游考试【大纲分析】讲座
         * money : 2900
         * keshi : 4
         * hot : 676
         */

        private String kc_id;
        private String title;
        private String img;
        private String info;
        private String money;
        private int keshi;
        private int hot;

        public String getKc_id() {
            return kc_id;
        }

        public void setKc_id(String kc_id) {
            this.kc_id = kc_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getKeshi() {
            return keshi;
        }

        public void setKeshi(int keshi) {
            this.keshi = keshi;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }
    }
}
