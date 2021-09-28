let MethodUtils = function () {
    var ajax = function(option){
        var type = option.type==null ? "post" : option.type;
        var url = option.url==null ? "" : option.url;
        var async = option.async==null ? true : option.async;
        var contentType = option.contentType==null ? "application/x-www-form-urlencoded;charset=UTF-8" : option.contentType;
        var dataType = option.dataType==null ? "json" : option.dataType;
        var enctype = option.enctype==null ? null : option.enctype;
        var processData = option.processData==null ? true : option.processData;
        var data = option.data==null ? null : option.data;
        var cache = option.cache==null ? true : option.cache;
        var beforeSend = $.isFunction(option.beforeSend) ? option.beforeSend : function(){};
        var success = $.isFunction(option.success) ? option.success : function(){};
        var complete = $.isFunction(option.complete) ? option.complete : function(){};
        var error = $.isFunction(option.error) ? option.error : function(){};
        var successMsg = option.successMsg==null ? "" : option.successMsg;
        var oHeader = option.header==null ? null : option.header;

        if(url){
            $.ajax({
                type: type,
                url: url,
                async: async,
                contentType: contentType,
                dataType: dataType,
                enctype: enctype,
                processData: processData,
                data: data,
                cache: cache,
                beforeSend: function(xmlHttpRequest){
                    xmlHttpRequest.setRequestHeader("AJAX", true);
                    beforeSend(xmlHttpRequest);
                },
                success: function(response){
                    success(response);
                },
                error: function(response, textStatus, errorThrown){
                    error(response, textStatus, errorThrown);
                },
            });
        }else{
            toastr["error"]("URL Empty");
        }
    }
    return {
        ajax: function (options) {
            ajax(options);
        },
        matchPhone : function (input) {
            return (input.match(RegexUtils.getRegexPhoneNotDash()) !== null)
                || (input.match(RegexUtils.getRegexPhone()) !== null);
        }
    }
}
let RegexUtils = function () {
    return {
        getRegexPhoneNotDash: function () {
            return /^\d{11}$/;
        },
        getRegexPhone: function () {
            return /^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$/;
        },
        getRegexPassword: function () {
            return /(?=.*\d)(?=.*[a-z]).{8,}/;
        },
        getRegexEmail: function () {
            return /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/;
        }
    }
}