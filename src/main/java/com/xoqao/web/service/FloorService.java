package com.xoqao.web.service;

import com.xoqao.web.bean.floors.Floor;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/20.
 * Email:dx96_j@163.com
 */
public interface FloorService {
    Floor findFloorBycount(String count)throws Exception;
}
