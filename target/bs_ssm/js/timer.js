/*
    create by zhaolei 2011-6-20 version 1.0
    company www.ingit.com
*/
var HH = 23;
var MM = 59;
var SS = 59;
var mode = {};
var LOOP;

function loopInit(id, sign) {
    LOOP = true;
    change(id, sign);
}

function loopBreak() {
    LOOP = false;
}

function getTag(id, tagName, str, attr) {
    var tagArray = document.getElementById(id).getElementsByTagName(tagName);
    for (i = 0; i < tagArray.length; i++) {
        if (eval("tagArray[i]." + attr) == str) {
            return tagArray[i];
        }
    }
}

function setMode(id, hms) {
    var selectInputPre = getTag(id, 'input', mode[id + "mode"], 'name');
    var selectInput = getTag(id, 'input', hms, 'name');
    selectInputPre.style.backgroundColor = "#FFFFFF";
    mode[id + "mode"] = hms;
    selectInput.style.backgroundColor = "#7FFFD4";
}

function change(id, sign) {
    if (LOOP) {
        var num = getTag(id, 'input', mode[id + "mode"], 'name').value - 0;
        var changeNum = eval(num + sign + 1) + "";
        if (changeNum >= 0 & changeNum <= eval(mode[id + "mode"])) {
            changeNum = complete(changeNum);
            getTag(id, 'input', mode[id + "mode"], 'name').value = changeNum;
        }
        setTimeout("change('" + id + "','" + sign + "')", 200);
    }
}

function complete(num) {
    while (!(num.length == 2)) {
        num = "0" + num;
    }
    return num;
}

function returnTimer(id) {
    var strHH = getTag(id, 'input', 'HH', 'name').value;
    var strMM = getTag(id, 'input', 'MM', 'name').value;
    var strSS = getTag(id, 'input', 'SS', 'name').value;
    return strHH + ':' + strMM + ':' + strSS;
}

function checkNum(id, sign, value) {
    if (value < 10) {
        value = complete(value);
        getTag(id, 'input', sign, 'name').value = value;
    } else {
        if (value > eval(sign)) {
            getTag(id, 'input', sign, 'name').value = eval(sign);
        }
    }
}

function keyDown(id, key, onFocusObject) {
    var nextObject;
    if (onFocusObject.name == 'HH') {
        nextObject = 'MM';
    } else if (onFocusObject.name == 'MM') {
        nextObject = 'SS';
    } else if (onFocusObject.name == 'SS') {
        nextObject = 'HH';
    }
    if (!((key >= 48 && key <= 57) || (key >= 96 && key <= 105) || (key == 8) || (key == 46) || (key >= 37 && key <= 40))) {
        event.returnValue = false;
    }
    if (key == 37 || key == 39) {
        getTag(id, 'input', nextObject, 'name').focus();
    }
    if (key == 38) {
        loopInit(id, '+');
    }
    if (key == 40) {
        loopInit(id, '-');
    }
}

function keyUp(key) {
    if (key == 38 || 40) {
        loopBreak();
    }
}

// function getCurDate()
// {
//     var d = new Date();
//     var years = d.getFullYear();
//     var month = add_zero(d.getMonth()+1);
//     var days = add_zero(d.getDate());
//     var hours = add_zero(d.getHours());
//     var minutes = add_zero(d.getMinutes());
//     var seconds=add_zero(d.getSeconds());
//     document.getElementById("HH").value=hours;
//     document.getElementById("MM").value=minutes;
//     document.getElementById("SS").value=seconds;
// }
// function add_zero(temp)
// {
//     if(temp<10) return "0"+temp;
//     else return temp;
// }
// setInterval("getCurDate()",100);


function showTimer(tempId) {

    mode[tempId + "mode"] = "HH";
    var timerConent ='<table cellpadding="0" cellspacing="0" style="width:100%;height:100%;border:0px;table-layout : fixed">' +
        '<tr style="width:100%;height:100%">' +
        '<td width="90%" height="100%" style="border:0px;display:block;">' +
        '<input type="text"  id="HH" value="09" maxlength="2" style="border: 0px; width: 30px;height:100%;text-align: left" name="HH" onchange="checkNum(' + "'" + tempId + "'," + "'HH'," + 'this.value)" onkeydown="keyDown(' + "'" + tempId + "'" + ',event.keyCode,this)" onkeyup="keyUp(event.keyCode)" onfocus="setMode(' + "'" + tempId + "','HH'" + ')"/>' +
        '<input type="text"  style="border:0;background:transparent;width:6;" readOnly=true  value=":"/>' +
        '<input type="text"  id="MM" value="00" maxlength="2" style="border: 0px; width: 30px;height:100%;text-align: left" name="MM" onchange="checkNum(' + "'" + tempId + "'," + "'MM'," + 'this.value)" onkeydown="keyDown(' + "'" + tempId + "'" + ',event.keyCode,this)" onkeyup="keyUp(event.keyCode)" onfocus="setMode(' + "'" + tempId + "','MM'" + ')"/>' +
        '<input type="text"  style="border:0;background:transparent;width:6"  readOnly=true value=":"/>' +
        '<input type="text"  id="SS" value="00" maxlength="2" style="border: 0px; width: 30px;height:100%;text-align: left" name="SS" onchange="checkNum(' + "'" + tempId + "'," + "'SS'," + 'this.value)" onkeydown="keyDown(' + "'" + tempId + "'" + ',event.keyCode,this)" onkeyup="keyUp(event.keyCode)" onfocus="setMode(' + "'" + tempId + "','SS'" + ')"/></td>' +
        '<td width="10%" height="100%" style="border:0px;">' +
        '<button type="button" class="layui-btn layui-btn-mini layui-btn-normal" hidefocus="true" style="display:block;width:20px;height:15px;" onmouseup="loopBreak()" onmousedown="loopInit(' + "'" + tempId + "','+'" + ')">+</button>' +
        '<button type="button" class="layui-btn layui-btn-mini layui-btn-normal" hidefocus="true" style="margin-left:0;margin-top:2px;width:20px;height:15px;" onmouseup="loopBreak()" onmousedown="loopInit(' + "'" + tempId + "','-'" + ')">-</button></td>' +
        '</tr>' +
        '</table>';
    document.getElementById(tempId).innerHTML = timerConent;
}

