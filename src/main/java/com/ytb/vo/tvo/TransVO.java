package com.ytb.vo.tvo;

import com.ytb.common.Const;
import com.ytb.dao.UserDao;
import com.ytb.pojo.Apply;
import com.ytb.pojo.Paper;
import com.ytb.pojo.Read;
import com.ytb.pojo.User;
import com.ytb.utils.DateUtils;
import com.ytb.vo.ApplyVO;
import com.ytb.vo.PaperVO;
import com.ytb.vo.ReadVO;
import com.ytb.vo.UserVO;
import org.springframework.beans.BeanUtils;

public class TransVO {

    public static PaperVO transPaperVO(Paper paper,User user){
        PaperVO paperVO = new PaperVO();

        BeanUtils.copyProperties(paper,paperVO);
        if (user != null){
            paperVO.setUsername(user.getUsername());
        }
        paperVO.setPaperStatusDesc(Const.PaperStatusEnum.codeOf(paper.getPaperStatus()).getMsg());
        paperVO.setCreateTime(DateUtils.dateToStr(paper.getCreateTime()));
        paperVO.setUpdateTime(DateUtils.dateToStr(paper.getUpdateTime()));

        return paperVO;
    }

    public static ReadVO transReadVO(Read read){
        ReadVO readVO = new ReadVO();

        BeanUtils.copyProperties(read,readVO);
        readVO.setReadStatusDesc(Const.ReadStatusEnum.codeOf(read.getReadStatus()).getMsg());
        readVO.setCreateTime(DateUtils.dateToStr(read.getCreateTime()));
        readVO.setUpdateTime(DateUtils.dateToStr(read.getUpdateTime()));

        return readVO;
    }

    public static UserVO transUserVO(User user){
        UserVO userVO = new UserVO();

        BeanUtils.copyProperties(user,userVO);
        userVO.setCreateTime(DateUtils.dateToStr(user.getCreateTime()));
        userVO.setUpdateTime(DateUtils.dateToStr(user.getUpdateTime()));
        userVO.setRoleDesc(Const.PowerEnum.codeOf(user.getRole()).getMsg());

        return userVO;
    }

    public static ApplyVO transApplyVO(Apply apply){
        ApplyVO applyVO = new ApplyVO();

        BeanUtils.copyProperties(apply,applyVO);
        applyVO.setApplyStatusDesc(Const.ApplyStatusEnum.codeOf(apply.getApplyStatus()).getMsg());
        applyVO.setCreateTime(DateUtils.dateToStr(apply.getCreateTime()));
        applyVO.setUpdateTime(DateUtils.dateToStr(apply.getUpdateTime()));

        return applyVO;
    }

}
