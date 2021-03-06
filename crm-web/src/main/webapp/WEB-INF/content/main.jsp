<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chinahanjiang.crm.dto.UserDto;" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>themes/material/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#r').click(function(){
			
			$('#dalytaskgrid').datagrid('reload');
			$('#undotaskgrid').datagrid('reload');
			$('#dalyitemgrid').datagrid('reload');
			$('#undoitemgrid').datagrid('reload');
		});
		
		$('#logout').click(function(){
			
			window.location.href= "<%=basePath%>login/out.do";
		});
		
		var remarkFormater = function(value, row, index){   
			alert("value="+value+"  index="+index);
			var content = '';
			var abValue = value +'';
			if(value != undefined){
				
				if(value.length>=22) {    
					abValue = value.substring(0,19) + "..."; 
					content = '<a href="javascript:;"  title="' + value + '" class="easyui-tooltip">' + abValue + '</a>'; 
					}else{     
						content = '<a href="javascript:;"  title="' + abValue + '" class="easyui-tooltip">' + abValue + '</a>';  
						} 
				}  
			return content;
		};
		
		/*
		$('#dalytaskgrid').datagrid('getPanel').find('.easyui-tooltip').each(function(){
	        var index = parseInt($(this).attr('data-p1'));
	        $(this).tooltip({
	            content: $('<div></div>'),
	            onUpdate: function(cc){
	                var row = $('#dalytaskgrid').datagrid('getRows')[index];
	                var content = '<div>content</div><ul>';
	                content += '<li>name: '+row.A+'</li>';
	                content += '<li>B: '+row.B+'</li>';
	                content += '<li>C: '+row.C+'</li>';
	                content += '<li>D: '+row.D+'</li>';
	                content += '</ul>';
	                cc.panel({
	                    width:200,
	                    content:content
	                });
	            },
	            position:'right'
	        });
	    });
		*/
	});
</script>
<title>任务管理信息系统</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 80px; padding-top: 10px; padding-left: 15px; background: #F8F8FF;">
		<div>
			<span style="font-family:微软雅黑,宋体; font-size:2em;">韩江机械任务管理信息系统v1.0</span>
		</div>
		<div style="float:right;padding-right:50px;">
			
			<span style="font-family:微软雅黑,宋体; font-size:14px;">你好,
				<%=((UserDto)session.getAttribute("user")).getName() %>
				<a id="logout" href="#" style="padding-left:5px;">退出</a>
			</span>
		</div>
	</div>
	
	<div data-options="region:'west',title:'导航栏',border:true"
		style="width: 150px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="项目管理" data-options="selected:true" style="padding: 10px;">
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('tasklist','项目列表','<%=basePath%>win/tasklist.do')">
						<img alt="项目列表" src="<%=basePath%>icon/project.png">
						<div>项目列表</div>
					</div>
				</div>
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('itemlist','任务列表','<%=basePath%>win/itemlist.do')">
						<img alt="任务列表" src="<%=basePath%>icon/task.png">
						<div>任务列表</div>
					</div>
				</div>
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('tasktypemgr','项目类型管理','<%=basePath%>win/tasktypelist.do')">
						<img alt="项目类型管理" src="<%=basePath%>icon/projecttype.png">
						<div>项目类型管理</div>
					</div>
				</div>
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('itemtypemgr','任务类型管理','<%=basePath%>win/itemtypelist.do')">
						<img alt="任务类型管理" src="<%=basePath%>icon/tasktype.png">
						<div>任务类型管理</div>
					</div>
				</div>
			</div>
			<div title="客户管理" style="padding: 10px;">
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('customerlist','客户列表','<%=basePath%>win/customerlist.do')">
						<img alt="客户列表" src="<%=basePath%>icon/customer.png">
						<div>客户列表</div>
					</div>
				</div>
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('groupslist','集团管理','<%=basePath%>win/groupslist.do')">
						<img alt="集团管理" src="<%=basePath%>icon/groups.png">
						<div>集团管理</div>
					</div>
				</div>
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('arealist','区域管理','<%=basePath%>win/arealist.do')">
						<img alt="区域管理" src="<%=basePath%>icon/area.png">
						<div>区域管理</div>
					</div>
				</div>
			</div>
			<div title="产品管理" style="padding: 10px;">
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('productslist','产品列表','<%=basePath%>win/productlist.do')">
						<img alt="产品列表" src="<%=basePath%>icon/product.png">
						<div>产品列表</div>
					</div>
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('catalogManager','产品类型列表','<%=basePath%>win/cataloglist.do')">
						<img alt="产品类型列表" src="<%=basePath%>icon/catalog.png">
						<div>产品类型管理</div>
					</div>
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('unitmgr','单位管理','<%=basePath%>win/unitlist.do')">
						<img alt="单位管理" src="<%=basePath%>icon/unit.png">
						<div>单位管理</div>
					</div>
				</div>
			</div>
			<div title="报表管理" style="padding: 10px">
				<div style="width: 100%; text-align: center">
					
				</div>
			</div>
			<div title="系统设置" style="padding: 10px">
				<div style="width: 100%; text-align: center">
					<div style="padding-top: 10px; cursor: pointer;"
						onclick="addPanel('userlist','用户管理','<%=basePath%>win/userlist.do')">
						<img alt="用户管理" src="<%=basePath%>icon/user.png">
						<div>用户管理</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height: 10px; background: #F0F0F0; padding: 10px;"></div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="border:false"
			style="width: auto; height: 660px;">
			<div title="主页" data-options="tools:'#p-tools'" style="padding: 10px">

				<div style="padding-top: 15px;">

					<div style="padding-left: 20px; display: block; float: left;">
						<div id="p1" class="easyui-panel" title='当天的任务列表' data-options="border:true,tools:'#gt1'"
							style="width: 600px; height: 290px;">
							<table id="dalytaskgrid" class="easyui-datagrid"
								data-options="
											url:'<%=basePath %>task/dalytask.do',
											loadMsg:'数据加载中请稍后……',  
											height: 260, 
											collapsible:false,
											showHeader:true,
											pagination:true,
											border:false,
											onDblClickRow:openTaskDetail
											">
								<thead>
									<tr>
										<th data-options="field:'id',width:30,hidden:true"></th>
										<th data-options="field:'code',width:125,align:'center'">编号</th>
										<th data-options="field:'taskType',width:80,align:'center'">类型</th>
										<th data-options="field:'name',width:160,align:'center'">内容</th>
										<th data-options="field:'customer',width:180,align:'center'">客户</th>
										<th data-options="field:'statusStr',width:30,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							
							<div id="gt1">
								<a href="javascript:void(0)" class="icon-add" onclick="addTask()"></a>
							</div>
							
						</div>
					</div>
					<div style="padding-left: 10px; display: block; float: left;">
						<div id="p2" class="easyui-panel" title='当天的事件列表' data-options="border:true,tools:'#gt2'"
							style="width: 600px; height: 290px;">
							<table id="dalyitemgrid" class="easyui-datagrid"
								data-options="
											url:'<%=basePath %>item/dalyitem.do',
											loadMsg:'数据加载中请稍后……',  
											height: 260, 
											collapsible:false,
											showHeader:true,
											pagination:true,
											border:false,
											onDblClickRow:openItemDetail
											">
					
								<thead>
									<tr>
										<th data-options="field:'id',width:30,hidden:true"></th>
										<th data-options="field:'code',width:140,align:'center'">编号</th>
										<th data-options="field:'taskType',width:70,align:'center'">类型</th>
										<th data-options="field:'name',width:180,align:'center'">内容</th>
										<th data-options="field:'customer',width:160,align:'center'">客户</th>
										<th data-options="field:'statusStr',width:30,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="gt2">
								<a href="javascript:void(0)" class="icon-more" onclick="moreItem1()"></a>
							</div>
						</div>
					</div>
					
					<div style="clear: both;"></div>

					<div style="padding-left: 20px; padding-top:10px; display: block; float: left;">
						<div id="p4" class="easyui-panel" title="未完成任务列表" data-options="border:true,tools:'#gt3'"
						style="width: 600px; height: 290px;">
							<table id="undotaskgrid" class="easyui-datagrid"
								data-options="
											url:'<%=basePath %>task/undotask.do',
											loadMsg:'数据加载中请稍后……',  
											height: 260, 
											collapsible:false,
											showHeader:true,
											pagination:true,
											border:false,
											onDblClickRow:openTaskDetail
											">
								<thead>
									<tr>
										<th data-options="field:'id',width:30,hidden:true"></th>
										<th data-options="field:'code',width:125,align:'center'">编号</th>
										<th data-options="field:'taskType',width:80,align:'center'">类型</th>
										<th data-options="field:'name',width:160,align:'center'">内容</th>
										<th data-options="field:'customer',width:180,align:'center'">客户</th>
										<th data-options="field:'statusStr',width:30,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="gt3">
								<a href="javascript:void(0)" class="icon-more" onclick="moreTask()"></a>
							</div>
						</div>
					</div>
					<div
						style="padding-left: 10px; padding-top:10px; display: block; float: left;">
						<div id="p5" class="easyui-panel"  title="未完成事件"  data-options="border:true,tools:'#gt4'"
							style="width: 600px; height: 290px;">
								<table id="undoitemgrid" class="easyui-datagrid"
								data-options="
											url:'<%=basePath %>item/undoitem.do',
											loadMsg:'数据加载中请稍后……',  
											height: 260, 
											collapsible:false,
											showHeader:true,
											pagination:true,
											border:false,
											onDblClickRow:openItemDetail
											">
								<thead>
									<tr>
										<th data-options="field:'id',width:30,hidden:true"></th>
										<th data-options="field:'code',width:140,align:'center'">编号</th>
										<th data-options="field:'tasktype',width:70,align:'center'">类型</th>
										<th data-options="field:'name',width:180,align:'center'">内容</th>
										<th data-options="field:'customer',width:160,align:'center'">客户</th>
										<th data-options="field:'statusStr',width:30,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="gt4">
								<a href="javascript:void(0)" class="icon-more" onclick="moreItem2()"></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="p-tools">
        <a id="r" href="javascript:void(0)" class="icon-mini-refresh"></a>
    </div>
	<script type="text/javascript">

	    function addPanel(id,title, href){
	        var tt = $('#tabs');  
	        if (tt.tabs('exists', title)){//如果tab已经存在,则选中并刷新该tab          
	            tt.tabs('select', title);  
	            refreshTab({tabTitle:title,url:href});  
	        } else {  
	            if (href){  
	               var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';
	               //var content = href;
	            } else {  
	                var content = '未实现';  
	            }  
	            tt.tabs('add',{ 
	            	id:id,
	                title:title,  
	                closable:true,  
	                content:content
	            });  
	        }  
	    }  
	    /**     
	     * 刷新tab 
	     * @cfg  
	     *example: {tabTitle:'tabTitle',url:'refreshUrl'} 
	     *如果tabTitle为空，则默认刷新当前选中的tab 
	     *如果url为空，则默认以原来的url进行reload 
	     */  
	    function refreshTab(cfg){  
	        var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');  
	        if(refresh_tab && refresh_tab.find('iframe').length > 0){  
	        var _refresh_ifram = refresh_tab.find('iframe')[0];  
	        var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;  
	        //_refresh_ifram.src = refresh_url;  
	        _refresh_ifram.contentWindow.location.href=refresh_url;  
	        }  
	    }
	    
	    function openAndClose(newObj,oldTitle){
	    	
	    	 var id = newObj.id;
	    	 var title = newObj.title;
	    	 var href = newObj.href;
	    	 
	    	 closePanel(oldTitle);
	    	 addPanel(id,title,href);
	    	 
	    }
	    
	    function openPanel(newObj){
	    	
	    	 var id = newObj.id;
	    	 var title = newObj.title;
	    	 var href = newObj.href;
	    	 
	    	 addPanel(id,title,href);
	    }
	    
	    function closePanel(title){
	    	
	    	 var tt = $('#tabs');  
		        if (tt.tabs('exists', title)){
		        	tt.tabs('close', title);  
		        }
	    }
	     
	    function addTask(){
	    	
	    	addPanel('taskedit','项目编辑','<%=basePath%>win/addtask.do');
	    }
	    
	    function moreTask(){
	    	
	    	addPanel('tasklist','项目列表','<%=basePath%>win/tasklist.do');
	    }
	    
	    function moreItem1(){
	    	
	    	addPanel('itemlist','任务列表','<%=basePath%>win/itemlist.do');
	    }
	    
	    function moreItem2(){
	    	
	    	addPanel('itemlist','任务列表','<%=basePath%>win/itemlist.do');
	    }
	    
	    function openTaskDetail(index,row){
	    	
	    	var _id = row.id;
	    	addPanel('taskdetail','项目详情','<%=basePath%>win/taskdetail.do?taskId='+_id);
	    }
	    
 		function openItemDetail(index,row){
	    	
	    	var _id = row.id;
	    	addPanel('itemdetail','任务详情','<%=basePath%>win/itemdetail.do?itemId='+_id);
	    }
	</script>
</body>
</html>