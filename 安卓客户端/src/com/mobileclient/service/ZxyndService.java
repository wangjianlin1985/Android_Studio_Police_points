package com.mobileclient.service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobileclient.domain.BnameDropdown;
import com.mobileclient.domain.JfjdDropdown;
import com.mobileclient.domain.Zxynd;
import com.mobileclient.util.HttpUtil;

/*单位年度积分管理业务逻辑层*/
public class ZxyndService {
	
	//查询当前表的所有积分季度信息
			public List<JfjdDropdown> queryAllJfjd() {
				HashMap<String, String> params = new HashMap<String, String>(); 
				params.put("action", "queryAllJfjd");
				List<JfjdDropdown> jfjdList = new ArrayList<JfjdDropdown>();
				byte[] resultByte;
				try {
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
					String result = new String(resultByte, "UTF-8");
					JSONArray array = new JSONArray(result);
					int length = array.length();
					for (int i = 0; i < length; i++) {
						JSONObject object = array.getJSONObject(i);
						JfjdDropdown jfjdDropdown = new JfjdDropdown();
						jfjdDropdown.setText(object.getString("text"));
						jfjdDropdown.setValue(object.getString("value")); 
						jfjdList.add(jfjdDropdown);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return jfjdList;
			}
			
			//查询当前表的所有部门信息
			public List<BnameDropdown> queryAllBname() {
				HashMap<String, String> params = new HashMap<String, String>(); 
				params.put("action", "queryAllBname");
				List<BnameDropdown> bnameList = new ArrayList<BnameDropdown>();
				byte[] resultByte;
				try {
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
					String result = new String(resultByte, "UTF-8");
					JSONArray array = new JSONArray(result);
					int length = array.length();
					for (int i = 0; i < length; i++) {
						JSONObject object = array.getJSONObject(i);
						BnameDropdown bnameDropdown = new BnameDropdown();
						bnameDropdown.setText(object.getString("text"));
						bnameDropdown.setValue(object.getString("value")); 
						bnameList.add(bnameDropdown);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return bnameList;
			}
	/* 添加单位年度积分 */
	public String AddZxynd(Zxynd zxynd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxynd.getId() + "");
		params.put("bid", zxynd.getBid() + "");
		params.put("bname", zxynd.getBname());
		params.put("btypes", zxynd.getBtypes() + "");
		params.put("jfjd", zxynd.getJfjd());
		params.put("xsfajf", zxynd.getXsfajf() + "");
		params.put("hmzfjf", zxynd.getHmzfjf() + "");
		params.put("cpfkjf", zxynd.getCpfkjf() + "");
		params.put("dwzsjf", zxynd.getDwzsjf() + "");
		params.put("hjf", zxynd.getHjf() + "");
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 查询单位年度积分 */
	public List<Zxynd> QueryZxynd(Zxynd queryConditionZxynd) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxyndServlet?action=query";
		if(queryConditionZxynd != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxynd.getBname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxynd.getJfjd(), "UTF-8") + "";
		}

		/* 2种数据解析方法，第一种是用SAXParser解析xml文件格式
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		ZxyndListHandler zxyndListHander = new ZxyndListHandler();
		xr.setContentHandler(zxyndListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Zxynd> zxyndList = zxyndListHander.getZxyndList();
		return zxyndList;*/
		//第2种是基于json数据格式解析，我们采用的是第2种
		List<Zxynd> zxyndList = new ArrayList<Zxynd>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxynd zxynd = new Zxynd();
				zxynd.setId(object.getInt("id"));
				zxynd.setBid(object.getInt("bid"));
				zxynd.setBname(object.getString("bname"));
				zxynd.setBtypes(object.getInt("btypes"));
				zxynd.setJfjd(object.getString("jfjd"));
				zxynd.setXsfajf((float) object.getDouble("xsfajf"));
				zxynd.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxynd.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxynd.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxynd.setHjf((float) object.getDouble("hjf"));
				zxyndList.add(zxynd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zxyndList;
	}

	/* 更新单位年度积分 */
	public String UpdateZxynd(Zxynd zxynd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxynd.getId() + "");
		params.put("bid", zxynd.getBid() + "");
		params.put("bname", zxynd.getBname());
		params.put("btypes", zxynd.getBtypes() + "");
		params.put("jfjd", zxynd.getJfjd());
		params.put("xsfajf", zxynd.getXsfajf() + "");
		params.put("hmzfjf", zxynd.getHmzfjf() + "");
		params.put("cpfkjf", zxynd.getCpfkjf() + "");
		params.put("dwzsjf", zxynd.getDwzsjf() + "");
		params.put("hjf", zxynd.getHjf() + "");
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 删除单位年度积分 */
	public String DeleteZxynd(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "单位年度积分信息删除失败!";
		}
	}

	/* 根据id获取单位年度积分对象 */
	public Zxynd GetZxynd(int id)  {
		List<Zxynd> zxyndList = new ArrayList<Zxynd>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxynd zxynd = new Zxynd();
				zxynd.setId(object.getInt("id"));
				zxynd.setBid(object.getInt("bid"));
				zxynd.setBname(object.getString("bname"));
				zxynd.setBtypes(object.getInt("btypes"));
				zxynd.setJfjd(object.getString("jfjd"));
				zxynd.setXsfajf((float) object.getDouble("xsfajf"));
				zxynd.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxynd.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxynd.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxynd.setHjf((float) object.getDouble("hjf"));
				zxyndList.add(zxynd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = zxyndList.size();
		if(size>0) return zxyndList.get(0); 
		else return null; 
	}
}
