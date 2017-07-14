/**
 * Created by 1Q84 on 2017/7/14.
 */

/**
 * reg  正规表达式对象
 * con  获取输入框的内容
 * */

//手机号
function checkPhoneNumber() {
    var reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|6|7|8]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    var con = document.getElementById('checkphonenumber').value;
    if (reg.test(con) != true || con.length == 0) {
        document.getElementById('checkphonenumber').style.backgroundColor = '#FF4040';
        return false;
    }
    return true;
}

//学院 中文
function checkCollege() {
    var reg = /[\u4e00-\u9fa5]/;
    var con = document.getElementById('checkcollege').valueOf();
    if (reg.test(con) != true) {
        return false;
    }
    return true;
}

//专业 中文
function checkMajor() {
    var reg = /[\u4e00-\u9fa5]/;
    var con = document.getElementById('checkmajor').valueOf();
    if (reg.test(con) != true) {
        return false;
    }
    return true;
}

//班级 数字
function checkClass() {
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkclass').value;
    if (reg.test(con) != true) {
        return false;
    }
    return true;
}

//学号 数字
function checkStudentId() {
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkstudentid').value;
    if (reg.test(con) != true) {
        return false;
    }
    return true;
}

//姓名 中文+字母
function checkName() {
    var reg = /^[\u4E00-\u9FA5A-Za-z]+$/;
    var con = document.getElementById('checkname').value;
    if (reg.test(con) != true) {
        return false;
    }
    return true;
}

//验证码 数字
function checkCode() {
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkcode').value;
    if (reg.test(con) != true || con.length == 0) {
        document.getElementById('checkcode').style.backgroundColor = '#FF4040';
        return false;
    }
    return true;
}

function checkForm() {
    if (
        checkPhoneNumber() == true &&
        // checkCollege() == true &&
        // checkMajor() == true &&
        // checkClass() == true &&
        // checkStudentId() == true &&
        // checkName() == true &&
        checkCode() == true
    ) {
        return true;
    } else {
        return false;
    }
}




