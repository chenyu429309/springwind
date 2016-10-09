/**
 * TABLE JS
 * 
 * @Author hubin
 */
var TableInit = function (id) {
    var oTableInit = new Object();
    //初始化 Table 参数配置
    oTableInit.Init = function (options) {
        options = $.extend({
            striped: true,           //是否显示行间隔色
            cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,          //是否显示分页（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            responseHandler: oTableInit.responseHandler,//数据处理（*）
            sidePagination: 'server',      //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,            //初始化加载第一页，默认第一页
            pageSize: 10,            //每页的记录行数（*）
            pageList: [10, 25, 50, 100],    //可供选择的每页的行数（*）
            strictSearch: true,
            sortOrder: 'desc',  //排序默认 desc
            //clickToSelect: true,        //是否启用点击选中行
            //height: 460,            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            cardView: false          //是否显示详细视图
        }, 
        options);

        $('#'+id).bootstrapTable(options);
    };
    //查询参数
    oTableInit.queryParams = function (params) {
        return dataQueryParams(params);
    };
    //处理服务器返回数据
//    oTableInit.responseHandler = function (res) {
//        var data = [];
//        var obj = eval(res);
//        if (obj.success) {
//            data['total'] = obj.item.totalCount;
//            data['rows'] = obj.item.results;
//        }
//        return data;
//    };
    return oTableInit;
};

//数据查询结果返回内容
var dataParams = {};
function dataQueryParams(params) {
    dataParams['limit']=params.limit;//页面大小
    dataParams['start']=params.offset; //页码
    dataParams['orderBy']=params.sort; //排序字段
    //排序顺序
    if(params.order=='asc'){
        dataParams['isASC']=true;
    }else{
        dataParams['isASC']=false;
    }
    return dataParams;
}

//刷新表格
function refreshTable(msg) {
    layer.msg(msg, {icon : 1});
    $table.bootstrapTable('refresh');
}

//获取选择ID集合
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}
