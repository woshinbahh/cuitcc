package com.cuitcc.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * BlogInterceptor
 */
public class UserInterceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		System.out.println("Before invoking " + ai.getActionKey());
		ai.invoke();
		System.out.println("After invoking " + ai.getActionKey());
	}
}
