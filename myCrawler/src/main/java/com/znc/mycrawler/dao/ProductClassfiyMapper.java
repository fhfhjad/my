package com.znc.mycrawler.dao;

import com.znc.mycrawler.dao.util.BaseDao;
import com.znc.mycrawler.domain.ProductClassfiy;
import com.znc.mycrawler.domain.ProductClassfiyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductClassfiyMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int countByExample(ProductClassfiyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int deleteByExample(ProductClassfiyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int deleteByPrimaryKey(Long cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int insert(ProductClassfiy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int insertSelective(ProductClassfiy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    List<ProductClassfiy> selectByExample(ProductClassfiyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    ProductClassfiy selectByPrimaryKey(Long cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByExampleSelective(@Param("record") ProductClassfiy record, @Param("example") ProductClassfiyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByExample(@Param("record") ProductClassfiy record, @Param("example") ProductClassfiyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByPrimaryKeySelective(ProductClassfiy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_classfiy
     *
     * @mbggenerated Sat Apr 25 13:33:15 CST 2015
     */
    int updateByPrimaryKey(ProductClassfiy record);
}