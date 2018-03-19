var comapi = new ComAPI();
function ComAPI()
{
	this.ajaxRequest = function(param){
		$.ajax({
			type : param.type == null ? 'get' : param.type,
			url : param.url,
			data : param.data,
			dataType : "json",
			async : true,
			cache : false,
			success : param.success,
			error : param.error
		});
	}
}