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
import com.mobileclient.domain.Zxy;
import com.mobileclient.util.HttpUtil;

/*单位季度积分管理业务逻辑层*/
public class ZxyService {
	
	//查询当前表的所有积分季度信息
		public List<JfjdDropdown> queryAllJfjd() {
			HashMap<String, String> params = new HashMap<String, String>(); 
			params.put("action", "queryAllJfjd");
			List<JfjdDropdown> jfjdList = new ArrayList<JfjdDropdown>();
			byte[] resultByte;
			try {
				resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
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
				resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
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
		
	/* 添加单位季度积分 */
	public String AddZxy(Zxy zxy) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxy.getId() + "");
		params.put("bid", zxy.getBid() + "");
		params.put("bname", zxy.getBname());
		params.put("btypes", zxy.getBtypes() + "");
		params.put("jid", zxy.getJid() + "");
		params.put("jfjd", zxy.getJfjd());
		params.put("jdsdate", zxy.getJdsdate().toString());
		params.put("jdedate", zxy.getJdedate().toString());
		params.put("xsfajf", zxy.getXsfajf() + "");
		params.put("hmzfjf", zxy.getHmzfjf() + "");
		params.put("cpfkjf", zxy.getCpfkjf() + "");
		params.put("dwzsjf", zxy.getDwzsjf() + "");
		params.put("hjf", zxy.getHjf() + "");
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 查询单位季度积分 */
	public List<Zxy> QueryZxy(Zxy queryConditionZxy) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxyServlet?action=query";
		if(queryConditionZxy != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxy.getBname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxy.getJfjd(), "UTF-8") + "";
		}

		/* 2种数据解析方法，第一种是用SAXParser解析xml文件格式
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		ZxyListHandler zxyListHander = new ZxyListHandler();
		xr.setContentHandler(zxyListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Zxy> zxyList = zxyListHander.getZxyList();
		return zxyList;*/
		//第2种是基于json数据格式解析，我们采用的是第2种
		List<Zxy> zxyList = new ArrayList<Zxy>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxy zxy = new Zxy();
				zxy.setId(object.getInt("id"));
				zxy.setBid(object.getInt("bid"));
				zxy.setBname(object.getString("bname"));
				zxy.setBtypes(object.getInt("btypes"));
				//zxy.setJid(object.getInt("jid"));
				zxy.setJidString(object.getString("jid"));
				zxy.setJfjd(object.getString("jfjd"));
				zxy.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxy.setJdedate(Timestamp.valueOf(object.getString("jdedate")));
				zxy.setXsfajf((float) object.getDouble("xsfajf"));
				zxy.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxy.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxy.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxy.setHjf((float) object.getDouble("hjf"));
				zxyList.add(zxy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zxyList;
	}

	/* 更新单位季度积分 */
	public String UpdateZxy(Zxy zxy) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxy.getId() + "");
		params.put("bid", zxy.getBid() + "");
		params.put("bname", zxy.getBname());
		params.put("btypes", zxy.getBtypes() + "");
		params.put("jid", zxy.getJid() + "");
		params.put("jfjd", zxy.getJfjd());
		params.put("jdsdate", zxy.getJdsdate().toString());
		params.put("jdedate", zxy.getJdedate().toString());
		params.put("xsfajf", zxy.getXsfajf() + "");
		params.put("hmzfjf", zxy.getHmzfjf() + "");
		params.put("cpfkjf", zxy.getCpfkjf() + "");
		params.put("dwzsjf", zxy.getDwzsjf() + "");
		params.put("hjf", zxy.getHjf() + "");
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 删除单位季度积分 */
	public String DeleteZxy(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "单位季度积分信息删除失败!";
		}
	}

	/* 根据id获取单位季度积分对象 */
	public Zxy GetZxy(int id)  {
		List<Zxy> zxyList = new ArrayList<Zxy>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxy zxy = new Zxy();
				zxy.setId(object.getInt("id"));
				zxy.setBid(object.getInt("bid"));
				zxy.setBname(object.getString("bname"));
				zxy.setBtypes(object.getInt("btypes"));
				zxy.setJid(object.getInt("jid"));
				zxy.setJfjd(object.getString("jfjd"));
				zxy.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxy.setJdedate(Timestamp.valueOf(object.getString("jdedate")));
				zxy.setXsfajf((float) object.getDouble("xsfajf"));
				zxy.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxy.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxy.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxy.setHjf((float) object.getDouble("hjf"));
				zxyList.add(zxy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = zxyList.size();
		if(size>0) return zxyList.get(0); 
		else return null; 
	}
}
