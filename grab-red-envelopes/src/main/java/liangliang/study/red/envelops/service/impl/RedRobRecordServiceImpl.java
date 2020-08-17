package liangliang.study.red.envelops.service.impl;

import liangliang.study.red.envelops.entity.RedRobRecord;
import liangliang.study.red.envelops.mapper.RedRobRecordMapper;
import liangliang.study.red.envelops.service.RedRobRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedRobRecordServiceImpl implements RedRobRecordService {

    @Autowired
    private RedRobRecordMapper redRobRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return redRobRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RedRobRecord record) {
        return redRobRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(RedRobRecord record) {
        return redRobRecordMapper.insertSelective(record);
    }

    @Override
    public RedRobRecord selectByPrimaryKey(Integer id) {
        return redRobRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RedRobRecord record) {
        return redRobRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RedRobRecord record) {
        return redRobRecordMapper.updateByPrimaryKey(record);
    }
}
