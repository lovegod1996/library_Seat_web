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
    document.getElementById('checkphonenumber').style.backgroundColor = '#ffffff';
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
    document.getElementById('checkcode').style.backgroundColor = '#ffffff';
    return true;
}

//学号
function checkStudentID(){
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkstudentid').value;
    var seatNum = document.getElementById('seatNum').innerHTML;
    //如果seatNum  lable中包含-，说明不为空
    var sign = "-";
    if (seatNum.length != 0) {
        if (reg.test(con) != true) {
            document.getElementById('checkstudentid').style.backgroundColor = '#FF4040';
            return false;
        } else {
            if (seatNum.indexOf(sign) >= 0) {
                document.getElementById('checkstudentid').style.backgroundColor = '#ffffff';
                return true;
            }
            return false;
        }
    } else {
        document.getElementById('seatNum').innerHTML = "点击入座自动输入";
        document.getElementById('seatNum').style.color = '#FF4040';
        return false;
    }
}

//密码 6- -15位字母数字下划线 两次是否一样  用于设置密码，重置密码
function checkSetPassword_ByOldpassword() {
    var reg = /^(\w){6,15}$/;
    var oldpwd = document.getElementById("oldPwd").value;
    var pd1 = document.getElementById("newPwd").value;
    var pd2 = document.getElementById("newRePwd").value;
    if (oldpwd.length != 0) {
        if (reg.test(pd1) != true || pd1.length < 6) {
            document.getElementById("newPwd").style.backgroundColor = '#FF4040';
            return false;
        }
        if (reg.test(pd2) != true || pd1.length < 6) {
            document.getElementById("newRePwd").style.backgroundColor = '#FF4040';
            return false;
        }
        if (pd1 != pd2) {
            document.getElementById("newPwd").style.backgroundColor = '#FF4040';
            document.getElementById("newRePwd").style.backgroundColor = '#FF4040';
            //显示不一致信息
            document.getElementById('errmsg').style.display = 'block';
            return false;
        }
        return true;
    }
    return false
}

function checkSetPassword_ByPhonenumber() {
    var reg = /^(\w){6,15}$/;
    var phonenum = document.getElementById("checkphonenumber").value;
    var code = document.getElementById("checkcode").value;
    var pd1 = document.getElementById("newnewPwd").value;
    var pd2 = document.getElementById("newnewRePwd").value;
    if (phonenum.length != 0 && code.length != 0) {
        if (reg.test(pd1) != true || pd1.length < 6) {
            document.getElementById("newnewPwd").style.backgroundColor = '#FF4040';
            return false;
        }
        if (reg.test(pd2) != true || pd1.length < 6) {
            document.getElementById("newnewRePwd").style.backgroundColor = '#FF4040';
            return false;
        }
        if (pd1 != pd2) {
            document.getElementById("newnewPwd").style.backgroundColor = '#FF4040';
            document.getElementById("newnewRePwd").style.backgroundColor = '#FF4040';
            //显示不一致信息
            document.getElementById('errmsg1').style.display = 'block';
            return false;
        }
        return true;
    }
    return false;
}

//学院 中文
function checkCollege() {
    var reg = /[\u4e00-\u9fa5]/;
    var con = document.getElementById('checkcollege').valueOf();
    if (reg.test(con) != true) {
        document.getElementById('checkcollege').style.backgroundColor = '#FF4040';
        return false;
    }
    document.getElementById('checkcollege').style.backgroundColor = '#ffffff';
    return true;
}

//专业 中文
function checkMajor() {
    var reg = /[\u4e00-\u9fa5]/;
    var con = document.getElementById('checkmajor').valueOf();
    if (reg.test(con) != true) {
        document.getElementById('checkmajor').style.backgroundColor = '#FF4040';
        return false;
    }
    document.getElementById('checkmajor').style.backgroundColor = '#ffffff';
    return true;
}

//班级 数字
function checkClass() {
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkclass').value;
    if (reg.test(con) != true) {
        document.getElementById('checkclass').style.backgroundColor = '#FF4040';
        return false;
    }
    document.getElementById('checkclass').style.backgroundColor = '#ffffff';
    return true;
}

//学号 数字
function checkStudentId() {
    var reg = /^[0-9]*$/;
    var con = document.getElementById('checkstudentid').value;
    if (reg.test(con) != true) {
        document.getElementById('checkstudentid').style.backgroundColor = '#FF4040';
        return false;
    }
    document.getElementById('checkstudentid').style.backgroundColor = '#ffffff';
    return true;
}

//姓名 中文+字母
function checkName() {
    var reg = /^[\u4E00-\u9FA5A-Za-z]+$/;
    var con = document.getElementById('checkname').value;
    if (reg.test(con) != true) {
        document.getElementById('checkname').style.backgroundColor = '#FF4040';
        return false;
    }
    document.getElementById('checkname').style.backgroundColor = '#ffffff';
    return true;
}

//重置手机号
function checkForm_SetNewPhonenumber() {
    if (checkPhoneNumber() == true && checkCode() == true) {
        return true;
    }
    return false;
}

//绑定手机号
function checkForm_BindingPhonenumber() {
    if (checkPhoneNumber() == true && checkCode() == true) {
        return true;
    }
    return false;
}

//添加用户
function checkForm_Add_User() {
    if (checkName() == true && checkStudentId() == true && checkCollege() == true && checkMajor() == true && checkClass() == true) {
        return true;
    }
    return false;
}





