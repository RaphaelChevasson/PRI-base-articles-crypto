package fr.tse.fise3.pri.p002.server.dto;

import java.util.Date;

public class DataSourceDTO {
    private long total;
    private long currentOffset;
    private boolean status;
    private Date createDate;
    private Date modifyDate;

    public DataSourceDTO() {
    }

    public DataSourceDTO(long total, long currentOffset, boolean status, Date createDate, Date modifyDate) {
        this.total = total;
        this.currentOffset = currentOffset;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(long currentOffset) {
        this.currentOffset = currentOffset;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
