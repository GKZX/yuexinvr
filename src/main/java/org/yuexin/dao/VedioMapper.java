package org.yuexin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioExample;

public interface VedioMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int countByExample(VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int deleteByExample(VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int insert(Vedio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int insertSelective(Vedio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    List<Vedio> selectByExampleWithBLOBs(VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    List<Vedio> selectByExample(VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    Vedio selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByExampleSelective(@Param("record") Vedio record, @Param("example") VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByExampleWithBLOBs(@Param("record") Vedio record, @Param("example") VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByExample(@Param("record") Vedio record, @Param("example") VedioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByPrimaryKeySelective(Vedio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(Vedio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vedio
     *
     * @mbggenerated Wed Dec 14 10:25:31 CST 2016
     */
    int updateByPrimaryKey(Vedio record);
}