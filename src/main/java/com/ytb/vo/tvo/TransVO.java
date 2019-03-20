package com.ytb.vo.tvo;

import com.ytb.common.Const;
import com.ytb.pojo.Paper;
import com.ytb.pojo.Read;
import com.ytb.pojo.User;
import com.ytb.utils.DateUtils;
import com.ytb.vo.PaperVO;
import com.ytb.vo.ReadVO;
import com.ytb.vo.UserVO;
import org.springframework.beans.BeanUtils;

public class TransVO {

    public static PaperVO transPaperVO(Paper paper){
        PaperVO paperVO = new PaperVO();

        BeanUtils.copyProperties(paper,paperVO);
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

}
