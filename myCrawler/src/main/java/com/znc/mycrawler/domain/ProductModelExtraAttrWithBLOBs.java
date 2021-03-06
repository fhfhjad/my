package com.znc.mycrawler.domain;

import java.io.Serializable;

public class ProductModelExtraAttrWithBLOBs extends ProductModelExtraAttr implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_model_extra_attr.box_list
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    private String boxList;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_model_extra_attr.sold_server
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    private String soldServer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_model_extra_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model_extra_attr.box_list
     *
     * @return the value of product_model_extra_attr.box_list
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    public String getBoxList() {
        return boxList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model_extra_attr.box_list
     *
     * @param boxList the value for product_model_extra_attr.box_list
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    public void setBoxList(String boxList) {
        this.boxList = boxList == null ? null : boxList.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model_extra_attr.sold_server
     *
     * @return the value of product_model_extra_attr.sold_server
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    public String getSoldServer() {
        return soldServer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model_extra_attr.sold_server
     *
     * @param soldServer the value for product_model_extra_attr.sold_server
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    public void setSoldServer(String soldServer) {
        this.soldServer = soldServer == null ? null : soldServer.trim();
    }
}