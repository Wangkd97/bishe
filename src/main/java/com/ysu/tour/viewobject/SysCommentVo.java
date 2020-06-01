package com.ysu.tour.viewobject;

import java.util.List;

public class SysCommentVo {

    String strategyName;

    List<SysCommentMainInfoVo> list;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public List<SysCommentMainInfoVo> getList() {
        return list;
    }

    public void setList(List<SysCommentMainInfoVo> list) {
        this.list = list;
    }
}
