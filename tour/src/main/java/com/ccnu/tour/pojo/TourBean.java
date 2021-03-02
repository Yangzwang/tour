package com.ccnu.tour.pojo;

/**
 * @Author: yang
 * @CreateTime: 2019-12-29 15:44
 * @Description: bean
 */
public class TourBean {
        private Integer id;
        private String tourUrl;
        private String tourTitle;
        private String tourAuthor;
        private  String tourContent;
        private  String  tourSummary;
        private  String createTime;
        private  Integer  cityId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTourUrl() {
            return tourUrl;
        }

        public void setTourUrl(String tourUrl) {
            this.tourUrl = tourUrl;
        }

        public String getTourTitle() {
            return tourTitle;
        }

        public void setTourTitle(String tourTitle) {
            this.tourTitle = tourTitle;
        }

        public String getTourAuthor() {
            return tourAuthor;
        }

        public void setTourAuthor(String tourAuthor) {
            this.tourAuthor = tourAuthor;
        }

        public String getTourContent() {
            return tourContent;
        }

        public void setTourContent(String tourContent) {
            this.tourContent = tourContent;
        }

        public String getTourSummary() {
            return tourSummary;
        }

        public void setTourSummary(String tourSummary) {
            this.tourSummary = tourSummary;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

}
