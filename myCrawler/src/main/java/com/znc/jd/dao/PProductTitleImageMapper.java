package com.znc.jd.dao;

import com.znc.jd.dao.util.BaseDao;
import com.znc.jd.domain.PProductTitleImage;
import com.znc.jd.domain.PProductTitleImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PProductTitleImageMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int countByExample(PProductTitleImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int deleteByExample(PProductTitleImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int deleteByPrimaryKey(Long imageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int insert(PProductTitleImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int insertSelective(PProductTitleImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    List<PProductTitleImage> selectByExample(PProductTitleImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    PProductTitleImage selectByPrimaryKey(Long imageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int updateByExampleSelective(@Param("record") PProductTitleImage record, @Param("example") PProductTitleImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int updateByExample(@Param("record") PProductTitleImage record, @Param("example") PProductTitleImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int updateByPrimaryKeySelective(PProductTitleImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table p_product_title_image
     *
     * @mbggenerated Mon Jan 18 10:40:03 CST 2016
     */
    int updateByPrimaryKey(PProductTitleImage record);
}