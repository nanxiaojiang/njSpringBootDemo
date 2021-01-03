package com.nj.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2020/12/19 11:27
 */
public class MydownBiaduSitemapG {

    @ApiModelProperty("站点ID")
    private String channelid;
    private String channelname;
    private String url;
    private String keyword;
    private String loc;
    private Integer flag;
    private Integer downStatus;

    @Override
    public String toString() {
        return "MydownBiaduSitemapG{" +
                "channelid='" + channelid + '\'' +
                ", channelname='" + channelname + '\'' +
                ", url='" + url + '\'' +
                ", keyword='" + keyword + '\'' +
                ", loc='" + loc + '\'' +
                ", flag=" + flag +
                ", downStatus=" + downStatus +
                '}';
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getDownStatus() {
        return downStatus;
    }

    public void setDownStatus(Integer downStatus) {
        this.downStatus = downStatus;
    }
}
