package com.znc.jd.domain;

import java.io.Serializable;
import java.util.Date;

public class PShopCategory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.category_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Long categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.ptype_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Long ptypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.parent_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.priority
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Integer priority;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.Meta_Title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String metaTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.Meta_Description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String metaDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.Meta_Keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String metaKeywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.Depth
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Integer depth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.Path
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.ImageUrl
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String imageurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.HasChildren
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Boolean haschildren;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.created_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String createdBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.created_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String createdByName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.created_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.modified_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String modifiedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.modified_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private String modifiedByName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.modified_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Date modifiedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column p_shop_category.status
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table p_shop_category
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.category_id
     *
     * @return the value of p_shop_category.category_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.category_id
     *
     * @param categoryId the value for p_shop_category.category_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.ptype_id
     *
     * @return the value of p_shop_category.ptype_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Long getPtypeId() {
        return ptypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.ptype_id
     *
     * @param ptypeId the value for p_shop_category.ptype_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setPtypeId(Long ptypeId) {
        this.ptypeId = ptypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.parent_id
     *
     * @return the value of p_shop_category.parent_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.parent_id
     *
     * @param parentId the value for p_shop_category.parent_id
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.name
     *
     * @return the value of p_shop_category.name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.name
     *
     * @param name the value for p_shop_category.name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.priority
     *
     * @return the value of p_shop_category.priority
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.priority
     *
     * @param priority the value for p_shop_category.priority
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.keywords
     *
     * @return the value of p_shop_category.keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.keywords
     *
     * @param keywords the value for p_shop_category.keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.description
     *
     * @return the value of p_shop_category.description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.description
     *
     * @param description the value for p_shop_category.description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.title
     *
     * @return the value of p_shop_category.title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.title
     *
     * @param title the value for p_shop_category.title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.Meta_Title
     *
     * @return the value of p_shop_category.Meta_Title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.Meta_Title
     *
     * @param metaTitle the value for p_shop_category.Meta_Title
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle == null ? null : metaTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.Meta_Description
     *
     * @return the value of p_shop_category.Meta_Description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getMetaDescription() {
        return metaDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.Meta_Description
     *
     * @param metaDescription the value for p_shop_category.Meta_Description
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription == null ? null : metaDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.Meta_Keywords
     *
     * @return the value of p_shop_category.Meta_Keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.Meta_Keywords
     *
     * @param metaKeywords the value for p_shop_category.Meta_Keywords
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords == null ? null : metaKeywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.Depth
     *
     * @return the value of p_shop_category.Depth
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Integer getDepth() {
        return depth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.Depth
     *
     * @param depth the value for p_shop_category.Depth
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.Path
     *
     * @return the value of p_shop_category.Path
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.Path
     *
     * @param path the value for p_shop_category.Path
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.ImageUrl
     *
     * @return the value of p_shop_category.ImageUrl
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.ImageUrl
     *
     * @param imageurl the value for p_shop_category.ImageUrl
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.HasChildren
     *
     * @return the value of p_shop_category.HasChildren
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Boolean getHaschildren() {
        return haschildren;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.HasChildren
     *
     * @param haschildren the value for p_shop_category.HasChildren
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setHaschildren(Boolean haschildren) {
        this.haschildren = haschildren;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.created_by
     *
     * @return the value of p_shop_category.created_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.created_by
     *
     * @param createdBy the value for p_shop_category.created_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.created_by_name
     *
     * @return the value of p_shop_category.created_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getCreatedByName() {
        return createdByName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.created_by_name
     *
     * @param createdByName the value for p_shop_category.created_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName == null ? null : createdByName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.created_time
     *
     * @return the value of p_shop_category.created_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.created_time
     *
     * @param createdTime the value for p_shop_category.created_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.modified_by
     *
     * @return the value of p_shop_category.modified_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.modified_by
     *
     * @param modifiedBy the value for p_shop_category.modified_by
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.modified_by_name
     *
     * @return the value of p_shop_category.modified_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public String getModifiedByName() {
        return modifiedByName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.modified_by_name
     *
     * @param modifiedByName the value for p_shop_category.modified_by_name
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setModifiedByName(String modifiedByName) {
        this.modifiedByName = modifiedByName == null ? null : modifiedByName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.modified_time
     *
     * @return the value of p_shop_category.modified_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.modified_time
     *
     * @param modifiedTime the value for p_shop_category.modified_time
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column p_shop_category.status
     *
     * @return the value of p_shop_category.status
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column p_shop_category.status
     *
     * @param status the value for p_shop_category.status
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}