package com.cuitcc.validator;

import com.cuitcc.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class UserValidator extends Validator {

	protected void validate(Controller controller) {
		validateRequiredString("user.name", "nameMsg", "请输入用户名");
		validateRequiredString("user.password", "passwordMsg", "请输入用户密码");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(User.class);

		String actionKey = getActionKey();
		if (actionKey.equals("/user/save"))
			controller.render("add.html");
		else if (actionKey.equals("/user/update"))
			controller.render("edit.html");
	}
}