package com.ruijie.rush.util;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IRequestControl {

	public String stringRequest(String url);

	public String stringRequest(String url, int method);
	
	public JSONObject jsonObjectRequest(String url);
	
	public JSONArray jsonArrayRequest(String url);
	
}
