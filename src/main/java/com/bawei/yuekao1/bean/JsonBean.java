package com.bawei.yuekao1.bean;

import java.util.List;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: Machenike
 * Date: 2019/3/29 20:48
 * Description:
 */
public class JsonBean {
    public int code;
    public DataBean data;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class DataBean{
        public List<NewsBean> news;
        public List<TopnewsBean> topnews;

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        public class  NewsBean{
            //"comment": false,
            //                "id": 5,
            //                "imageUrl": "image/banner1.jpeg",
            //                "publishAt": "2019-03-22",
            //                "title": "脸书再曝安全漏洞：数亿用户密码没加密保存，员工可直接浏览",
            //                "type": "news",
            //                "url": "724D6A55496A11726628.html"
                private boolean comment;
                private int id;
                private String imageUrl;
                private String publishAt;
                private String title;
                private String type;
                private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getPublishAt() {
                return publishAt;
            }

            public void setPublishAt(String publishAt) {
                this.publishAt = publishAt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
        public class  TopnewsBean{
            // "comment": false,
            //                "id": 0,
            //                "imageUrl": "image/banner1.jpeg",
            //                "publishAt": "2019-03-22",
            //                "title": "波音接受质询",
            //                "type": "news",
            //                "url": "724D6A55496A11726628.html"
            private boolean comment;
            private int id;
            private String imageUrl;
            private String publishAt;
            private String title;
            private String type;
            private String url;

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getPublishAt() {
                return publishAt;
            }

            public void setPublishAt(String publishAt) {
                this.publishAt = publishAt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
