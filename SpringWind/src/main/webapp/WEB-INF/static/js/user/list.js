/**
 * 用户管理JS
 * 
 * @Author Jack
 */
$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
})

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#dataTable').bootstrapTable({
            url: '/user/getUserList.html',     //请求后台的URL（*）
            method: 'get',           //请求方式（*）
            toolbar: '#toolbar',        //工具按钮用哪个容器
            striped: true,           //是否显示行间隔色
            cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,          //是否显示分页（*）
            sortable: false,           //是否启用排序
            sortOrder: "asc",          //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",      //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,            //初始化加载第一页，默认第一页
            pageSize: 10,            //每页的记录行数（*）
            pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
            strictSearch: true,
            clickToSelect: true,        //是否启用点击选中行
            height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",           //每一行的唯一标识，一般为主键列
            cardView: false,          //是否显示详细视图
            detailView: false,          //是否显示父子表
            columns: [{
                field: 'id',
                title: '序号'
            }, {
                field: 'loginName',
                title: '登录名'
            }, {
                field: 'email',
                title: '邮箱'
            },{
                field: 'type',
                title: '用户类型'
            }, {
                field: 'status',
                title: '状态̬'
            }, {
                field: 'crTime',
                title: '创建时间'
            }, {
                field: 'lastTime',
                title: '最后更新时间'
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {  //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
    		_size: params.limit,  //页面大小
    		_index: params.offset + 1, //页码
            /*sdate: $("#stratTime").val(),
            edate: $("#endTime").val(),
            sellerid: $("#sellerid").val(),
            orderid: $("#orderid").val(),
            CardNumber: $("#CardNumber").val(),
            maxrows: params.limit,
            pageindex: params.pageNumber,
            portid: $("#portid").val(),
            CardNumber: $("#CardNumber").val(),
            tradetype: $('input:radio[name="tradetype"]:checked').val(),
            success: $('input:radio[name="success"]:checked').val(),*/
        };
        return temp;
    };
    return oTableInit;
};