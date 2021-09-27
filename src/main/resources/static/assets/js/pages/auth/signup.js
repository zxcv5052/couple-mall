"use strict";

// Class definition
var SignUp = function () {
	// Base elements
	var _wizardEl;
	var _formEl;
	var _wizard;
	var _validations = [];

	// Private functions
	var initWizard = function () {
		// Initialize form wizard
		_wizard = new KTWizard(_wizardEl, {
			startStep: 1, // initial active step number
			clickableSteps: true  // allow step clicking
		});

		// Validation before going to next page
		_wizard.on('beforeNext', function (wizard) {
			_validations[wizard.getStep() - 1].validate().then(function (status) {
				if (status === 'Valid') {
					_wizard.goNext();
					KTUtil.scrollTop();
				} else {
					Swal.fire({
						text: "오류가 발생하였습니다. 확인해주세요.",
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

	var initValidation = function () {
		// Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
		// Step 1
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

				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap()
				}
			}
		));
	}

	return {
		// public functions
		init: function () {
			_wizardEl = KTUtil.getById('kt_wizard_v4');
			_formEl = KTUtil.getById('kt_form');

			initWizard();
			initValidation();
		}
	};
}();

jQuery(document).ready(function () {
	SignUp.init();
});
