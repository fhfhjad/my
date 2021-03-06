package com.znc.mycrawler.dao;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttr;
import com.znc.mycrawler.domain.ProductMobileAccessoryAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMobileAccessoryAttrMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int countByExample(ProductMobileAccessoryAttrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int deleteByExample(ProductMobileAccessoryAttrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int insert(ProductMobileAccessoryAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int insertSelective(ProductMobileAccessoryAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    List<ProductMobileAccessoryAttr> selectByExample(ProductMobileAccessoryAttrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    ProductMobileAccessoryAttr selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByExampleSelective(@Param("record") ProductMobileAccessoryAttr record, @Param("example") ProductMobileAccessoryAttrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByExample(@Param("record") ProductMobileAccessoryAttr record, @Param("example") ProductMobileAccessoryAttrExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByPrimaryKeySelective(ProductMobileAccessoryAttr record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_mobile_accessory_attr
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByPrimaryKey(ProductMobileAccessoryAttr record);
}