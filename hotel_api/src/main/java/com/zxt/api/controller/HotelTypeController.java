package com.zxt.api.controller;

import com.zxt.common.result.R;
import com.zxt.common.result.Rt;
import com.zxt.common.util.PageUtil;
import com.zxt.hotel.pojo.HotelRoomTypeFullVO;
import com.zxt.hotel.pojo.HotelRoomTypeQuery;
import com.zxt.hotel.service.HotelRoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: 房型信息
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/18 17:53
 */
@RestController
@RequestMapping("/hotel/type")
@Api(tags = "房型信息")
public class HotelTypeController {

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @RequestMapping("/list")
    @ApiOperation(httpMethod = "GET", value = "房型列表，酒店主键必填")
    public Rt queryHotelTypeList(Long hotelInfoId, Integer page, Integer limit){
        if(hotelInfoId == null){
            return Rt.error(403,"酒店主键不能为空");
        }
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(hotelInfoId);
        return hotelRoomTypeService.queryHotelRoomTypeByPage(query,handle.getPage(),handle.getLimit());
    }

    @RequestMapping("/lists")
    @ApiOperation(httpMethod = "GET", value = "房型列表(详细)，酒店主键必填")
    public Rt queryHotelTypeFullList(Long hotelInfoId, Integer page, Integer limit){
        if(hotelInfoId == null){
            return Rt.error(403,"酒店主键不能为空");
        }
        PageUtil.PageDomain handle = PageUtil.handle(page, limit);
        HotelRoomTypeQuery query = new HotelRoomTypeQuery();
        query.setIsHotelId(hotelInfoId);
        return hotelRoomTypeService.queryHotelRoomTypeFullByPage(query,handle.getPage(),handle.getLimit());
    }

    @RequestMapping("/find-one")
    @ApiOperation(httpMethod = "GET", value = "根据主键查询房型信息")
    public R findOneById(Long roomTypeId){
        if(roomTypeId==null){
            return R.error(403,"主键不能为空");
        }
        HotelRoomTypeFullVO hotelRoomTypeFullVO = hotelRoomTypeService.findOneById(roomTypeId);
        return (hotelRoomTypeFullVO!=null)?R.ok("success",hotelRoomTypeFullVO):R.error();
    }

}
