package liangliang.study.red.envelops.service;

import liangliang.study.red.envelops.entity.RedDetail;

public interface RedDetailService {

    int deleteByPrimaryKey(Integer id);
    int insert(RedDetail record);
    int insertSelective(RedDetail record);
    RedDetail selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(RedDetail record);
    int updateByPrimaryKey(RedDetail record);

}
