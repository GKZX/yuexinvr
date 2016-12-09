package org.yuexin.model;

import java.util.Date;

public class SysUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.id
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_user_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private String sysUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_real_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private String sysRealName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_user_password
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private String sysUserPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_phone
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private String sysPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.face_img_url
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private String faceImgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.add_time
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_flag
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    private Byte sysFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.id
     *
     * @return the value of sys_user.id
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.id
     *
     * @param id the value for sys_user.id
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_user_name
     *
     * @return the value of sys_user.sys_user_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public String getSysUserName() {
        return sysUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_user_name
     *
     * @param sysUserName the value for sys_user.sys_user_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName == null ? null : sysUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_real_name
     *
     * @return the value of sys_user.sys_real_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public String getSysRealName() {
        return sysRealName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_real_name
     *
     * @param sysRealName the value for sys_user.sys_real_name
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setSysRealName(String sysRealName) {
        this.sysRealName = sysRealName == null ? null : sysRealName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_user_password
     *
     * @return the value of sys_user.sys_user_password
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public String getSysUserPassword() {
        return sysUserPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_user_password
     *
     * @param sysUserPassword the value for sys_user.sys_user_password
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setSysUserPassword(String sysUserPassword) {
        this.sysUserPassword = sysUserPassword == null ? null : sysUserPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_phone
     *
     * @return the value of sys_user.sys_phone
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public String getSysPhone() {
        return sysPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_phone
     *
     * @param sysPhone the value for sys_user.sys_phone
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone == null ? null : sysPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.face_img_url
     *
     * @return the value of sys_user.face_img_url
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public String getFaceImgUrl() {
        return faceImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.face_img_url
     *
     * @param faceImgUrl the value for sys_user.face_img_url
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setFaceImgUrl(String faceImgUrl) {
        this.faceImgUrl = faceImgUrl == null ? null : faceImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.add_time
     *
     * @return the value of sys_user.add_time
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.add_time
     *
     * @param addTime the value for sys_user.add_time
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_flag
     *
     * @return the value of sys_user.sys_flag
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public Byte getSysFlag() {
        return sysFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_flag
     *
     * @param sysFlag the value for sys_user.sys_flag
     *
     * @mbggenerated Fri Dec 09 15:36:35 CST 2016
     */
    public void setSysFlag(Byte sysFlag) {
        this.sysFlag = sysFlag;
    }
}