package liangliang.study.red.envelops.service;

import liangliang.study.red.envelops.entity.RedRecord;

public interface RedRecordService {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    int insert(RedRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    int insertSelective(RedRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    RedRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    int updateByPrimaryKeySelective(RedRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table red_record
     *
     * @mbg.generated Mon Aug 17 22:40:12 CST 2020
     */
    int updateByPrimaryKey(RedRecord record);


}
