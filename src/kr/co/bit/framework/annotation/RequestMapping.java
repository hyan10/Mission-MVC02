package kr.co.bit.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Ÿ�� ������ �ϱ� - �޼���, Ŭ������ Annotation�� ���� �� �ִ�.
// @Target({ElementType.METHOD, ElementType.TYPE})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	String value();
//	String name();
}
