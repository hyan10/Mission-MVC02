package kr.co.bit.framework;

import java.lang.reflect.Method;

public class CtrlAndMethod {
	
	/*
	 *  target.method
	 *	new BoardController.select();
	 */
	
	private Object target;
	private Method method;
	
	public CtrlAndMethod() {
	}
	
	public CtrlAndMethod(Object target, Method method) {
		this.target = target;
		this.method = method;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public Method getMethod() {
		return method;
	}	
	
}
