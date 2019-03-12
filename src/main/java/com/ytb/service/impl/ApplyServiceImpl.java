package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.ApplyDao;
import com.ytb.pojo.Apply;
import com.ytb.service.IApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplyServiceImpl implements IApplyService {

    @Resource
    private ApplyDao applyDao;

    @Override
    public ServerResponse insert(Integer userId) {

        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setApplyStatus(Const.ApplyStatusEnum.UN_HANDLE.getCode());

        int result = applyDao.insert(apply);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess("提交成功");
        }

        return ServerResponse.serverResponseByFail(1,"提交失败");
    }

    @Override
    public ServerResponse selectAllByUserId(Integer userId) {

        List<Apply> applyList = applyDao.selectAllByUserId(userId);
        if (applyList == null || applyList.size() == 0){
            return ServerResponse.serverResponseByFail("该用户未提交过申请");
        }

        return ServerResponse.serverResponseBySuccess(applyList);
    }

    @Override
    public ServerResponse selectAll() {

        List<Apply> applyList = applyDao.selectAll();

        if (applyList != null && applyList.size() >= 0){
            return ServerResponse.serverResponseBySuccess(applyList);
        }

        return ServerResponse.serverResponseByFail();
    }
}
