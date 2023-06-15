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
import com.mobileclient.domain.Zxymj;
import com.mobileclient.util.HttpUtil;

/*警员季度积分管理业务逻辑层*/
public class ZxymjService {
	
	//查询当前表的所有积分季度信息
			public List<JfjdDropdown> queryAllJfjd() {
				HashMap<String, String> params = new HashMap<String, String>(); 
				params.put("action", "queryAllJfjd");
				List<JfjdDropdown> jfjdList = new ArrayList<JfjdDropdown>();
				byte[] resultByte;
				try {
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
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
					resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
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
			
			
	/* 添加警员季度积分 */
	public String AddZxymj(Zxymj zxymj) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxymj.getId() + "");
		params.put("bid", zxymj.getBid() + "");
		params.put("bname", zxymj.getBname());
		params.put("sid", zxymj.getSid() + "");
		params.put("sname", zxymj.getSname());
		params.put("btypes", zxymj.getBtypes() + "");
		params.put("jid", zxymj.getJid() + "");
		params.put("jfjd", zxymj.getJfjd());
		params.put("jdsdate", zxymj.getJdsdate().toString());
		params.put("jdedate", zxymj.getJdedate().toString());
		params.put("xsfajf", zxymj.getXsfajf() + "");
		params.put("hmzfjf", zxymj.getHmzfjf() + "");
		params.put("cpfkjf", zxymj.getCpfkjf() + "");
		params.put("dwzsjf", zxymj.getDwzsjf() + "");
		params.put("hjf", zxymj.getHjf() + "");
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 查询警员季度积分 */
	public List<Zxymj> QueryZxymj(Zxymj queryConditionZxymj) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxymjServlet?action=query";
		if(queryConditionZxymj != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxymj.getBname(), "UTF-8") + "";
			urlString += "&sname=" + URLEncoder.encode(queryConditionZxymj.getSname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxymj.getJfjd(), "UTF-8") + "";
		}

		/* 2种数据解析方法，第一种是用SAXParser解析xml文件格式
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		ZxymjListHandler zxymjListHander = new ZxymjListHandler();
		xr.setContentHandler(zxymjListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Zxymj> zxymjList = zxymjListHander.getZxymjList();
		return zxymjList;*/
		//第2种是基于json数据格式解析，我们采用的是第2种
		List<Zxymj> zxymjList = new ArrayList<Zxymj>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxymj zxymj = new Zxymj();
				zxymj.setId(object.getInt("id"));
				zxymj.setBid(object.getInt("bid"));
				zxymj.setBname(object.getString("bname"));
				zxymj.setSid(object.getInt("sid"));
				zxymj.setSname(object.getString("sname"));
				zxymj.setBtypes(object.getInt("btypes"));
				zxymj.setJid(object.getInt("jid"));
				zxymj.setJfjd(object.getString("jfjd"));
				zxymj.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxymj.setJdedate(Timestamp.valueOf(object.getString("jdedate")));
				zxymj.setXsfajf((float) object.getDouble("xsfajf"));
				zxymj.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxymj.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxymj.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxymj.setHjf((float) object.getDouble("hjf"));
				zxymjList.add(zxymj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zxymjList;
	}

	/* 更新警员季度积分 */
	public String UpdateZxymj(Zxymj zxymj) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxymj.getId() + "");
		params.put("bid", zxymj.getBid() + "");
		params.put("bname", zxymj.getBname());
		params.put("sid", zxymj.getSid() + "");
		params.put("sname", zxymj.getSname());
		params.put("btypes", zxymj.getBtypes() + "");
		params.put("jid", zxymj.getJid() + "");
		params.put("jfjd", zxymj.getJfjd());
		params.put("jdsdate", zxymj.getJdsdate().toString());
		params.put("jdedate", zxymj.getJdedate().toString());
		params.put("xsfajf", zxymj.getXsfajf() + "");
		params.put("hmzfjf", zxymj.getHmzfjf() + "");
		params.put("cpfkjf", zxymj.getCpfkjf() + "");
		params.put("dwzsjf", zxymj.getDwzsjf() + "");
		params.put("hjf", zxymj.getHjf() + "");
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 删除警员季度积分 */
	public String DeleteZxymj(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "警员季度积分信息删除失败!";
		}
	}

	/* 根据id获取警员季度积分对象 */
	public Zxymj GetZxymj(int id)  {
		List<Zxymj> zxymjList = new ArrayList<Zxymj>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxymjServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxymj zxymj = new Zxymj();
				zxymj.setId(object.getInt("id"));
				zxymj.setBid(object.getInt("bid"));
				zxymj.setBname(object.getString("bname"));
				zxymj.setSid(object.getInt("sid"));
				zxymj.setSname(object.getString("sname"));
				zxymj.setBtypes(object.getInt("btypes"));
				zxymj.setJid(object.getInt("jid"));
				zxymj.setJfjd(object.getString("jfjd"));
				zxymj.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxymj.setJdedate(Timestamp.valueOf(object.getString("jdedate")));
				zxymj.setXsfajf((float) object.getDouble("xsfajf"));
				zxymj.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxymj.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxymj.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxymj.setHjf((float) object.getDouble("hjf"));
				zxymjList.add(zxymj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = zxymjList.size();
		if(size>0) return zxymjList.get(0); 
		else return null; 
	}
}
