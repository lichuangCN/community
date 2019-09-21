package vex.muzhi.community.model;

public class Notification {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notifier_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Long notifierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.receiver_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Long receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notify_outer_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Long notifyOuterId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notify_type
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Integer notifyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.gmt_create
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.status
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notification.notify_outer_title
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    private String notifyOuterTitle;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.id
     *
     * @return the value of notification.id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.id
     *
     * @param id the value for notification.id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notifier_id
     *
     * @return the value of notification.notifier_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Long getNotifierId() {
        return notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notifier_id
     *
     * @param notifierId the value for notification.notifier_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setNotifierId(Long notifierId) {
        this.notifierId = notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.receiver_id
     *
     * @return the value of notification.receiver_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.receiver_id
     *
     * @param receiverId the value for notification.receiver_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notify_outer_id
     *
     * @return the value of notification.notify_outer_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Long getNotifyOuterId() {
        return notifyOuterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notify_outer_id
     *
     * @param notifyOuterId the value for notification.notify_outer_id
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setNotifyOuterId(Long notifyOuterId) {
        this.notifyOuterId = notifyOuterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notify_type
     *
     * @return the value of notification.notify_type
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Integer getNotifyType() {
        return notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notify_type
     *
     * @param notifyType the value for notification.notify_type
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.gmt_create
     *
     * @return the value of notification.gmt_create
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.gmt_create
     *
     * @param gmtCreate the value for notification.gmt_create
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.status
     *
     * @return the value of notification.status
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.status
     *
     * @param status the value for notification.status
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notification.notify_outer_title
     *
     * @return the value of notification.notify_outer_title
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public String getNotifyOuterTitle() {
        return notifyOuterTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notification.notify_outer_title
     *
     * @param notifyOuterTitle the value for notification.notify_outer_title
     *
     * @mbg.generated Fri Sep 20 15:17:19 CST 2019
     */
    public void setNotifyOuterTitle(String notifyOuterTitle) {
        this.notifyOuterTitle = notifyOuterTitle == null ? null : notifyOuterTitle.trim();
    }
}