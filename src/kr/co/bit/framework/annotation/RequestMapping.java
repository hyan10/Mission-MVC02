package kr.co.bit.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 타겟 여러개 하기 - 메서드, 클래스에 Annotation을 붙일 수 있다.
// @Target({ElementType.METHOD, ElementType.TYPE})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value();
//	String name();
}
