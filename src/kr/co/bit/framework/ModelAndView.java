package kr.co.bit.framework;

import java.util.HashMap;
import java.util.Map;

/*
 *  Model: 화면단(JSP)에서 사용할 데이터 저장 객체 (request 공유영역에 등록시킬 객체)
 *  View: 응답화면 페이지(JSP) 정보
 */
public class ModelAndView {
	private String view;
	private Map<String, Object> model;

	public ModelAndView() {
		model = new HashMap<>();
	}

	public ModelAndView(String view) {
		this();
		this.view = view;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	public void addAttribute(String key, Object value) {
		model.put(key, value);
	}

}
