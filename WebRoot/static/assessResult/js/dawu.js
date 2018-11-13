	//mui初始化
	mui.init();
//声明保存模块node变量
var bgsmNode;
var zyNode;
var ndbxNode;
var yyNode;
var wdNode;
var twowdNode;
var threewdNode;
var fourwdNode;
var fivewdNode;
/**
 * 函数配置
 */
	//初始化追加内容函数的参数
	//报告
	var bgIniConfig={
		selecter:"baogao-ul",
		buttonStr:"baogao-increaseF",
		ElType:"li",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType);
		}
	}
	//摘要
	var zyIniConfig={
		selecter:"assessresult_abstract-content_box",
		buttonStr:"zhaiyao-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	} 
	
	//维度一头部
	var oneHeadIniConfig={
		selecter:"dimensionone-head-content_box",
		buttonStr:"one-head-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//维度二头部
	var twoHeadIniConfig={
		selecter:"dimensiontwo-head-content_box",
		buttonStr:"two-head-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//维度三头部
	var threeHeadIniConfig={
		selecter:"dimensionthree-head-content_box",
		buttonStr:"three-head-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//维度四头部
	var fourHeadIniConfig={
		selecter:"dimensionfour-head-content_box",
		buttonStr:"four-head-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//维度五头部
	var fiveHeadIniConfig={
		selecter:"dimensionfive-head-content_box",
		buttonStr:"five-head-increaseF",
		ElType:"p",
		ElClassName:"assessresult-abstract-content-text",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//原因分析
	var yyIniConfig={
		selecter:"reasonanalysis-contentbox",
		buttonStr:"yuanyin-increaseF",
		ElType:"p",
		ElClassName:"reasonanalysis-content",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	} 
	//维度二原因分析
	var wdTwoyyIniConfig={
		selecter:"dimensiontwotwo-reasonanalysis-contentbox",
		buttonStr:"two-yuanyin-increaseF",
		ElType:"p",
		ElClassName:"reasonanalysis-content",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	} 
	//维度三原因分析
	var wdThreeyyIniConfig={
		selecter:"dimensionthree-reasonanalysis-contentbox",
		buttonStr:"three-yuanyin-increaseF",
		ElType:"p",
		ElClassName:"reasonanalysis-content",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	} 
	//维度四原因分析
	var wdFouryyIniConfig={
		selecter:"dimensionfour-reasonanalysis-contentbox",
		buttonStr:"four-yuanyin-increaseF",
		ElType:"p",
		ElClassName:"reasonanalysis-content",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//维度五原因分析
	var wdFiveyyIniConfig={
		selecter:"dimensionfive-reasonanalysis-contentbox",
		buttonStr:"five-yuanyin-increaseF",
		ElType:"p",
		ElClassName:"reasonanalysis-content",
		add:function(){
			increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
		}
	}
	//操作区域
	var czIniConfig={
			selecter:"operation-contentbox",
			buttonStr:"cz-increaseF",
			ElType:"p",
			ElClassName:"operation-content-text",
			add:function(){
				increaseFun(this.selecter,this.buttonStr,this.ElType,this.ElClassName);
			}
		}

	
	
/**
 *函数启用 
 */
	//关闭按钮函数
	closeTemplate();
	//启用增加一行按钮事件追加报告内容
	bgIniConfig.add();
	//启用增加一行按钮事件追加摘要内容
	zyIniConfig.add();
	//启用增加一行按钮事件追加维度一你的表现内容
	addBiaoxian();
	//启用增加一行按钮事件追加维度二你的表现内容
	addTwoBiaoxian();
	//启用增加一行按钮事件追加维度三你的表现内容
	addThreeBiaoxian();
	//启用增加一行按钮事件追加维度四你的表现内容
	addFourBiaoxian();
	//启用增加一行按钮事件追加维度五你的表现内容
	addFiveBiaoxian();
	//启用增加一行按钮事件追加原因分析内容
	yyIniConfig.add();
	//启用增加一行按钮事件追加维度三原因分析内容
	wdThreeyyIniConfig.add();
	//启用增加一行按钮事件追加维度四原因分析内容
	wdFouryyIniConfig.add();
	//启用增加一行按钮事件追加维度五原因分析内容
	wdFiveyyIniConfig.add();
	//启用增加一行按钮事件追加操作区域内容
	czIniConfig.add();
	//启用增加一行按钮事件追加操作维度一头部内容
	oneHeadIniConfig.add();
	//启用增加一行按钮事件追加操作维度二头部内容
	twoHeadIniConfig.add();
	//启用增加一行按钮事件追加操作维度三头部内容
	threeHeadIniConfig.add();
	//启用增加一行按钮事件追加操作维度四头部内容
	fourHeadIniConfig.add();
	//启用增加一行按钮事件追加操作维度五头部内容
	fiveHeadIniConfig.add();
	//启用增加一行按钮事件追加维度二原因分析
	wdTwoyyIniConfig.add();
	//监听点击录入完毕按钮
	copyTemplate();
	//删除为空的节点
	claerContent();
	//点击添加事件
	mButtinClick();
/**
 * “展开”、“收起”报告
 */
function changeStyle() {
   
   var reportState=document.getElementById('behavior-n-box').getAttribute('state');
   //判断是展开还是收起状态
   if(reportState=='true'){
   		//进入这里表示当前报告是收起状态
   		//1.将标签的state属性设置为false表示已经是展开状态的
   		document.getElementById('behavior-n-box').setAttribute('state','false')
   		//2.需要将文本和图片换成收起状态的
   		document.getElementById('behavior-img').setAttribute('src','images/tap-up.png')
   		document.getElementById('behavior-text').innerHTML='收起详细报告'
   		//3.将隐藏的各个报告区域显示出来
   		//先判断要显示的标签id是否存在，如果存在再显示，否则会报错
   		//显示"你的表现区域"
   		if(document.getElementById('assessresult-yourperformance')!=null){
   			document.getElementById('assessresult-yourperformance').style.display='block';
   		}
   		//显示"原因分析区域"
   		if(document.getElementById('reasonanalysis-box')!=null){
   			document.getElementById('reasonanalysis-box').style.display='block'
   		}
   		//显示“维度一”
   		if(document.getElementById('dimension-one-box')!=null){
   			document.getElementById('dimension-one-box').style.display='block'
   		}
   		//显示“维度二”
   		if(document.getElementById('dimension-two-box')!=null){
   			document.getElementById('dimension-two-box').style.display='block'
   		}
   		//显示“维度三”
   		if(document.getElementById('dimension-three-box')!=null){
   			document.getElementById('dimension-three-box').style.display='block'
   		}
   		//显示“维度四”
   		if(document.getElementById('dimension-four-box')!=null){
   			document.getElementById('dimension-four-box').style.display='block'
   		}
   		//显示“维度五”
   		if(document.getElementById('dimension-five-box')!=null){
   			document.getElementById('dimension-five-box').style.display='block'
   		}
   		//显示"操作区域"
   		if(document.getElementById('operation-box')!=null){
   			document.getElementById('operation-box').style.display='block'
   		}
   }else{
   		//进入这里表示当前报告是展开状态
   		//1.将标签的state属性设置为true表示已经是未展开状态的
   		document.getElementById('behavior-n-box').setAttribute('state','true')
   		//2.需要将文本和图片换成收起状态的
   		document.getElementById('behavior-img').setAttribute('src','images/tap-dowm.png')
   		document.getElementById('behavior-text').innerHTML='查看详细报告'
   		//先判断要隐藏的标签id是否存在，如果存在再隐藏，否则会报错
   		//隐藏"你的表现区域"
   		if(document.getElementById('assessresult-yourperformance')!=null){
   			document.getElementById('assessresult-yourperformance').style.display='none';
   		}
   		//隐藏"原因分析区域"
   		if(document.getElementById('reasonanalysis-box')!=null){
   			document.getElementById('reasonanalysis-box').style.display='none'
   		}
   		//隐藏"操作区域"
   		if(document.getElementById('operation-box')!=null){
   			document.getElementById('operation-box').style.display='none'
   		}
   		//隐藏“维度一”
   		if(document.getElementById('dimension-one-box')!=null){
   			document.getElementById('dimension-one-box').style.display='none'
   		}
   		//隐藏“维度二”
   		if(document.getElementById('dimension-two-box')!=null){
   			document.getElementById('dimension-two-box').style.display='none'
   		}
   		//隐藏“维度三”
   		if(document.getElementById('dimension-three-box')!=null){
   			document.getElementById('dimension-three-box').style.display='none'
   		}
   		//隐藏“维度四”
   		if(document.getElementById('dimension-four-box')!=null){
   			document.getElementById('dimension-four-box').style.display='none'
   		}
   		//隐藏“维度五”
   		if(document.getElementById('dimension-five-box')!=null){
   			document.getElementById('dimension-five-box').style.display='none'
   		}
   }
}


/**
 * 点击关闭按钮函数
 */
function closeTemplate(){
	//给最顶级的标签添加事件监听
	document.getElementById('result').addEventListener('tap',function(){
		//得到目标节点
		var target=event.target;
		//循环找到需要点击的标签
		while (target!=this){
			if(target.tagName.toLowerCase()=='img'){
				break;
			}
			target=target.parentNode;
		}
		//获取点击的标签自定义属性
		var indexTemplate=target.getAttribute('data-index');
		//获取顶级父节点
		var fatherNode=document.getElementById('result');
		//判断点击的是哪个标签
		switch(indexTemplate){
			case "1":
				//如果是1就替换成报告说明按钮
				var thisNode=document.getElementById('assess-result-reportdescription');
				var buttonNode=mButton('报告说明',1,'replace-bg-button');
				//将thisNode保存到为全局变量
				bgsmNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode)
			break;
			case "2":
				//如果是2就替换成摘要按钮
				var thisNode=document.getElementById('assessresult-abstract');
				var buttonNode=mButton('摘要',2,'replace-zy-button');
				//将thisNode保存到为全局变量
				zyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "3":
				//如果是3就替换成添加维度一按钮
				var thisNode=document.getElementById('dimension-one-box');
				var buttonNode=mButton('维度一',3,'replace-bx-button');
				//将thisNode保存到为全局变量
				ndbxNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "4":
				//如果是4就替换成维度一你的表现按钮
				var thisNode=document.getElementById('dimensionone-assessresult-yourperformance');
				var buttonNode=mButton('维度一你的表现',4,'replace-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "5":
				//如果是5就替换成维度一原因分析按钮
				var thisNode=document.getElementById('dimensionone-reasonanalysis-box');
				var buttonNode=mButton('维度一原因分析',5,'dimensionone-replace-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "6":
				//如果是6就替换成维度二按钮
				var thisNode=document.getElementById('dimension-two-box');
				var buttonNode=mButton('维度二',6,'dimension-two-button');
				//将thisNode保存到为全局变量
				twowdNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "7":
				//如果是7就替换成维度二你的表现按钮
				var thisNode=document.getElementById('dimensiontwo-yourperformance');
				var buttonNode=mButton('维度二你的表现',7,'dimensiontwo-ndbx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "8":
				//如果是8就替换成维度二原因分析按钮
				var thisNode=document.getElementById('dimensiontwo-reasonanalysis-box');
				var buttonNode=mButton('维度二原因分析',8,'dimensiontwo-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "9":
				//如果是9就替换成维度三按钮
				var thisNode=document.getElementById('dimension-three-box');
				var buttonNode=mButton('维度三',9,'dimension-three-button');
				//将thisNode保存到为全局变量
				threewdNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "10":
				//如果是10就替换成维度三你的表现按钮
				var thisNode=document.getElementById('dimensionthree-yourperformance');
				var buttonNode=mButton('维度三你的表现',10,'dimensionthree-ndbx-button');
				//将thisNode保存到为全局变量
				ndbxNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "11":
				//如果是8就替换成维度三原因分析按钮
				var thisNode=document.getElementById('dimensionthree-reasonanalysis-box');
				var buttonNode=mButton('维度三原因分析',11,'dimensionthree-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "12":
				//如果是12就替换成维度四按钮
				var thisNode=document.getElementById('dimension-four-box');
				var buttonNode=mButton('维度四',12,'dimension-four-button');
				//将thisNode保存到为全局变量
				fourwdNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "13":
				//如果是13就替换成维度四你的表现按钮
				var thisNode=document.getElementById('dimensionfour-yourperformance');
				var buttonNode=mButton('维度四你的表现',13,'dimensionthree-ndbx-button');
				//将thisNode保存到为全局变量
				ndbxNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "14":
				//如果是14就替换成维度四原因分析按钮
				var thisNode=document.getElementById('dimensionfour-reasonanalysis-box');
				var buttonNode=mButton('维度四原因分析',14,'dimensionfour-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "15":
				//如果是15就替换成维度五按钮
				var thisNode=document.getElementById('dimension-five-box');
				var buttonNode=mButton('维度五',15,'dimension-five-button');
				//将thisNode保存到为全局变量
				fivewdNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "16":
				//如果是16就替换成维度四你的表现按钮
				var thisNode=document.getElementById('dimensionfive-yourperformance');
				var buttonNode=mButton('维度五你的表现',16,'dimensionfive-ndbx-button');
				//将thisNode保存到为全局变量
				ndbxNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
			case "17":
				//如果是14就替换成维度四原因分析按钮
				var thisNode=document.getElementById('dimensionfive-reasonanalysis-box');
				var buttonNode=mButton('维度五原因分析',17,'dimensionfive-yyfx-button');
				//将thisNode保存到为全局变量
				yyNode=thisNode;
				thisNode.parentNode.replaceChild(buttonNode,thisNode);
			break;
		}
	});
}

/**
 *点击指定按钮增加固定选择器的子标签
 * @param {Object} selecter：选择器(标签的id)
 * @param {Object} button:按钮选择器(按钮标签的id)
 * @param {Object} elType：要追加的节点类型(仅限创建li和p标签)
 */
function increaseFun(selecter,buttonStr,elType,elClassName){
	//监听button的点击事件
	document.getElementById(buttonStr).addEventListener('tap',function(){
		//判断elType参数
		switch (elType){
			case "li":
					//创建一个要追加的子节点
					var li=document.createElement('li')
					li.contentEditable='true';
					li.innerHTML='点击这里填写内容';
					//给当前操作对象的子节点增加一个p标签
					document.getElementById(selecter).appendChild(li);
			break;
			case "p":
					//判断elClassName是否为空
					if(elClassName!=null){
						//进入这里表示不为空
						//创建一个要追加的子节点
						var p=document.createElement('p')
						//设置p标签可以实时编辑
						p.contentEditable='true';
						//设置追加的p标签的内容
						p.innerHTML='点击这里填写内容';
						//为创建的p标签追加className
						p.className=elClassName;
						//给当前操作对象的子节点增加一个p标签
						document.getElementById(selecter).appendChild(p);
					}else{
						//进入这里表示为空
						//创建一个要追加的子节点
						var p=document.createElement('p')
						//设置p标签可以实时编辑
						p.contentEditable='true';
						//设置追加的p标签的内容
						p.innerHTML='点击这里填写内容';
						//给当前操作对象的子节点增加一个p标签
						document.getElementById(selecter).appendChild(p);
					}
			break
		}
	});
}

/**
 * 维度一你的表现增加一行
 */
function addBiaoxian(){
	//监听button的点击事件
	document.getElementById("biaoxian-increaseF").addEventListener('tap',function(){
		//创建一个要追加的子节点
		var p=document.createElement('p')
		//设置p标签可以实时编辑
		p.contentEditable='true';
		//设置追加的p标签的内容
		//p.innerHTML='点击这里填写内容';
		//为创建的p标签追加className
		p.className="assessresult-yourperformance-content-tag";
		//创建一个span标签
		var span=document.createElement('span');
		//给span添加一个类标签
		span.className='yourperformancetag-text';
		//设置可以实时编辑
		span.contentEditable='true';
		//设置span的内容
		span.innerHTML='点击设置';
		//将span标签追加到p标签中
		p.appendChild(span);
		//创建第二个span标签
		var spantwo=document.createElement('span');
		//设置可以实时编辑
		spantwo.contentEditable='true';
		//设置span的内容
		spantwo.innerHTML='点击填写内容主体';
		//将第二个span标签追加到p标签中
		p.appendChild(spantwo);
		//给当前操作对象的子节点增加一个p标签
		document.getElementById("assessresult-yourperformance-content-box").appendChild(p);
	});
}

/**
 * 维度二你的表现增加一行
 */
function addTwoBiaoxian(){
	//监听button的点击事件
	document.getElementById("two-biaoxian-increaseF").addEventListener('tap',function(){
		//创建一个要追加的子节点
		var p=document.createElement('p')
		//设置p标签可以实时编辑
		p.contentEditable='true';
		//设置追加的p标签的内容
		//p.innerHTML='点击这里填写内容';
		//为创建的p标签追加className
		p.className="assessresult-yourperformance-content-tag";
		//创建一个span标签
		var span=document.createElement('span');
		//给span添加一个类标签
		span.className='yourperformancetag-text';
		//设置可以实时编辑
		span.contentEditable='true';
		//设置span的内容
		span.innerHTML='点击设置';
		//将span标签追加到p标签中
		p.appendChild(span);
		//创建第二个span标签
		var spantwo=document.createElement('span');
		//设置可以实时编辑
		spantwo.contentEditable='true';
		//设置span的内容
		spantwo.innerHTML='点击填写内容主体';
		//将第二个span标签追加到p标签中
		p.appendChild(spantwo);
		//给当前操作对象的子节点增加一个p标签
		document.getElementById("dimensiontwotwo-yourperformance-content-box").appendChild(p);
	});
}

/**
 * 维度三你的表现增加一行
 */
function addThreeBiaoxian(){
	//监听button的点击事件
	document.getElementById("three-biaoxian-increaseF").addEventListener('tap',function(){
		//创建一个要追加的子节点
		var p=document.createElement('p')
		//设置p标签可以实时编辑
		p.contentEditable='true';
		//设置追加的p标签的内容
		//p.innerHTML='点击这里填写内容';
		//为创建的p标签追加className
		p.className="assessresult-yourperformance-content-tag";
		//创建一个span标签
		var span=document.createElement('span');
		//给span添加一个类标签
		span.className='yourperformancetag-text';
		//设置可以实时编辑
		span.contentEditable='true';
		//设置span的内容
		span.innerHTML='点击设置';
		//将span标签追加到p标签中
		p.appendChild(span);
		//创建第二个span标签
		var spantwo=document.createElement('span');
		//设置可以实时编辑
		spantwo.contentEditable='true';
		//设置span的内容
		spantwo.innerHTML='点击填写内容主体';
		//将第二个span标签追加到p标签中
		p.appendChild(spantwo);
		//给当前操作对象的子节点增加一个p标签
		document.getElementById("dimensionthree-yourperformance-content-box").appendChild(p);
	});
}

/**
 * 维度四你的表现增加一行
 */
function addFourBiaoxian(){
	//监听button的点击事件
	document.getElementById("four-biaoxian-increaseF").addEventListener('tap',function(){
		//创建一个要追加的子节点
		var p=document.createElement('p')
		//设置p标签可以实时编辑
		p.contentEditable='true';
		//设置追加的p标签的内容
		//p.innerHTML='点击这里填写内容';
		//为创建的p标签追加className
		p.className="assessresult-yourperformance-content-tag";
		//创建一个span标签
		var span=document.createElement('span');
		//给span添加一个类标签
		span.className='yourperformancetag-text';
		//设置可以实时编辑
		span.contentEditable='true';
		//设置span的内容
		span.innerHTML='点击设置';
		//将span标签追加到p标签中
		p.appendChild(span);
		//创建第二个span标签
		var spantwo=document.createElement('span');
		//设置可以实时编辑
		spantwo.contentEditable='true';
		//设置span的内容
		spantwo.innerHTML='点击填写内容主体';
		//将第二个span标签追加到p标签中
		p.appendChild(spantwo);
		//给当前操作对象的子节点增加一个p标签
		document.getElementById("dimensionfour-yourperformance-content-box").appendChild(p);
	});
}

/**
 * 维度五你的表现增加一行
 */
function addFiveBiaoxian(){
	//监听button的点击事件
	document.getElementById("five-biaoxian-increaseF").addEventListener('tap',function(){
		//创建一个要追加的子节点
		var p=document.createElement('p')
		//设置p标签可以实时编辑
		p.contentEditable='true';
		//设置追加的p标签的内容
		//p.innerHTML='点击这里填写内容';
		//为创建的p标签追加className
		p.className="assessresult-yourperformance-content-tag";
		//创建一个span标签
		var span=document.createElement('span');
		//给span添加一个类标签
		span.className='yourperformancetag-text';
		//设置可以实时编辑
		span.contentEditable='true';
		//设置span的内容
		span.innerHTML='点击设置';
		//将span标签追加到p标签中
		p.appendChild(span);
		//创建第二个span标签
		var spantwo=document.createElement('span');
		//设置可以实时编辑
		spantwo.contentEditable='true';
		//设置span的内容
		spantwo.innerHTML='点击填写内容主体';
		//将第二个span标签追加到p标签中
		p.appendChild(spantwo);
		//给当前操作对象的子节点增加一个p标签
		document.getElementById("dimensionfive-yourperformance-content-box").appendChild(p);
	});
}

/**
 * 点击录入完毕，复制全部模板内容
 */
function copyTemplate(){
	//监听点击录入完毕按钮
	document.getElementById('insert-ok').addEventListener('tap',function(){
		//隐藏所有模块左上角的叉子按钮
		var colseImg=document.querySelectorAll('#colse-img');
		for(var i=0;i<colseImg.length;i++){
			//colseImg[i].style.display='none';
			colseImg[i].parentNode.removeChild(colseImg[i]);
		}
		//隐藏所有按钮
		var buttonClick=document.querySelectorAll('button');
		for(var i=0;i<buttonClick.length;i++){
			//increase[i].style.display='none';
			buttonClick[i].parentNode.removeChild(buttonClick[i]);
		}
		//将可编辑的标签设置为不可编辑
		var textStr=document.querySelectorAll('[contenteditable=true]')
		for(var i=0;i<textStr.length;i++){
			textStr[i].setAttribute('contenteditable','false');
		}
		//获取要复制的内容
		var content=document.getElementById('resultArea').innerHTML;
		//将内容复赋值到内容池中
		//document.getElementById('content-pool').innerHTML=content;
	});
	
}
/**
 * 监听所有p标签和span标签还有li标签的内容如果内容为空就删除当前标签
 */
function claerContent(){
	//监听
	document.getElementById('resultArea').addEventListener('mouseout',function(){
		//获取目标节点
		var target=event.target;
		while(target!=this){
			if(target.tagName.toLowerCase()=='span'){
			break;
			}
			if(target.tagName.toLowerCase()=='li'){
			break;
			}
			if(target.tagName.toLowerCase()=='p'){
			break;
			}
			target=target.parentNode;
		}
		//如果当前节点的内容等于空就删除当前节点
		if(target.innerHTML==''||target.innerHTML==null){
			target.parentNode.removeChild(target);
		}
	})
}


/**
 * 创建添加按钮
 * @param {Object} ButString:按钮名称
 * @param {Object} index:自定义下标
 * @param {Object} id:设置该标签id属性
 */
var mButton=function CreateButton(ButString,index,id){
	//创建一个mui的按钮
	var mButton=document.createElement('button');
	//设置按钮的属性
	mButton.type='button';
	mButton.className='mui-btn mui-btn-primary mui-btn-outlined add-button';
	mButton.innerHTML='添加'+ButString;
	mButton.setAttribute('data-index',index);
	//判断id参数是否为空
	if(id!=null){
		mButton.setAttribute('id',id);
	}
	//返回创建的按钮
	return mButton;
}



/**
 * 点击添加按钮事件
 */
function mButtinClick(){
	//监听
	document.getElementById('resultArea').addEventListener('click',function(){
		//获取目标节点
		var target=event.target;
		while(target!=this){
			if(target.tagName.toLowerCase()=='button'){
			break;
			}
			target=target.parentNode;
		}
		//获取按钮的data-index
		var index=target.getAttribute('data-index');
		//判断点击的是哪个版块的按钮
		switch (index){
			case '1':
				//将被删除的报告说明模块替换会原应显示的内容
				var bg_button=document.getElementById('replace-bg-button');
				bg_button.parentNode.replaceChild(bgsmNode,bg_button);
			break;
			case '2':
				//将被删除的摘要模块替换会原应显示的内容
				var bg_button=document.getElementById('replace-zy-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(zyNode,bg_button);
			break;
			case '3':
				//将被删除的你的表现模块替换会原应显示的内容
				var bg_button=document.getElementById('replace-bx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(ndbxNode,bg_button);
			break;
			case '4':
				//将被删除的推荐阅读模块替换会原应显示的内容
				var bg_button=document.getElementById('replace-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '5':
				//将被删除的维度一原因分析模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionone-replace-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '6':
				//将被删除的维度二模块替换会原应显示的内容
				var bg_button=document.getElementById('dimension-two-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(twowdNode,bg_button);
			break;
			case '7':
				//将被删除的维度二你的表现模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensiontwo-ndbx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '8':
				//将被删除的维度二原因分析模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensiontwo-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '9':
				//将被删除的维度三模块替换会原应显示的内容
				var bg_button=document.getElementById('dimension-three-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(threewdNode,bg_button);
			break;
			case '10':
				//将被删除的维度三你的表现模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionthree-ndbx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(ndbxNode,bg_button);
			break;
			case '11':
				//将被删除的维度三原因分析模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionthree-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '12':
				//将被删除的维度四模块替换会原应显示的内容
				var bg_button=document.getElementById('dimension-four-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(fourwdNode,bg_button);
			break;
			case '13':
				//将被删除的维度三你的表现模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionthree-ndbx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(ndbxNode,bg_button);
			break;
			case '14':
				//将被删除的维度三原因分析模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionfour-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
			case '15':
				//将被删除的维度四模块替换会原应显示的内容
				var bg_button=document.getElementById('dimension-five-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(fivewdNode,bg_button);
			break;
			case '16':
				//将被删除的维度三你的表现模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionfive-ndbx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(ndbxNode,bg_button);
			break;
			case '17':
				//将被删除的维度三原因分析模块替换会原应显示的内容
				var bg_button=document.getElementById('dimensionfive-yyfx-button');
				//调用实际替换函数
				bg_button.parentNode.replaceChild(yyNode,bg_button);
			break;
		}
	})
};

/**
 * 保存结论
 * @param {Object} Str:要保存的内容
 */
function SaveConclusion(Str){
	//ajax请求后台接口
	mui.ajax(baseUrl+'/NewIns/');
}
