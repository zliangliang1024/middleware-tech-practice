package liangliang.study.red.envelops.service.impl;

import liangliang.study.red.envelops.entity.RedDetail;
import liangliang.study.red.envelops.mapper.RedDetailMapper;
import liangliang.study.red.envelops.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedDetailServiceImpl implements RedDetailService {

    @Autowired
    private RedDetailMapper redDetailMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return redDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RedDetail record) {
        return redDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(RedDetail record) {
        return redDetailMapper.insertSelective(record);
    }

    @Override
    public RedDetail selectByPrimaryKey(Integer id) {
        return redDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RedDetail record) {
        return redDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RedDetail record) {
        return redDetailMapper.updateByPrimaryKeySelective(record);
    }
}
