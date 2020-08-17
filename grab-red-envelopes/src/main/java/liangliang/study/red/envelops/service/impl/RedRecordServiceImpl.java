package liangliang.study.red.envelops.service.impl;

import liangliang.study.red.envelops.entity.RedRecord;
import liangliang.study.red.envelops.mapper.RedRecordMapper;
import liangliang.study.red.envelops.service.RedRecordService;
import org.springframework.stereotype.Service;

@Service
public class RedRecordServiceImpl implements RedRecordService {

    private RedRecordMapper redRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return redRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RedRecord record) {
        return redRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(RedRecord record) {
        return redRecordMapper.insertSelective(record);
    }

    @Override
    public RedRecord selectByPrimaryKey(Integer id) {
        return redRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RedRecord record) {
        return redRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RedRecord record) {
        return redRecordMapper.updateByPrimaryKeySelective(record);
    }
}
