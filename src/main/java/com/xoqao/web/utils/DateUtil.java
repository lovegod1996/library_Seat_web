package com.xoqao.web.utils;

import com.xoqao.web.bean.booking.Booking;
import com.xoqao.web.bean.weekopen.WeekOpen;
import com.xoqao.web.commen.CommenValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/7/14.
 * Email:dx96_j@163.com
 */
public class DateUtil {

    public static Date getDate(String time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        Date date = sdf.parse(time);
        return date;
    }

    public static Integer getDisTime(String inTime, String ouTime) {

        Integer timedis = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date ot = df.parse(ouTime);
            Date it = df.parse(inTime);
            long l = ot.getTime() - it.getTime();

            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

            System.out.println("" + day + "天" + hour + "小时" + min + "分");
            timedis = (int) (day * 24 * 60 + hour * 60 + min);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timedis;
    }

    public static Integer getDisTime(Date inTime, Date ouTime) {
        Integer timedis = 0;
        long l = ouTime.getTime() - inTime.getTime();

        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

        System.out.println("" + day + "天" + hour + "小时" + min + "分");
        timedis = (int) (day * 24 * 60 + hour * 60 + min);
        return timedis;
    }


    /**
     * 　　* 判断时间是否在时间段内 *
     * <p>
     * 　　* @param date
     * <p>
     * 　　* 当前时间 yyyy-MM-dd HH:mm:ss
     * <p>
     * 　　* @param strDateBegin
     * <p>
     * 　　* 开始时间 00:00:00
     * <p>
     * 　　* @param strDateEnd
     * <p>
     * 　　* 结束时间 00:05:00
     * <p>
     * 　　* @return
     * <p>
     */

    public static boolean isInDate(Date date, String strDateBegin, String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
// 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
// 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
// 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
// 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                    && strDateM <= strDateEndM) {
                return true;
// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算座位当前时间状态
     *
     * @param bookings
     * @return
     */
    public static Integer findSeatStatue(List<Booking> bookings) {
        Integer statue = 0;
        for (int i = 0; i < bookings.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          boolean inDate = isInDate(new Date(), sdf.format(bookings.get(i).getBstime()), sdf.format(bookings.get(i).getBetime()));
            boolean b = belongCalendar(new Date(), bookings.get(i).getBstime(), bookings.get(i).getBetime());
            if (b) {
                if (bookings.get(i).getStime() == null && bookings.get(i).getStatue() == 0) {  //如果还没有入座
                    statue = 1;
                } else {
                    if (bookings.get(i).getEtime() == null) {   //如果还没有离开
                        statue = 2;
                    } else {
                        if (bookings.get(i).getStatue() == 2) {
                            statue = 3;
                        } else if (bookings.get(i).getStatue() == 1) {
                            statue = 2;
                        } else {
                            statue = 0;
                        }
                    }
                }
            } else {
                statue = 0;
            }

        }
        return statue;
    }

    /**
     * 查找当前时间内的预约
     *
     * @param bookings
     * @return
     */
    public static Booking findbooknow(List<Booking> bookings) {
        for (int i = 0; i < bookings.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            boolean b = belongCalendar(new Date(), bookings.get(i).getBstime(), bookings.get(i).getBetime());
            if (b) {
                return bookings.get(i);
            }
        }
        return null;
    }

    /**
     * 判断用户预约时间是否在开放时间段内
     *
     * @return
     */
    public static boolean getfollowTime(WeekOpen weekOpen, Date stime, Date etime) {
        boolean b = false;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date opentime = null;
        Date closeTime = null;
        try {
            String[] split = weekOpen.getParam1().split("-");
            opentime = df.parse(split[0]);
            closeTime = df.parse(split[1]);
            String format = df.format(stime);
            String format1 = df.format(etime);
            stime = df.parse(format);
            etime = df.parse(format1);
            boolean b1 = belongCalendar(stime, opentime, closeTime);
            if (stime.equals(opentime)) {
                b1 = true;
            }
            if (b1) {
                boolean b2 = belongCalendar(etime, opentime, closeTime);
                if (b2) {
                    b = true;
                }
                if (etime.equals(closeTime)) {
                    b = true;
                }
            } else {
                if (weekOpen.getParam2() != null) {
                    String[] split2 = weekOpen.getParam2().split("-");
                    opentime = df.parse(split2[0]);
                    closeTime = df.parse(split2[1]);
                    boolean b3 = belongCalendar(stime, opentime, closeTime);
                    if (stime.equals(opentime)) {
                        b3 = true;
                    }
                    if (b3) {
                        boolean b4 = belongCalendar(etime, opentime, closeTime);
                        if (b4) {
                            b = true;
                        }
                        if (etime.equals(closeTime)) {
                            b = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 检查与某一预约是否右冲突
     *
     * @param booking
     * @param stime
     * @param etime
     * @return
     */
    public static boolean checkbookclash(Booking booking, Date stime, Date etime) {
        boolean bb = false;
        if (booking.getStatue() != 3) {
            boolean b = belongCalendar(stime, booking.getBstime(), booking.getBetime());
            if (b) {
                bb = true;
            } else {
                boolean b1 = belongCalendar(etime, booking.getBstime(), booking.getBetime());
                if (b1) {
                    bb = true;
                }
                if (etime.equals(booking.getEtime())) {
                    bb = true;
                }
            }
            if (stime.before(booking.getBstime()) && etime.after(booking.getBetime())) {
                bb = true;
            }
        }
        return bb;
    }

    /**
     * 查找选择时间段的区分
     *
     * @return
     */
    public static Integer getWeekOpenDiten(WeekOpen weekOpen, Date time) {
        int statue = 0;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date opentime = null;
        Date closeTime = null;
        try {
            String[] split = weekOpen.getParam1().split("-");
            opentime = df.parse(split[0]);
            closeTime = df.parse(split[1]);
            String format = df.format(time);
            time = df.parse(format);
            boolean b1 = belongCalendar(time, opentime, closeTime);
            if (time.equals(opentime) || time.equals(closeTime)) {
                statue = 1;
            }
            if (b1) {
                statue = 1;  //时间在第一时间端内
            } else {
                if (time.before(opentime)) {
                    statue = 0;  //时间在第一时间段之前
                } else if (time.after(closeTime)) {
                    if (weekOpen.getParam2() != null) {
                        String[] split2 = weekOpen.getParam2().split("-");
                        opentime = df.parse(split2[0]);
                        closeTime = df.parse(split2[1]);
                        boolean b3 = belongCalendar(time, opentime, closeTime);
                        if (b3) {
                            statue = 3; //时间在第二是时间段内
                        } else {
                            if (time.equals(opentime) || time.equals(closeTime)) {
                                statue = 3;
                            }
                            if (time.before(opentime)) {
                                statue = 2;//时间在第一时间段后，第二时间段前
                            }
                            if (time.after(closeTime)) {
                                statue = 4;//时间在第二时间段后
                            }
                        }

                    } else {
                        statue = 2;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statue;
    }

    /**
     * 获取时间段的开始时间或结束时间
     *
     * @param weekOpen 开放时间
     * @param para     第一时间段或第二
     * @param type     开始还是结束
     * @return
     */
    public static Date getWeekOpentime(WeekOpen weekOpen, Integer para, Integer type) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date date = null;
        Date date1 = new Date();
        if (para == 1) {
            String[] split = weekOpen.getParam1().split("-");
            if (type == 1) {
                date = df.parse(split[0]);
            } else {
                date = df.parse(split[1]);
            }
        } else {
            String[] split = weekOpen.getParam2().split("-");
            if (type == 1) {
                date = df.parse(split[0]);
            } else {
                date = df.parse(split[1]);
            }
        }
        date.setDate(date1.getDate());
        date.setMonth(date1.getMonth());
        date.setYear(date1.getYear());
        return date;
    }

    /**
     * 检查预约时间是否有冲突
     *
     * @param bookings
     * @param stime
     * @param etime
     * @return
     */
    public static boolean checkbooksclash(List<Booking> bookings, Date stime, Date etime) {
        boolean b = false;
        for (int i = 0; i < bookings.size(); i++) {
            boolean checkbookclash = checkbookclash(bookings.get(i), stime, etime);
            if (checkbookclash) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 获取两个小时后的时间
     *
     * @return
     */
    public static Date getTwoOursAfter(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(java.util.Calendar.HOUR, 2);
        String format = sdf.format(calendar.getTime());
        Date parse = sdf.parse(format);
        return parse;
    }

    /**
     * 获取一定时间后的日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDaysAfter(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, CommenValue.MAX_NOBOOK_DAY);
        String format = sdf.format(calendar.getTime());
        Date parse = sdf.parse(format);
        return parse;
    }


    /**
     * 将时间转化为日期
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static Date getTimeDate(String time, Integer day) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date parse = df.parse(time);
        Date now = new Date();
        if (day == 1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);
            String format = sdf.format(calendar.getTime());
            now = sdf.parse(format);
        }
        parse.setYear(now.getYear());
        parse.setMonth(now.getMonth());
        parse.setDate(now.getDate());
        return parse;
    }
}
