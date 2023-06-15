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
import com.mobileclient.domain.Zxymjnd;
import com.mobileclient.util.HttpUtil;

/*警员年度积分管理业务逻辑层*/
public class ZxymjndService {
	
	//查询当前表的所有积分季度信息
			public List<JfjdDropdown> queryAllJfjd() {
				HashMap<String, String> params = new HashMap<String, String>(); 
				params.put("action", "queryAllJfjd");
				List<JfjdDropdown> jfjdList = new ArrayList<JfjdDropdown>();
				byte[] resultByte;
				try {
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
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
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
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
			
			
	/* 添加警员年度积分 */
	public String AddZxymjnd(Zxymjnd zxymjnd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxymjnd.getId() + "");
		params.put("bid", zxymjnd.getBid() + "");
		params.put("bname", zxymjnd.getBname());
		params.put("sid", zxymjnd.getSid() + "");
		params.put("sname", zxymjnd.getSname());
		params.put("btypes", zxymjnd.getBtypes() + "");
		params.put("jfjd", zxymjnd.getJfjd());
		params.put("xsfajf", zxymjnd.getXsfajf() + "");
		params.put("hmzfjf", zxymjnd.getHmzfjf() + "");
		params.put("cpfkjf", zxymjnd.getCpfkjf() + "");
		params.put("dwzsjf", zxymjnd.getDwzsjf() + "");
		params.put("hjf", zxymjnd.getHjf() + "");
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 查询警员年度积分 */
	public List<Zxymjnd> QueryZxymjnd(Zxymjnd queryConditionZxymjnd) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxymjndServlet?action=query";
		if(queryConditionZxymjnd != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxymjnd.getBname(), "UTF-8") + "";
			urlString += "&sname=" + URLEncoder.encode(queryConditionZxymjnd.getSname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxymjnd.getJfjd(), "UTF-8") + "";
		}

		/* 2种数据解析方法，第一种是用SAXParser解析xml文件格式
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		ZxymjndListHandler zxymjndListHander = new ZxymjndListHandler();
		xr.setContentHandler(zxymjndListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Zxymjnd> zxymjndList = zxymjndListHander.getZxymjndList();
		return zxymjndList;*/
		//第2种是基于json数据格式解析，我们采用的是第2种
		List<Zxymjnd> zxymjndList = new ArrayList<Zxymjnd>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxymjnd zxymjnd = new Zxymjnd();
				zxymjnd.setId(object.getInt("id"));
				zxymjnd.setBid(object.getInt("bid"));
				zxymjnd.setBname(object.getString("bname"));
				zxymjnd.setSid(object.getInt("sid"));
				zxymjnd.setSname(object.getString("sname"));
				zxymjnd.setBtypes(object.getInt("btypes"));
				zxymjnd.setJfjd(object.getString("jfjd"));
				zxymjnd.setXsfajf((float) object.getDouble("xsfajf"));
				zxymjnd.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxymjnd.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxymjnd.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxymjnd.setHjf((float) object.getDouble("hjf"));
				zxymjndList.add(zxymjnd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zxymjndList;
	}

	/* 更新警员年度积分 */
	public String UpdateZxymjnd(Zxymjnd zxymjnd) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxymjnd.getId() + "");
		params.put("bid", zxymjnd.getBid() + "");
		params.put("bname", zxymjnd.getBname());
		params.put("sid", zxymjnd.getSid() + "");
		params.put("sname", zxymjnd.getSname());
		params.put("btypes", zxymjnd.getBtypes() + "");
		params.put("jfjd", zxymjnd.getJfjd());
		params.put("xsfajf", zxymjnd.getXsfajf() + "");
		params.put("hmzfjf", zxymjnd.getHmzfjf() + "");
		params.put("cpfkjf", zxymjnd.getCpfkjf() + "");
		params.put("dwzsjf", zxymjnd.getDwzsjf() + "");
		params.put("hjf", zxymjnd.getHjf() + "");
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 删除警员年度积分 */
	public String DeleteZxymjnd(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "警员年度积分信息删除失败!";
		}
	}

	/* 根据id获取警员年度积分对象 */
	public Zxymjnd GetZxymjnd(int id)  {
		List<Zxymjnd> zxymjndList = new ArrayList<Zxymjnd>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjndServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxymjnd zxymjnd = new Zxymjnd();
				zxymjnd.setId(object.getInt("id"));
				zxymjnd.setBid(object.getInt("bid"));
				zxymjnd.setBname(object.getString("bname"));
				zxymjnd.setSid(object.getInt("sid"));
				zxymjnd.setSname(object.getString("sname"));
				zxymjnd.setBtypes(object.getInt("btypes"));
				zxymjnd.setJfjd(object.getString("jfjd"));
				zxymjnd.setXsfajf((float) object.getDouble("xsfajf"));
				zxymjnd.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxymjnd.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxymjnd.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxymjnd.setHjf((float) object.getDouble("hjf"));
				zxymjndList.add(zxymjnd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = zxymjndList.size();
		if(size>0) return zxymjndList.get(0); 
		else return null; 
	}
}
