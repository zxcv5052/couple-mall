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
					_wizard.goNext();
					KTUtil.scrollTop();
				} else {
					Swal.fire({
						text: "입력 값을 확인 하세요.",
						icon: "error",
						buttonsStyling: false,
						confirmButtonText: "OK",
						customClass: {
							confirmButton: "btn font-weight-bold btn-light"
						}
					}).then(function () {

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
							// 나중에 이메일 체크 만들면 처리해야함
							// callback: {
							// 	message: '중복된 이메일입니다.',
							// 	callback: function(input) {
							// 		return $("input[name=validEmail]").val() !== 'true';
							// 	}
							// }
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
							callback: {
								message: '번호 입력을 확인 하세요.',
								callback: function(input) {
									console.log($("input[name=phone]").val());
									console.log(MethodUtils.matchPhone($("input[name=phone]").val()))
									return MethodUtils.matchPhone($("input[name=phone]").val());
								}
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
			const data = {
				"email" : $("input[name=email]").val()
			}
			MethodUtils.ajax({
				"url" : ""
			})
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
		}
	};
}();
jQuery(document).ready(function () {
	SignUp.init();
});
