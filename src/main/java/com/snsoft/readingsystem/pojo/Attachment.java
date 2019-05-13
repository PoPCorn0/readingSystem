/*
 * @copyright ：神农大学生软件创新中心 版权所有 © 2019
 *
 * @author 16级信息与计算科学潘鹏程
 *
 * @version
 *
 * @date 2019.05.08
 *
 * @Description
 */

package com.snsoft.readingsystem.pojo;

import java.io.Serializable;

public class Attachment implements Serializable {
    private String id;
    private String relyOnId;
    private String authorId;
    private String savePath;
    private int size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelyOnId() {
        return relyOnId;
    }

    public void setRelyOnId(String relyOnId) {
        this.relyOnId = relyOnId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + id + '\'' +
                ", relyOnId='" + relyOnId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", savePath='" + savePath + '\'' +
                ", size=" + size +
                '}';
    }
}