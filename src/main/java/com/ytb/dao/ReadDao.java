package com.ytb.dao;

import com.ytb.pojo.Read;

import java.util.List;

public interface ReadDao {

    int insert(Read read);

    Read selectByKey(Integer readId);

    int update(Read read);

    List<Read> selectAllByPaperId(Integer paperId);

}
