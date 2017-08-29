package com.xoqao.web.controller;

import com.mysql.jdbc.CacheAdapter;
import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.building.Building;
import com.xoqao.web.bean.code.CodeModel;
import com.xoqao.web.bean.code.FontSide;
import com.xoqao.web.bean.data.MonthData;
import com.xoqao.web.bean.data.WeekData;
import com.xoqao.web.bean.floors.Floor;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.seat.SeatCus;
import com.xoqao.web.bean.user.User;
import com.xoqao.web.bean.userbook.UserLearn;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.*;
import com.xoqao.web.utils.CodeCreator;
import com.xoqao.web.utils.DateUtil;
import com.xoqao.web.utils.MD5Util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1Q84 on 2017/7/12.
 */
@Controller
@RequestMapping("/view")
public class Admin_Controller {


    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FloorService floorService;


    @Autowired
    private WeekOpenService weekOpenService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/seat_Now")
    public String seat_Now(Model model) throws Exception {
        return "admin_page/Seat_Now";
    }


    @RequestMapping("/setNewTime")
    public String setNewTime(Model model) throws Exception {
        return "admin_page/SetNewTime";
    }

    @RequestMapping("/managing_Seat")
    public String managing_Seat(Model model, Integer fid) throws Exception {
        model.addAttribute("fid", fid);
        return "admin_page/Managing_Seat";
    }

    /**
     * 查看管理楼层内座位
     *
     * @param model
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/floorSeat")
    public String seatList(Model model, Integer page, HttpSession httpSession, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        List<Seat> seatsByFid = seatService.findSeatsByFid(floor.getFid());
        Integer pageSize = 10;
        if (seatsByFid.size() > 0) {
            model.addAttribute("seatSize", seatsByFid.size());
            int pageTims;
            if (seatsByFid.size() % pageSize == 0) {
                pageTims = seatsByFid.size() / pageSize;
            } else {
                pageTims = seatsByFid.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值

            if (null == page) {
                page = 1;
            }

            //每页开始的第几条记录
            int startRow;
            if (seatsByFid.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Seat> seatsByFidPage = seatService.findSeatsByFidPage(floor.getFid(), startRow, pageSize);
            List<SeatCus> seatCuses = new ArrayList<SeatCus>();
            for (int i = 0; i < seatsByFidPage.size(); i++) {
                SeatCus seatCus = new SeatCus();
                //将列表中的seat中的属性复制到seatcus中
                BeanUtils.copyProperties(seatsByFidPage.get(i), seatCus);
                Floor floor1 = floorService.findfloorByid(seatsByFidPage.get(i).getFid());

                Building buildingById = buildingService.findBuildingById(floor1.getBid());
                seatCus.setFloor(floor1.getEmployer());
                seatCus.setBuilding(buildingById.getEmployer());
                seatCuses.add(seatCus);
            }
            model.addAttribute("seats", seatCuses);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无座位信息");
        }
        model.addAttribute("fid", fid);
        return "admin_page/Seat_Floor_List_Admin";
    }


    @RequestMapping("/floorSeatsList")
    public String seatFloorList(Model model, Integer page, HttpSession httpSession, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        List<Seat> seatsByFid = seatService.findSeatsByFid(floor.getFid());
        Integer pageSize = 10;
        if (seatsByFid.size() > 0) {
            model.addAttribute("seatSize", seatsByFid.size());
            int pageTims;
            if (seatsByFid.size() % pageSize == 0) {
                pageTims = seatsByFid.size() / pageSize;
            } else {
                pageTims = seatsByFid.size() / pageSize + 1;
            }
            httpSession.setAttribute("pageTimes", pageTims);
            //页面初始的时候没有初试值

            if (null == page) {
                page = 1;
            }

            //每页开始的第几条记录
            int startRow;
            if (seatsByFid.size() < pageSize) {
                startRow = 0;
            } else {
                startRow = (page - 1) * pageSize;
            }
            model.addAttribute("currentPage", page);
            List<Seat> seatsByFidPage = seatService.findSeatsByFidPage(floor.getFid(), startRow, pageSize);
            List<SeatCus> seatCuses = new ArrayList<SeatCus>();
            for (int i = 0; i < seatsByFidPage.size(); i++) {
                SeatCus seatCus = new SeatCus();
                //将列表中的seat中的属性复制到seatcus中
                BeanUtils.copyProperties(seatsByFidPage.get(i), seatCus);
                Floor floor1 = floorService.findfloorByid(seatsByFidPage.get(i).getFid());

                Building buildingById = buildingService.findBuildingById(floor1.getBid());
                seatCus.setFloor(floor1.getEmployer());
                seatCus.setBuilding(buildingById.getEmployer());
                seatCuses.add(seatCus);
            }
            model.addAttribute("seats", seatCuses);
        } else {
            httpSession.setAttribute("pageTimes", 1);
            model.addAttribute("nullList", "暂无座位信息");
        }
        model.addAttribute("fid", fid);
        return "admin_page/Seat_List_Admin";
    }

    /**
     * 更改座位状态
     *
     * @param model
     * @param statue
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeSeatStatue")
    public String changeSeatSatue(Model model, Integer statue, Integer sid, Integer fid) throws Exception {
        if (statue == 0) {
            seatService.updateSeatSatue(1, sid);
        } else {
            seatService.updateSeatSatue(0, sid);
        }
        return "redirect:/view/floorSeatsList?page=1&fid=" + fid;
    }

    /**
     * 删除某个座位
     *
     * @param model
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteSeat")
    public String deleteSeat(Model model, Integer sid, Integer fid) throws Exception {
        seatService.deleteSeat(sid);
        return "redirect:/view/floorSeatsList?page=1&fid=" + fid;
    }

    /**
     * 查看下载二维码
     *
     * @param model
     * @param seatNumber
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/getSeatPic")
    public void GetSeatPic(Model model, String seatNumber, Integer type, HttpServletResponse httpServletResponse) throws Exception {
        Seat seatBynumber = seatService.findSeatBynumber(seatNumber);
        Floor floor = floorService.findfloorByid(seatBynumber.getFid());
        Building buildingById = buildingService.findBuildingById(floor.getBid());
        //生成二维码并下载到本地
        CodeCreator creator = new CodeCreator();
        CodeModel info = new CodeModel();
        info.setContents(seatBynumber.getSeatnumber());
        info.setLogoFile(new File(CommenValue.SCHOOL_EMBLEM));
        String leftside = null;
        if (seatBynumber.getLeftside() == 0) {
            leftside = "左";
        } else {
            leftside = "右";
        }
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + new String((seatBynumber.getSeatnumber() + "." + info.getFormat()).getBytes("gbk"), "ISO8859-1"));
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        switch (type) {
            case 1:
                info.setWidth(330);
                info.setHeight(330);
                info.setFontSize(24);
                FontSide building1 = new FontSide();  //楼层描述
                building1.setColor(0x7E3805);
                building1.setDes(buildingById.getEmployer());
                building1.setFontsize(36);
                building1.setStartx(40);
                building1.setStarty(690);

                FontSide room1 = new FontSide();  //场馆描述
                room1.setColor(0x7E3805);
                room1.setDes(floor.getEmployer());
                room1.setFontsize(36);
                room1.setStartx(325);
                room1.setStarty(690);

                FontSide location1 = new FontSide();  //位置描述
                location1.setColor(0x000000);
                location1.setFontsize(48);
                location1.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location1.setStartx(195);
                location1.setStarty(570);

                info.setFontSideLocal(location1);
                info.setFontSideRoom(room1);
                info.setFontSideBuiding(building1);
                info.setCodestart(new int[]{160, 130});
                info.setBackimg(new File(CommenValue.CODE_XIAOPOHAI));
                creator.createCodeImgTheme(info, outputStream);
                break;
            case 2:
                info.setWidth(125);
                info.setHeight(125);
                info.setFontSize(24);
                FontSide building2 = new FontSide();  //楼层描述
                building2.setColor(0xFFFFFF);
                building2.setDes(buildingById.getEmployer());
                building2.setFontsize(18);
                building2.setStartx(30);
                building2.setStarty(437);

                FontSide room2 = new FontSide();  //场馆描述
                room2.setColor(0xFFFFFF);
                room2.setDes(floor.getEmployer());
                room2.setFontsize(18);
                room2.setStartx(175);
                room2.setStarty(437);

                FontSide location2 = new FontSide();  //位置描述
                location2.setColor(0x5e7d91);
                location2.setFontsize(36);
                location2.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location2.setStartx(80);
                location2.setStarty(55);

                info.setFontSideLocal(location2);
                info.setFontSideRoom(room2);
                info.setFontSideBuiding(building2);
                info.setCodestart(new int[]{112, 217});
                info.setBackimg(new File(CommenValue.CODE_BLUSKY));
                creator.createCodeImgTheme(info, outputStream);
                break;

            case 3:
                info.setWidth(140);
                info.setHeight(140);
                info.setFontSize(24);
                FontSide building3 = new FontSide();  //楼层描述
                building3.setColor(0xFFFFFF);
                building3.setDes(buildingById.getEmployer());
                building3.setFontsize(20);
                building3.setStartx(75);
                building3.setStarty(522);

                FontSide room3 = new FontSide();  //场馆描述
                room3.setColor(0xFFFFFF);
                room3.setDes(floor.getEmployer());
                room3.setFontsize(20);
                room3.setStartx(80);
                room3.setStarty(552);

                FontSide location3 = new FontSide();  //位置描述
                location3.setColor(0xFFFFFF);
                location3.setFontsize(30);
                location3.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location3.setStartx(50);
                location3.setStarty(270);

                info.setFontSideLocal(location3);
                info.setFontSideRoom(room3);
                info.setFontSideBuiding(building3);
                info.setCodestart(new int[]{80, 350});
                info.setBackimg(new File(CommenValue.CODE_TAG));
                creator.createCodeImgTheme(info, outputStream);
                break;
            case 4:
                info.setWidth(235);
                info.setHeight(235);
                info.setFontSize(24);
                FontSide building4 = new FontSide();  //楼层描述
                building4.setColor(0xec5801);
                building4.setDes(buildingById.getEmployer());
                building4.setFontsize(20);
                building4.setStartx(70);
                building4.setStarty(150);

                FontSide room4 = new FontSide();  //场馆描述
                room4.setColor(0xec5801);
                room4.setDes(floor.getEmployer());
                room4.setFontsize(20);
                room4.setStartx(287);
                room4.setStarty(150);

                FontSide location4 = new FontSide();  //位置描述
                location4.setColor(0xa02503);
                location4.setFontsize(30);
                location4.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location4.setStartx(165);
                location4.setStarty(60);

                info.setFontSideLocal(location4);
                info.setFontSideRoom(room4);
                info.setFontSideBuiding(building4);
                info.setCodestart(new int[]{148, 218});
                info.setBackimg(new File(CommenValue.CODE_AIGUO));
                creator.createCodeImgTheme(info, outputStream);
                break;
            case 5:

                info.setWidth(218);
                info.setHeight(218);
                info.setFontSize(24);
                FontSide building5 = new FontSide();  //楼层描述
                building5.setColor(0xFFFFFF);
                building5.setDes(buildingById.getEmployer());
                building5.setFontsize(14);
                building5.setStartx(50);
                building5.setStarty(663);

                FontSide room5 = new FontSide();  //场馆描述
                room5.setColor(0xFFFFFF);
                room5.setDes(floor.getEmployer());
                room5.setFontsize(14);
                room5.setStartx(285);
                room5.setStarty(663);

                FontSide location5 = new FontSide();  //位置描述
                location5.setColor(0xFFFFFF);
                location5.setFontsize(30);
                location5.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location5.setStartx(150);
                location5.setStarty(80);

                info.setFontSideLocal(location5);
                info.setFontSideRoom(room5);
                info.setFontSideBuiding(building5);
                info.setCodestart(new int[]{130, 400});
                info.setBackimg(new File(CommenValue.CODE_BOY));
                creator.createCodeImgTheme(info, outputStream);
                break;
            case 6:
                info.setWidth(204);
                info.setHeight(204);
                info.setFontSize(24);
                FontSide building6 = new FontSide();  //楼层描述
                building6.setColor(0x000000);
                building6.setDes(buildingById.getEmployer());
                building6.setFontsize(18);
                building6.setStartx(29);
                building6.setStarty(540);

                FontSide room6 = new FontSide();  //场馆描述
                room6.setColor(0x000000);
                room6.setDes(floor.getEmployer());
                room6.setFontsize(18);
                room6.setStartx(296);
                room6.setStarty(540);

                FontSide location6 = new FontSide();  //位置描述
                location6.setColor(0x000000);
                location6.setFontsize(36);
                location6.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location6.setStartx(110);
                location6.setStarty(60);

                info.setFontSideLocal(location6);
                info.setFontSideRoom(room6);
                info.setFontSideBuiding(building6);
                info.setCodestart(new int[]{147, 212});
                info.setBackimg(new File(CommenValue.CODE_KNOT));
                creator.createCodeImgTheme(info, outputStream);
                break;

            case 7:
                info.setWidth(275);
                info.setHeight(275);
                info.setFontSize(24);
                FontSide building7 = new FontSide();  //楼层描述
                building7.setColor(0x6aa839);
                building7.setDes(buildingById.getEmployer());
                building7.setFontsize(20);
                building7.setStartx(25);
                building7.setStarty(545);

                FontSide room7 = new FontSide();  //场馆描述
                room7.setColor(0x6aa839);
                room7.setDes(floor.getEmployer());
                room7.setFontsize(20);
                room7.setStartx(270);
                room7.setStarty(545);

                FontSide location7 = new FontSide();  //位置描述
                location7.setColor(0xFFFFFF);
                location7.setFontsize(48);
                location7.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location7.setStartx(85);
                location7.setStarty(110);

                info.setFontSideLocal(location7);
                info.setFontSideRoom(room7);
                info.setFontSideBuiding(building7);
                info.setCodestart(new int[]{85, 225});
                info.setBackimg(new File(CommenValue.CODE_GREEN));
                creator.createCodeImgTheme(info, outputStream);
                break;
            case 8:
                info.setWidth(244);
                info.setHeight(244);
                info.setFontSize(24);
                FontSide building8 = new FontSide();  //楼层描述
                building8.setColor(0x000000);
                building8.setDes(buildingById.getEmployer());
                building8.setFontsize(24);
                building8.setStartx(74);
                building8.setStarty(594);

                FontSide room8 = new FontSide();  //场馆描述
                room8.setColor(0x000000);
                room8.setDes(floor.getEmployer());
                room8.setFontsize(24);
                room8.setStartx(250);
                room8.setStarty(594);

                FontSide location8 = new FontSide();  //位置描述
                location8.setColor(0x000000);
                location8.setFontsize(42);
                location8.setDes(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
                location8.setStartx(113);
                location8.setStarty(70);

                info.setFontSideLocal(location8);
                info.setFontSideRoom(room8);
                info.setFontSideBuiding(building8);
                info.setCodestart(new int[]{134, 240});
                info.setBackimg(new File(CommenValue.CODE_CLOUDS));
                creator.createCodeImgTheme(info, outputStream);

                break;
            default:
                info.setWidth(330);
                info.setHeight(330);
                info.setFontSize(24);

                info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer() + "\n                    " + leftside + "侧 " + seatBynumber.getRow() + " 排 " + seatBynumber.getColumns() + " 列");

                creator.createCodeImage(info, outputStream);
//                outputStream.flush();
//                outputStream.close();
                break;
        }

////        info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer() + "\n                    " + leftside + "侧 " + seatBynumber.getRow() + " 排 " + seatBynumber.getColumns() + " 列");
//        info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer());
//        info.setLocation(leftside + "侧" + seatBynumber.getRow() + "排" + seatBynumber.getColumns() + "列");
//        //info.setLogoDesc("一叶浮萍归大海，adsasfbhtjg人生何处不相逢");
//        //info.setLogoDesc("一叶浮萍");
////        creator.createCodeImage(info, CommenValue.CODEPATH + seatBynumber.getSeatnumber() + "." + info.getFormat());
//        httpServletResponse.setContentType("image/jpeg");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + new String((seatBynumber.getSeatnumber() + "." + info.getFormat()).getBytes("gbk"), "ISO8859-1"));
//        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
////        creator.createCodeImage(info, outputStream);
//        creator.createCodeImgXiaoPoHai(info, outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 提交座位添加
     *
     * @param model
     * @param left
     * @param row
     * @param column
     * @param mark
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSeatSub")
    public String Seatadd(Model model, Integer left, Integer row, Integer column, String mark, Integer fid, HttpServletResponse httpServletResponse, RedirectAttributes redirectAttributes) throws Exception {
        if (left != null && row != null && column != null && fid != null) {


            Floor floor = floorService.findfloorByid(fid);
            //添加座位前需要先查看每周的开放时间是否已经设置完成
            List<WeekOpen> weekOpens = weekOpenService.findweekByfid(floor.getFid());
            if (weekOpens.size() != 7) {
                model.addAttribute("error_msg", "请先添加每周的开放时间段.");
                redirectAttributes.addFlashAttribute("error_msg", "请先添加每周的开放时间段");
                return "redirect:/view/managing_Floor?fid=" + fid;
            }
            Building buildingById = buildingService.findBuildingById(floor.getBid());
            Seat seat = new Seat();
            seat.setColumns(column);
            seat.setFid(floor.getFid());
            seat.setLeftside(left);
            seat.setRow(row);
            String number = floor.getBid() + String.format("%02d", floor.getFloor()) + String.format("%02d", floor.getFid()) + left + String.format("%02d", row) + String.format("%02d", column);
            seat.setSeatnumber(number);
            try {
                seatService.insertSeat(seat);
                model.addAttribute("error_msg", "添加成功");
                //生成二维码并下载到本地
                CodeCreator creator = new CodeCreator();
                CodeModel info = new CodeModel();
                info.setWidth(300);
                info.setHeight(300);
                info.setFontSize(24);
                //info.setContents("<a href='http://www.sohu.com'>人生就是拼搏</a>");
                //info.setContents("http://www.sohu.com");
                info.setContents(number);
                info.setLogoFile(new File(CommenValue.SCHOOL_EMBLEM));
                String leftside = null;
                if (seat.getLeftside() == 0) {
                    leftside = "左";
                } else {
                    leftside = "右";
                }
                info.setDesc(buildingById.getEmployer() + "\n       " + floor.getEmployer() + "\n                  " + leftside + "侧 " + seat.getRow() + " 排 " + seat.getColumns() + " 列");
                //info.setLogoDesc("一叶浮萍归大海，adsasfbhtjg人生何处不相逢");
                //info.setLogoDesc("一叶浮萍");
//            creator.createCodeImage(info, CommenValue.CODEPATH + number + "." + info.getFormat());
                httpServletResponse.setContentType("image/jpeg");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setHeader("Content-Disposition", "attachment;fileName=" + new String((number + "." + info.getFormat()).getBytes("gbk"), "ISO8859-1"));
                ServletOutputStream outputStream = httpServletResponse.getOutputStream();
                creator.createCodeImage(info, outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("fid", fid);
                model.addAttribute("error_msg", "该座位已存在");
                return "admin_page/Managing_Seat";
            }
            return "redirect:/view/floorSeat?page=1&fid=" + fid;
        } else {
            model.addAttribute("fid", fid);
            model.addAttribute("error_msg", "请选择必要的参数");
            return "admin_page/Managing_Seat";
        }
    }

    @RequestMapping("/addColumsSeat")
    public String SeataddColums(Model model, Integer left, Integer row, Integer column, Integer fid, HttpServletResponse httpServletResponse, RedirectAttributes redirectAttributes) throws Exception {
        if (left != null && row != null && column != null && fid != null) {
            Floor floor = floorService.findfloorByid(fid);
            //添加座位前需要先查看每周的开放时间是否已经设置完成
            List<WeekOpen> weekOpens = weekOpenService.findweekByfid(floor.getFid());
            if (weekOpens.size() != 7) {
                model.addAttribute("error_msg", "请先添加每周的开放时间段.");
                redirectAttributes.addFlashAttribute("error_msg", "请先添加每周的开放时间段");
                return "redirect:/view/managing_Floor?fid=" + fid;
            }
            int suc=0;
            int faild=0;
            for (int i = 1; i <=column; i++) {
                Seat seat = new Seat();
                seat.setColumns(i);
                seat.setFid(floor.getFid());
                seat.setLeftside(left);
                seat.setRow(row);
                String number = floor.getBid() + String.format("%02d", floor.getFloor()) + String.format("%02d", floor.getFid()) + left + String.format("%02d", row) + String.format("%02d", i);
                seat.setSeatnumber(number);
                try {
                    seatService.insertSeat(seat);
                    suc++;
                }catch (Exception e){
                    e.printStackTrace();
                    faild++;
                }
            }
            model.addAttribute("error_msg", "添加成功"+suc+"个，失败"+faild+"个!");
            redirectAttributes.addFlashAttribute("error_msg", "添加成功"+suc+"个，失败"+faild+"个!");
            return "redirect:/view/floorSeatsList?fid=" + fid;
        }else {
            model.addAttribute("fid", fid);
            model.addAttribute("error_msg", "请选择必要的参数");
            return "admin_page/Managing_Seat";
        }
    }

    /**
     * 进入设置开放时间
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/managing_Floor")
    public String mnaging_Floor(Model model, Integer fid) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        List<WeekOpen> weekOpens = weekOpenService.findweekByfid(floor.getFid());
        if (weekOpens.size() == 0) {
            model.addAttribute("NullList", "暂无一周当中的预约日期设置");
        }
        model.addAttribute("weekopens", weekOpens);
        model.addAttribute("fid", fid);
        return "admin_page/Managing_Floor";
    }

    /**
     * 提交完成添加
     *
     * @param model
     * @param httpSession
     * @param week
     * @param param1
     * @param param2
     * @return
     * @throws Exception
     */
    @RequestMapping("/addWeekOpenSub")
    public String addWeekOpen(Model model, HttpSession httpSession, Integer week, String param1, String param2, Integer fid, RedirectAttributes redirectAttributes) throws Exception {
        Floor floor = floorService.findfloorByid(fid);
        WeekOpen weekOpen = new WeekOpen();
        weekOpen.setLid(floor.getFid());
        weekOpen.setWeek(week);
        weekOpen.setParam1(param1);
        weekOpen.setParam2(param2);
        try {
            weekOpenService.insertweek(weekOpen);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "周" + week + "的已存在");
            redirectAttributes.addFlashAttribute("error_msg", "周" + week + "的已存在");
        }
        return "redirect:/view/managing_Floor?fid=" + fid;
    }

    /**
     * 改变一周中可预约状态
     *
     * @param model
     * @param woid
     * @param statue
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeWeekStatue")
    public String changeWeekSatue(Model model, Integer woid, Integer statue, Integer fid) throws Exception {
        if (statue == 0) {
            weekOpenService.updatestatue(1, woid);
        } else {
            weekOpenService.updatestatue(0, woid);
        }
        return "redirect:/view/managing_Floor?fid=" + fid;
    }


    /**
     * 删除周开放设置
     *
     * @param model
     * @param woid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteWeek")
    public String deleteWeekopen(Model model, Integer woid, Integer fid) throws Exception {
        weekOpenService.deletestatue(woid);
        return "redirect:/view/managing_Floor?fid=" + fid;
    }


    @RequestMapping("/managing_Users")
    public String managing_Users(Model model, Integer page, String year, String college, String major, HttpSession httpSession) throws Exception {
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);
        if (college != null && college.length() > 0) {
            if (college.contains("%")) {
                college = URLDecoder.decode(college, "utf-8");
            }
        }
        if (major != null && major.length() > 0) {
            if (major.contains("%")) {
                major = URLDecoder.decode(major, "utf-8");
            }
        }
        List<User> allUsers = userService.findAllUsers(year, college, major);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        if (major != null) {
            model.addAttribute("major", major);
            model.addAttribute("college", college);
        }
        if (year != null) {
            model.addAttribute("year", year);
        }

        List<User> allUsersPage = userService.findAllUsersPage(year, college, major, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }
    }

    @RequestMapping("/add_User")
    public String add_User(Model model) throws Exception {
        return "admin_page/Add_User";
    }

    /**
     * 批量提交用户
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/allUserUp")
    public String AllUserUp(Model model, MultipartFile excelfile, HttpSession httpSession, RedirectAttributes redirectAttributes) throws Exception {
        //获取文件名称
        String fileName = excelfile.getOriginalFilename();
        String leftPath = httpSession.getServletContext().getRealPath("/file");
        File file = new File(leftPath, fileName);
        excelfile.transferTo(file);
        String filePath = leftPath + "\\" + fileName;
        try {
            //进行文件解析
            FileInputStream inputStream = new FileInputStream(filePath);
            //正则表达式 判断 文件时 xls 2003   还是 xlsx  2007
            if (filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
                boolean is03eExcel = filePath.matches("^.+\\.(?i)((xls))$");
                //1.读取工作簿
                Workbook workbook = is03eExcel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
                //2.读取工作表
                Sheet sheet = workbook.getSheetAt(0);
                //3.读取所有行
                Integer add = 0;
                Integer mis = 0;

                for (Row row : sheet) {
                    try {
                        String name = row.getCell(0).getStringCellValue();
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        String sno = row.getCell(1).getStringCellValue();
                        String college = row.getCell(2).getStringCellValue();
                        String major = row.getCell(3).getStringCellValue();
                        String classes = row.getCell(4).getStringCellValue();
                        String sexstr = row.getCell(5).getStringCellValue();
                        Integer sex = 0;
                        if (sexstr.equals("女")) {
                            sex = 1;
                        }
                        User user = new User();
                        user.setName(name);
                        user.setSno(sno);
                        user.setPassword(MD5Util.encode(sno.substring(sno.length() - 6, sno.length())));
                        user.setCollege(college);
                        user.setMajor(major);
                        user.setClasses(classes);
                        user.setSex(sex);
                        userService.insertUser(user);
                        add++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        mis++;
                    }
                }
                redirectAttributes.addFlashAttribute("error_msg", "成功添加" + add + "条,添加失败" + mis + "条");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/view/managing_Users";
    }


    /**
     * 添加用户提交
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/aduserSub")
    public String adUserSub(Model model, String name, String sno, String college, String major, String classes, HttpSession httpSession) throws Exception {
        User user = new User();
        user.setClasses(classes);
        user.setMajor(major);
        user.setCollege(college);
        user.setPassword(MD5Util.encode(sno.substring(sno.length() - 6, sno.length())));
        user.setSno(sno);
        user.setName(name);
        user.setSex(0);
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "添加失败！");
        }


        String year = null;
        Integer page = null;
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);
        if (college != null && college.length() > 0) {
            if (college.contains("%")) {
                college = URLDecoder.decode(college, "utf-8");
            }
        }
        if (major != null && major.length() > 0) {
            if (major.contains("%")) {
                major = URLDecoder.decode(major, "utf-8");
            }
        }
        List<User> allUsers = userService.findAllUsers(year, college, major);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);
        if (major != null) {
            model.addAttribute("major", major);
            model.addAttribute("college", college);
        }
        if (year != null) {
            model.addAttribute("year", year);
        }

        List<User> allUsersPage = userService.findAllUsersPage(year, college, major, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }
    }

    @RequestMapping("/userDelete")
    public String userDelte(Model model, Integer uid, HttpSession httpSession) throws Exception {
        try {
            userService.deleteUserByID(uid);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error_msg", "删除失败！");
        }

        String year = null;
        Integer page = null;
        int pageSize = 10;
        List<String> colleges = userService.findAllCollege();
        model.addAttribute("colleges", colleges);

        List<User> allUsers = userService.findAllUsers(null, null, null);

        model.addAttribute("usersSize", allUsers.size());
        int pageTims;
        if (allUsers.size() % pageSize == 0) {
            pageTims = allUsers.size() / pageSize;
        } else {
            pageTims = allUsers.size() / pageSize + 1;
        }
        httpSession.setAttribute("pageTimes", pageTims);
        //页面初始的时候没有初试值
        if (null == page) {
            page = 1;
        }
        //每页开始的第几条记录
        int startRow;
        if (allUsers.size() < pageSize) {
            startRow = 0;
        } else {
            startRow = (page - 1) * pageSize;
        }
        model.addAttribute("currentPage", page);

        model.addAttribute("major", null);
        model.addAttribute("college", null);


        model.addAttribute("year", null);


        List<User> allUsersPage = userService.findAllUsersPage(year, null, null, startRow, pageSize);

        if (allUsersPage.size() > 0) {
            model.addAttribute("users", allUsersPage);
            return "admin_page/Managing_Users";
        } else {
            model.addAttribute("nullList", "暂无数据");
            return "admin_page/Managing_Users";
        }

    }

    /**
     * 根据学院查询专业
     *
     * @param college
     * @return
     * @throws Exception
     */
    @RequestMapping("/collegeMajor")
    public
    @ResponseBody
    List<String> getCollegeMajor(String college) throws Exception {
        List<String> majorByCollege = userService.findMajorByCollege(college);
        return majorByCollege;
    }

    //    座位使用情况统计
    @RequestMapping("/seat_DataStatistics")
    public String seat_DataStatistics(Model model) throws Exception {
        return "admin_page/Seat_DataStatistics";
    }

    /**
     * 进入到密码修改界面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/intoUpdatePassword")
    public String intoUpdatePassword(Model model) throws Exception {
        return "public_page/ResetPassword_ForAdmin";
    }


    //    学习统计
    @RequestMapping("/study_DataStatistics")
    public String study_DataStatistics(Model model, HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floor.getFid(), findweekofbook.get(i));  //查看某楼层在某州的预约情况
            WeekData weekData = new WeekData();
            weekData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofweek.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(j).getStime(), findbookfloorofweek.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofweek.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofweek.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int) dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }

        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i < findmonthofbook.size(); i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofmonth.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(j).getStime(), findbookfloorofmonth.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofmonth.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofmonth.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int) dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        model.addAttribute("weekdatas", weekDataList);
        model.addAttribute("monthdatas", monthDataList);

        return "admin_page/Study_DataStatistics";
    }

    /**
     * 获取每月的学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getmonthlearntime")
    public @ResponseBody
    List<MonthData> findmonthlearntim(HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findmonthofbook = bookingService.findmonthofbook();
        List<MonthData> monthDataList = new ArrayList<MonthData>();
        for (int i = 0; i < findmonthofbook.size(); i++) {
            List<Booking> findbookfloorofmonth = bookingService.findbookfloorofmonth(floor.getFid(), findmonthofbook.get(i));
            MonthData monthData = new MonthData();
            monthData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofmonth.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofmonth.get(j).getStime(), findbookfloorofmonth.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofmonth.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofmonth.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofmonth.size()) * 100;
            }
            monthData.setAllLearn(allLearn);
            monthData.setDealpro((int) dealpro);
            monthData.setLearntime(learntime / 60);
            monthData.setUndeal(nudeal);
            monthData.setMonth(findmonthofbook.get(i));
            monthDataList.add(monthData);
        }
        return monthDataList;
    }


    /**
     * 获取每周的学习情况
     *
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/getweeklearntime")
    public @ResponseBody
    List<WeekData> findweeklearntim(HttpSession httpSession) throws Exception {
        Floor floor = (Floor) httpSession.getAttribute("admin");
        List<Integer> findweekofbook = bookingService.findweekofbook();  //查看所有的预约周数
        List<WeekData> weekDataList = new ArrayList<WeekData>();
        for (int i = 0; i < findweekofbook.size(); i++) {
            List<Booking> findbookfloorofweek = bookingService.findbookfloorofweek(floor.getFid(), findweekofbook.get(i));  //查看某楼层在某州的预约情况
            WeekData weekData = new WeekData();
            weekData.setVenue(floor.getEmployer());
            Integer learntime = 0;
            Integer allLearn = 0;
            Integer nudeal = 0;
            float dealpro = 0;
            for (int j = 0; j < findbookfloorofweek.size(); j++) {
                Integer disTime = DateUtil.getDisTime(findbookfloorofweek.get(j).getStime(), findbookfloorofweek.get(j).getEtime());
                learntime = learntime + disTime;
                if (disTime > 0) {
                    allLearn++;
                }
                if (findbookfloorofweek.get(j).getDeal() == 1) {
                    nudeal++;
                }
            }
            if (findbookfloorofweek.size() > 0) {  //查看某周的预约是否未零
                dealpro = (nudeal / (float) findbookfloorofweek.size()) * 100;
            }
            weekData.setAllLearn(allLearn);
            weekData.setDealpro((int) dealpro);
            weekData.setLearntime(learntime / 60);
            weekData.setUndeal(nudeal);
            weekDataList.add(weekData);
        }
        return weekDataList;
    }

}
