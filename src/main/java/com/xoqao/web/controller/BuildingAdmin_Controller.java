package com.xoqao.web.controller;

import com.xoqao.web.bean.booking.BookingSeat;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.service.BookingService;
import com.xoqao.web.service.BuildingService;
import com.xoqao.web.service.FloorService;
import com.xoqao.web.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/19.
 */
@Controller
@RequestMapping("/view")
public class BuildingAdmin_Controller {

    @Autowired
    private FloorService floorService;

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private BookingService bookingService;

    @RequestMapping("/index_BuildingAdmin")
    public String index_BuildingAdmin(Model model) throws Exception {
        return "buildingadmin_page/Index_BuildingAdmin";
    }

    @RequestMapping("/leftmenu_BuildingAdmin")
    public String leftmenu_BuildingAdmin(Model model, HttpSession httpSession) throws Exception {
        Building building = (Building) httpSession.getAttribute("admin");
        List<Floor> floors = floorService.findfloorsBybid(building.getBid());
        model.addAttribute("floors", floors.size());
        Integer seatSize = 0;
        Integer seatnow = 0;
        Integer noseat = 0;
        for (int i = 0; i < floors.size(); i++) {
            List<Seat> seatsByFid = seatService.findSeatsByFid(floors.get(i).getFid());
            seatSize = seatSize + seatsByFid.size();
            List<BookingSeat> seatInSeat = bookingService.findSeatInSeat(floors.get(i).getFid());
            seatnow = seatnow + seatInSeat.size();
            List<Seat> canBookingToday = bookingService.findCanBookingToday(floors.get(i).getFid());
            noseat = noseat + canBookingToday.size();
        }
        model.addAttribute("seatSize", seatSize);
        model.addAttribute("seatnow", seatnow);
        model.addAttribute("noseat", noseat);
        return "buildingadmin_page/Leftmenu_BuildingAdmin";
    }

    @RequestMapping("/main_BuildingAdmin")
    public String main_BuildingAdmin(Model model) throws Exception {

        return "buildingadmin_page/Main_BuildingAdmin";
    }

    @RequestMapping("/managing_Floor_BuildingAdmin")
    public String managing_Floor_BuildingAdmin(Model model, HttpSession httpSession) throws Exception {
        Building admin = (Building) httpSession.getAttribute("admin");
        List<Floor> floors = floorService.findfloorsBybid(admin.getBid());
        if (floors.size() > 0) {
            model.addAttribute("floors", floors);
        } else {
            model.addAttribute("nullList", "您暂无可管理楼层！");
        }
        return "buildingadmin_page/Managing_Floor_BuildingAdmin";
    }

    /**
     * 更改楼层状态
     *
     * @param model
     * @param statue
     * @param fid
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/floor/changestatue")
    public String changeStatue(Model model, Integer statue, Integer fid, HttpSession httpSession) throws Exception {
        Building admin = (Building) httpSession.getAttribute("admin");
        if (statue == 0) {
            floorService.updateStatueByid(1, fid);
        } else {
            floorService.updateStatueByid(0, fid);
        }
        List<Floor> floors = floorService.findfloorsBybid(admin.getBid());
        if (floors.size() > 0) {
            model.addAttribute("floors", floors);
        } else {
            model.addAttribute("nullList", "您暂无可管理楼层！");
        }
        return "buildingadmin_page/Managing_Floor_BuildingAdmin";
    }


    @RequestMapping("/managing_User_BuildingAdmin")
    public String managing_User_BuildingAdmin(Model model) throws Exception {
        return "buildingadmin_page/Managing_User_BuildingAdmin";
    }

}
