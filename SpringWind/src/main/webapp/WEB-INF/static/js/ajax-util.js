/**
 * AJAX 封装，依赖 jQuery 、 layer 
 * 
 * AjaxUtil.request({
 * 		url: '/sample/delete?ids=' + id,
 * 		callback: function(data) {
 * 			alert(data);
 * 		}
 * });
 * 
 * @Author hubin
 */
var AjaxUtil = {

	//基础选项
	options : {
		method : "POST",
		url : "",
		params : "",
		async : false,
		callback : function(data) {
		}//回调函数 required
	},
	//设置基础选项
	setOptions : function(newOptions) {
		for ( var pro in newOptions) {
			this.options[pro] = newOptions[pro];
		}
	},
	//发送Ajax请求
	request : function(options) {
		//设置参数
		var ajaxObj = this;
		ajaxObj.setOptions.call(ajaxObj, options);
		var opts = ajaxObj.options;

		//请求的方式
		$.ajax({
			url : opts.url,
			type : opts.method,
			data : opts.params,
			async: opts.async,
			error : function(jqXHR, status, error) {
				layer.msg("操作失败，请联系系统管理员或稍后再试！", {icon : 5});
			},
			success : function(data) {
				var obj = $.parseJSON(data);
				if (obj.success) {
					ajaxObj.options.callback(obj);
				} else {
					layer.msg(obj.message, {icon : 5});
				}
			}
		});
	}
};