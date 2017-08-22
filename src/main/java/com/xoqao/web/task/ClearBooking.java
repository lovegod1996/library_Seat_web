package com.xoqao.web.task;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.deal.UnDeal;
import com.xoqao.web.bean.seat.Seat;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.commen.CommenValue;
import com.xoqao.web.service.BookingService;
import com.xoqao.web.service.SeatService;
import com.xoqao.web.service.UndealService;
import com.xoqao.web.service.WeekOpenService;
import com.xoqao.web.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 说明：执行定时任务清理恶意占座情况
 * <p>
 * Author: lovegod
 * Date:  2017/8/3.
 * Email:dx96_j@163.com
 */
@Component
public class ClearBooking {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private WeekOpenService weekOpenService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private UndealService undealService;

    @Scheduled(cron = "0 0 22 * * ?")   //晚上十点执行一次
    public void ClearBook() throws Exception {
        List<Booking> bookings = bookingService.findbookNoOverToday();
        for (int i = 0; i < bookings.size(); i++) {
            try {
                List<UnDeal> unDealCord = undealService.findUnDealCord(bookings.get(i).getSno());
                switch (bookings.get(i).getStatue()) {
                    case 0:
                        bookingService.updateStime(new Date(), bookings.get(i).getBid());
                        bookingService.updateEtime(new Date(), 3, 0, 1, bookings.get(i).getBid());
                        /**
                         * 失信处理
                         */
                        if (unDealCord.size() > 0) {
                            List<Booking> userBookDeal = bookingService.findUserBookDeal(bookings.get(i).getSno(), 1, unDealCord.get(unDealCord.size() - 1).getRecord());
                            if (userBookDeal.size() >= CommenValue.MAX_DEAL) {  //失信超过一定次数
                                undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                            }
                        } else {
                            List<Booking> finduserbookpromise = bookingService.finduserbookpromise(bookings.get(i).getSno(), 1);
                            if (finduserbookpromise.size() >= CommenValue.MAX_DEAL) {   //失信超过一定次数
                                undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                            }
                        }
                        break;
                    case 1:
                        bookingService.updateEtime(bookings.get(i).getBetime(), 3, 0, 0, bookings.get(i).getBid());
                        break;
                    case 2:
                        Seat seat = seatService.findByid(bookings.get(i).getSid());
                        WeekOpen weekOpen = weekOpenService.findopenFloorday(seat.getFid(), 1);
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
                        if (weekOpen.getParam2() != null) {
                            String[] split = weekOpen.getParam1().split("-");
                            Date closeTime = df.parse(split[1]);
                            String format1 = df.format(bookings.get(i).getEtime());
                            Date etime = df.parse(format1);
                            Integer disTime = DateUtil.getDisTime(etime, closeTime);
                            if (disTime < bookings.get(i).getDelay()) {
                                bookingService.updateEtime(bookings.get(i).getEtime(), 3, 0, 0, bookings.get(i).getBid());
                            } else {
                                bookingService.updateEtime(bookings.get(i).getEtime(), 3, 0, 1, bookings.get(i).getBid());

                                /**
                                 * 失信处理
                                 */

                                if (unDealCord.size() > 0) {
                                    List<Booking> userBookDeal = bookingService.findUserBookDeal(bookings.get(i).getSno(), 1, unDealCord.get(unDealCord.size() - 1).getRecord());
                                    if (userBookDeal.size() >= CommenValue.MAX_DEAL) {  //失信超过一定次数
                                        undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                                    }
                                } else {
                                    List<Booking> finduserbookpromise = bookingService.finduserbookpromise(bookings.get(i).getSno(), 1);
                                    if (finduserbookpromise.size() >= CommenValue.MAX_DEAL) {   //失信超过一定次数
                                        undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                                    }
                                }
                            }
                        } else {
                            String[] split = weekOpen.getParam2().split("-");
                            Date closeTime = df.parse(split[1]);
                            String format1 = df.format(bookings.get(i).getEtime());
                            Date etime = df.parse(format1);
                            Integer disTime = DateUtil.getDisTime(etime, closeTime);
                            if (disTime < bookings.get(i).getDelay()) {
                                bookingService.updateEtime(bookings.get(i).getEtime(), 3, 0, 0, bookings.get(i).getBid());
                            } else {
                                bookingService.updateEtime(bookings.get(i).getEtime(), 3, 0, 1, bookings.get(i).getBid());
                                /**
                                 * 失信处理
                                 */
                                if (unDealCord.size() > 0) {
                                    List<Booking> userBookDeal = bookingService.findUserBookDeal(bookings.get(i).getSno(), 1, unDealCord.get(unDealCord.size() - 1).getRecord());
                                    if (userBookDeal.size() >= CommenValue.MAX_DEAL) {  //失信超过一定次数
                                        undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                                    }
                                } else {
                                    List<Booking> finduserbookpromise = bookingService.finduserbookpromise(bookings.get(i).getSno(), 1);
                                    if (finduserbookpromise.size() >= CommenValue.MAX_DEAL) {   //失信超过一定次数
                                        undealService.insertUndeal(bookings.get(i).getSno(), new Date());
                                    }
                                }
                            }
                        }

                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date() + "清理今天预约已完成！");
    }
}
