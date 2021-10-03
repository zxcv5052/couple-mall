let MethodUtils = function () {
    let swalFire = function(option){
        let text = option.text === undefined ? "입력 값을 확인 하세요." : option.text;
        let success = option.success ? "success" : "error";
        let confirmButtonText = option.confirmButtonText === undefined ? "OK" : option.confirmButtonText;
        let afterComplete = $.isFunction(option.after) ? option.after : function(){};
        Swal.fire({
            text: text,
            icon: success,
            buttonsStyling: false,
            confirmButtonText: confirmButtonText,
            customClass: {
                confirmButton: "btn font-weight-bold btn-light"
            }
        }).then(function () {
            afterComplete();
        });
    }
    let ajax = function(option){
        let type = option.type === undefined ? "post" : option.type;
        let url = option.url === undefined ? "" : option.url;
        let async = option.async === undefined ? true : option.async;
        let contentType = option.contentType === undefined ? "application/json" : option.contentType;
        let dataType = option.dataType === undefined ? "json" : option.dataType;
        let enctype = option.enctype === undefined ? null : option.enctype;
        let processData = option.processData === undefined ? true : option.processData;
        let data = option.data === undefined ? null : JSON.stringify(option.data);
        let cache = option.cache === undefined ? true : option.cache;
        let beforeSend = $.isFunction(option.beforeSend) ? option.beforeSend : function(){};
        let success = $.isFunction(option.success) ? option.success : function(){};
        let error = $.isFunction(option.error) ? option.error : function(){};
        let header = option.header === undefined ? null : option.header;
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
    }
    return {
        swalFire : function (options) {
            swalFire(options);
        },
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