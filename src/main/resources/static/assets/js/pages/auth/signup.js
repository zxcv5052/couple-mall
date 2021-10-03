"use strict";

// Class definition
let SignUp = function () {
	// Base elements
	let _wizardEl;
	let _formEl;
	let _wizard;
	let _validations = [];

	// Private functions
	let initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number
			clickableSteps: true  // allow step clicking

		});
		// Validation before going to next page
		_wizard.on('beforeNext', function (wizard) {
			_validations[wizard.getStep()-1].validate().then(function (status) {
				if (status === 'Valid') {
					if(wizard.getStep() === 2){
						if($("input[name=validEmail]").val() !== 'true'){
							MethodUtils().swalFire({
								success: false,
								text : "중복을 확인해주세요."
							})
							return;
						}
					}
					_wizard.goNext();
					KTUtil.scrollTop();
				} else {
					MethodUtils().swalFire({
						success : false
					});
				}
			});
			_wizard.stop();  // Don't go to the next step
		});

		// Change event
		_wizard.on('change', function (wizard) {
			KTUtil.scrollTop();
		});
	}

	let initValidation = function () {
		_validations.push(FormValidation.formValidation(
			_formEl,
			{
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap()
				}
			}
		));

		// Step 2
		_validations.push(FormValidation.formValidation(
			_formEl,
			{
				fields: {
					email: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							},
							emailAddress: {
								message: '이메일 형식이 아닙니다.'
							}
						}
					},
					password: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							},
							minLength: {
								message: '비밀번호는 8자리 이상으로 입력해주세요.'
							},
							maxLength: {
								message: '비밀번호는 14자리 이하로 입력해주세요.'
							}
						}
					},
					checkPassword: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							},
							callback: {
								message: '비밀번호를 확인해주세요.',
								callback: function(input) {
									return $("input[name=checkPassword]").val() === $("input[name=password]").val();
								}
							}
						}
					},
					dividerAuth: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							}
						}
					}
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap()
				}
			}
		));

		// Step 3
		_validations.push(FormValidation.formValidation(
			_formEl,
			{
				fields: {
					name: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							}
						}
					},
					nickname: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							}
						}
					},
					phone: {
						validators: {
							notEmpty: {
								message: '필수값 입니다.'
							},
							regexp: {
								regexp: RegexUtils().getRegexPhone(),
								message: '번호 입력을 확인 하세요.'
							}
						}
					}
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap()
				}
			}
		));
	}
	let submitListener = function () {
		$("button[name=submitForm]").on('click', function (e) {
			e.preventDefault();
			_validations[2].validate().then(function (status) {
				if (status === 'Valid') {
					if($("input[name=validNickname]").val() !== 'true'){
						MethodUtils().swalFire({
							success: false,
							text : "중복을 확인해주세요."
						})
					}else{
						const data = {
							email : $("input[name=email]").val(),
							password : $("input[name=password]").val(),
							nickname : $("input[name=nicknmae]").val(),
							name : $("input[name=name]").val(),
							dividerAuth : $("input[name=dividerAuth]").val(),
							phone : $("input[name=phone]").val(),
							addressCd : $("input[name=addressCd]").val(),
							address : $("input[name=address]").val(),
							addressExact : $("input[name=addressExact]").val()
						}
						alert("good!")
					}
				} else {
					MethodUtils().swalFire({
						success : false
					});
				}
			});
		})
	}
	let validEmailListener = function(){
		$("button[name=valid-email]").on('click', function (e) {
			e.preventDefault();
			const email = $("input[name=email]").val();
			const data = {
				email : email
			}
			if(email === '' || email.match(RegexUtils().getRegexEmail()) === null) {
				MethodUtils().swalFire({
					success : false
				})
			}else{
				MethodUtils().ajax({
					url : "/api/check/email",
					type : "POST",
					data : data,
					success : function (response) {
						if(!response.success){
							$("input[name=validEmail]").val("true");
							$("input[name=email]").attr('readonly', true);
						}else{
							$("input[name=validEmail]").val("false");
						}
					},
					error : function (error) {
						console.log(error);
					}
				})
			}
		})
	}

	let validNicknameListener = function(){
		$("button[name=valid-nickname]").on('click', function (e) {
			e.preventDefault();
			const nickname = $("input[name=nickname]").val();
			const data = {
				nickname : nickname
			}
			if(nickname === '') {
				MethodUtils().swalFire({
					success : false
				})
			}else{
				MethodUtils().ajax({
					url : "/api/check/nickname",
					type : "POST",
					data : data,
					success : function (response) {
						if(!response.success){
							$("input[name=validNickname]").val("true");
							$("input[name=nickname]").attr('readonly', true);
						}else{
							$("input[name=validNickname]").val("false");
						}
					},
					error : function (error) {
						console.log(error);
					}
				})
			}
		})
	}
	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('kt_wizard_v4');
			_formEl = KTUtil.getById('kt_form');

			initWizard();
			initValidation();
			submitListener();
			validEmailListener();
			validNicknameListener();
		}
	};
}();
jQuery(document).ready(function () {
	SignUp.init();
});
