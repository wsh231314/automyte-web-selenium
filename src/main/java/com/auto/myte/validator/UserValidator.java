package com.auto.myte.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.auto.myte.form.LoginForm;

public class UserValidator implements ConstraintValidator<CheckPassword, LoginForm> {

	@Override
	public void initialize(CheckPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(LoginForm user, ConstraintValidatorContext context) {
		if (user == null) {
			return true;
		}

		// not empty
		if (StringUtils.isEmpty(user.getEid())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("ユーザID: 入力してください。").addPropertyNode("eid")
					.addConstraintViolation();
			return false;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("パスワード: 入力してください。").addPropertyNode("password")
					.addConstraintViolation();
			return false;
		}
		if (StringUtils.isEmpty(user.getPasswordConfrim())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("確認パスワード: 入力してください。").addPropertyNode("passwordConfrim")
					.addConstraintViolation();
			return false;
		}

		// 两次密码不一样
		if (!user.getPassword().trim().equals(user.getPasswordConfrim().trim())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("パスワードとパスワード確認は一致ではありません。").addPropertyNode("password")
					.addConstraintViolation();
			return false;
		}
		return true;
	}

}
