

/**
* 检查输入的字符是否具有特殊字符
* true 正确
*/
function checkQuote(str) {
    var items = new Array("~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "{", "}", "[", "]", "(", ")");
    items.push(":", ";", "'", "|", "\\", "<", ">", "?", "/", "<<", ">>", "||", "//");
    items.push("admin", "administrators", "administrator", "管理员", "系统管理员");
    items.push("select", "delete", "update", "insert", "create", "drop", "alter", "trancate");
    str = str.toLowerCase();
    for (var i = 0; i < items.length; i++) {
        if (str.indexOf(items[i]) >= 0) {
            return true;
        }
    }
    return false;
}
//检查输入的是否为空
function isEmpty(str){
	if(str==null){
		return true;
	}else if(str == undefined){
		return true;
	}else if(str.length <= 0){
		return true;
	}else if($.trim(str).length <= 0){
		return true;
	}
	return false;
}

/**
 * 检查输入的电话号码格式是否正确
 * true 通过
 */
function checkPhone(strPhone) {
	var phoneRegWithArea=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
	var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/;
    if (strPhone.length > 9) {
        if (phoneRegWithArea.test(strPhone)) {
            return true;
        } else {
            return false;
        }
    } else {
        if (phoneRegNoArea.test(strPhone)) {
            return true;
        } else {
            return false;
        }
    }
}

/**
 * 校验ip地址的格式
 * true 正确
 */
function isIP(strIP) {
    if (isNull(strIP)) return false;
    var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; //匹配IP地址的正则表达式
    if (re.test(strIP)) {
        if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256) return true;
    }
    return false;
}

//检查输入的url是否正确
function checkURL(str){
    if (str.match(/(http[s]?|ftp):\/\/[^\/\.]+?\..+\w[\/]?$/i) == null) {
        return false;
    } else {
        return true;
    }
}

//检验输入的是否是数字  非0开头
function checkNum(s){
	var regu = "^[1-9][0-9]*$";
	var re = new RegExp(regu);
	if(re.test(s)){
		return true;
	} else {
		return false;
	}
}
//全数字
function checkNumber(str){
	var regu = "^[0-9]*$";
	var re = new RegExp(regu);
	if(re.test(str)){
		return true;
	} else {
		return false;
	}
	
}
// 校验价格
function checkPrice(str){
	var regu = "^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$";
	var re = new RegExp(regu);
	
	if(re.test(str)){
		return true;
	}else{
		return false;
	}
}

/**
 * 消息提示Dialog
 */
function showMessageDialog(msg,callback){
	var div = $("<div></div>");
	$(div).html(msg);
	$(div).css({
		"text-align":"center",
		"line-height":"35px",
		"padding":"0"
	});
	
	$(div).dialog({
		title: "提示消息",
		autoOpen : true,
		width : 380,
		height: 200,
		modal : true,
		closeOnEscape:false,
		show:"fadeIn",
		hide:"fadeOut",
		buttons:{
			"确定": function(){
				$(this).dialog("close");
				if(callback){
					callback();
				}
			}
		},
		close:function(){
			$(this).dialog("destory");
		},
		open :function(){
			useDialogModifyCss();
			$(".ui-dialog-titlebar-close").hide();
		}
		
		
	});
	
}

/**
 * 确定取消提示Dialog
 */
function showConfirmDialog(msg, callback) {
    var div = $("<div></div>");
    $(div).html(msg);
    $(div).css({
        "text-align": "center",
        "line-height": "35px"
    });

    $(div).dialog({
        title: "确认消息提示",
        autoOpen: true,
        width: 380,
        height: 200,
        modal: true,
        closeOnEscape:false,
        show:"fadeIn", //{effect:"blind",duration:400}
        hide:"fadeOut",
        buttons: {
        	"取消": function () {
                callback(false);
                $(this).dialog("close");
            },
            "确定": function () {
                    callback(true);
                    $(this).dialog("close");
            }
        },
        close: function () {
            callback(false);
            $(this).dialog("destroy").remove();
        },
        open: function () {
            useDialogModifyCss();
            $(".ui-dialog-titlebar-close").hide();
        }
    });
}

/**
 * 使用dialog样式
 */
function useDialogModifyCss() {
    $(".ui-dialog").css({
        "padding": 0,
        "background": "#FFF"
    });

    $(".ui-dialog-titlebar").css({
        "padding": ".8em 1em",
        "background": "#e6e9ed",
        "color": "#000",
        "border": "none",
        "border-bottom-left-radius": 0,
        "border-bottom-right-radius": 0
    });

    $(".ui-dialog .ui-dialog-buttonpane").css({
        "background": "#FFF",
        "text-align": "center"
    });

    $(".ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset").css({
        "float": "none",
        "width": "100%"
    });

    $(".ui-dialog .ui-dialog-buttonpane button").css({
        "padding": "0 18px",
        "background": "#0091E6",
        "border": "none", 
        "color": "#FFF"
    });

    $(".ui-dialog .ui-dialog-buttonpane").css({
        "padding": ".5em 1em"
    });

    $(".ui-dialog .ui-dialog-buttonpane button:hover").css({
        "background": "#4cb2ed",
        "border": "none",
        "color": "#FFF"
    });
  
}

/*
 * 获取所有name为spCodeId的checkbox
 * */
function getCheckValue(spCodeId){
	var spCodesTemp = "";
	 $('input:checkbox[name=spCodeId]:checked').each(function(i){
	  if(0==i){
	   spCodesTemp = $(this).val();
	  }else{
	   spCodesTemp += (","+$(this).val());
	  }
	 });
	 $("#txt_spCodes").val(spCodesTemp);
}

/**
 * 添加问卷后选择跳转
 * @param msg
 * @param callback
 */
function showConfirmDialog_saveQueJump(msg, callback) {
    var div = $("<div></div>");
    $(div).html(msg);
    $(div).css({
        "text-align": "center",
        "line-height": "35px"
    });

    $(div).dialog({
        title: "确认消息提示",
        autoOpen: true,
        width: 380,
        height: 200,
        modal: true,
        closeOnEscape:false,
        show:"fadeIn", //{effect:"blind",duration:400}
        hide:"fadeOut",
        buttons: {
        	"继续添加": function () {
        		callback(false);
                $(this).dialog("close");
            },
            "返回列表": function () {
                callback(true);
                $(this).dialog("close");
            }
        },
        close: function () {
            callback(false);
            $(this).dialog("destroy").remove();
        },
        open: function () {
            useDialogModifyCss();
            $(".ui-dialog-titlebar-close").hide();
        }
    });
}
/**
 * 页面管理定时器添加后跳转选择
 * @param msg
 * @param callback
 */
function showConfirmDialog_timeSuperList(msg, callback) {
    var div = $("<div></div>");
    $(div).html(msg);
    $(div).css({
        "text-align": "center",
        "line-height": "35px"
    });

    $(div).dialog({
        title: "确认消息提示",
        autoOpen: true,
        width: 380,
        height: 200,
        modal: true,
        closeOnEscape:false,
        show:"fadeIn", //{effect:"blind",duration:400}
        hide:"fadeOut",
        buttons: {
        	"重新设置": function () {
        		callback(false);
                $(this).dialog("close");
            },
            "返回列表": function () {
                callback(true);
                $(this).dialog("close");
            }
        },
        close: function () {
            callback(false);
            $(this).dialog("destroy").remove();
        },
        open: function () {
            useDialogModifyCss();
            $(".ui-dialog-titlebar-close").hide();
        }
    });
}











