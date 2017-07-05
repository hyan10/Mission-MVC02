package kr.co.bit.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import kr.co.bit.framework.annotation.RequestMapping;

public class HandlerMapping {

	private Map<String, CtrlAndMethod> mappings = null;

	public HandlerMapping(String ctrlNames) throws Exception {
		
		mappings = new HashMap<>();
			
		String[] ctrls = ctrlNames.split("\\|");
		
		for(String ctrl : ctrls){
		//	System.out.println(ctrl.trim());
			Class<?> cl = Class.forName(ctrl.trim());
			Object target = cl.newInstance();
			// System.out.println(target);
			
			// 클래스에 포함된 public 메서드 모두 가져옴
	//		Method[] methods = cl.getMethods();
			
			// 클래스에 사용자정의한 메서드만 나옴
			Method[] methods = cl.getDeclaredMethods();
			
			for(Method method : methods){
				RequestMapping reqAnno = method.getAnnotation(RequestMapping.class);
			//	System.out.println(reqAnno);
				
				if(reqAnno != null){
					String uri = reqAnno.value();
			//		System.out.println(method);
			//		System.out.println("uri: "+uri);
					
					CtrlAndMethod cam = new CtrlAndMethod(target, method);
					mappings.put(uri,cam);
				}
			}
		}
	}

	public CtrlAndMethod getCtrlAndMethod(String uri){
		return mappings.get(uri);
	}
}
