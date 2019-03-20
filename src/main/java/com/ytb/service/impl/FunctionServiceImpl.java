package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.FunctionDao;
import com.ytb.pojo.Function;
import com.ytb.pojo.User;
import com.ytb.service.IFunctionService;
import com.ytb.vo.FunctionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FunctionServiceImpl implements IFunctionService {

    @Resource
    private FunctionDao functionDao;

    @Override
    public ServerResponse home(User user) {

        if (user == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        List<Function> functionList = functionDao.selectByParentIdAndRole(0,user.getRole());
        List<FunctionVO> functionVOList = getFunctionVOList(functionList,user.getRole());

        if (functionVOList != null && functionVOList.size() != 0){
            Map<String,Object> map = new HashMap<>();
            map.put("user",user);
            map.put("functionVOList",functionVOList);

            return ServerResponse.serverResponseBySuccess(map);
        }
        return ServerResponse.serverResponseByFail("获取功能列表失败");
    }


    private List<FunctionVO> getFunctionVOList(List<Function> functionList,Integer role){
        List<FunctionVO> functionVOList = new ArrayList<>();

        if (functionList != null && functionList.size() != 0){
            for (Function function:functionList
            ) {
                FunctionVO functionVO = new FunctionVO();
                BeanUtils.copyProperties(function,functionVO);

                List<Function> functionList1 = functionDao.selectByParentIdAndRole(function.getFunctionId(),role);

                functionVO.setFunctionVOList(getFunctionVOList(functionList1,role));
                functionVOList.add(functionVO);
            }
        }

        return functionVOList;

    }

}
