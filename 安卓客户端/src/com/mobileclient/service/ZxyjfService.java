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
import com.mobileclient.domain.Zxyjf;
import com.mobileclient.util.HttpUtil;

/*单位民警积分管理业务逻辑层*/
public class ZxyjfService {
	
	//查询当前表的所有积分季度信息
	public List<JfjdDropdown> queryAllJfjd() {
		HashMap<String, String> params = new HashMap<String, String>(); 
		params.put("action", "queryAllJfjd");
		List<JfjdDropdown> jfjdList = new ArrayList<JfjdDropdown>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
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
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
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
	
	/* 添加单位民警积分 */
	public String AddZxyjf(Zxyjf zxyjf) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxyjf.getId() + "");
		params.put("bid", zxyjf.getBid() + "");
		params.put("bname", zxyjf.getBname());
		params.put("btypes", zxyjf.getBtypes() + "");
		params.put("sid", zxyjf.getSid());
		params.put("sname", zxyjf.getSname());
		params.put("jid", zxyjf.getJid() + "");
		params.put("jftj", zxyjf.getJftj());
		params.put("jfjd", zxyjf.getJfjd());
		params.put("jfdate", zxyjf.getJfdate().toString());
		params.put("jdsdate", zxyjf.getJdsdate().toString());
		params.put("fs", zxyjf.getFs() + "");
		params.put("sl", zxyjf.getSl() + "");
		params.put("xsfajf", zxyjf.getXsfajf() + "");
		params.put("hmzfjf", zxyjf.getHmzfjf() + "");
		params.put("cpfkjf", zxyjf.getCpfkjf() + "");
		params.put("dwzsjf", zxyjf.getDwzsjf() + "");
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 查询单位民警积分 */
	public List<Zxyjf> QueryZxyjf(Zxyjf queryConditionZxyjf) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxyjfServlet?action=query";
		if(queryConditionZxyjf != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxyjf.getBname(), "UTF-8") + "";
			urlString += "&sname=" + URLEncoder.encode(queryConditionZxyjf.getSname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxyjf.getJfjd(), "UTF-8") + "";
			if(queryConditionZxyjf.getJfdate() != null) {
				urlString += "&jfdate=" + URLEncoder.encode(queryConditionZxyjf.getJfdate().toString(), "UTF-8");
			}
		}

		/* 2种数据解析方法，第一种是用SAXParser解析xml文件格式
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		ZxyjfListHandler zxyjfListHander = new ZxyjfListHandler();
		xr.setContentHandler(zxyjfListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Zxyjf> zxyjfList = zxyjfListHander.getZxyjfList();
		return zxyjfList;*/
		//第2种是基于json数据格式解析，我们采用的是第2种
		List<Zxyjf> zxyjfList = new ArrayList<Zxyjf>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxyjf zxyjf = new Zxyjf();
				zxyjf.setId(object.getInt("id"));
				zxyjf.setBid(object.getInt("bid"));
				zxyjf.setBname(object.getString("bname"));
				zxyjf.setBtypes(object.getInt("btypes"));
				zxyjf.setSid(object.getString("sid"));
				zxyjf.setSname(object.getString("sname"));
				zxyjf.setJid(object.getInt("jid"));
				zxyjf.setJftj(object.getString("jftj"));
				zxyjf.setJfjd(object.getString("jfjd"));
				zxyjf.setJfdate(Timestamp.valueOf(object.getString("jfdate")));
				zxyjf.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxyjf.setFs((float) object.getDouble("fs"));
				zxyjf.setSl(object.getInt("sl"));
				zxyjf.setXsfajf((float) object.getDouble("xsfajf"));
				zxyjf.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxyjf.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxyjf.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxyjfList.add(zxyjf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zxyjfList;
	}

	/* 更新单位民警积分 */
	public String UpdateZxyjf(Zxyjf zxyjf) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", zxyjf.getId() + "");
		params.put("bid", zxyjf.getBid() + "");
		params.put("bname", zxyjf.getBname());
		params.put("btypes", zxyjf.getBtypes() + "");
		params.put("sid", zxyjf.getSid());
		params.put("sname", zxyjf.getSname());
		params.put("jid", zxyjf.getJid() + "");
		params.put("jftj", zxyjf.getJftj());
		params.put("jfjd", zxyjf.getJfjd());
		params.put("jfdate", zxyjf.getJfdate().toString());
		params.put("jdsdate", zxyjf.getJdsdate().toString());
		params.put("fs", zxyjf.getFs() + "");
		params.put("sl", zxyjf.getSl() + "");
		params.put("xsfajf", zxyjf.getXsfajf() + "");
		params.put("hmzfjf", zxyjf.getHmzfjf() + "");
		params.put("cpfkjf", zxyjf.getCpfkjf() + "");
		params.put("dwzsjf", zxyjf.getDwzsjf() + "");
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* 删除单位民警积分 */
	public String DeleteZxyjf(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "单位民警积分信息删除失败!";
		}
	}

	/* 根据id获取单位民警积分对象 */
	public Zxyjf GetZxyjf(int id)  {
		List<Zxyjf> zxyjfList = new ArrayList<Zxyjf>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "ZxyjfServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Zxyjf zxyjf = new Zxyjf();
				zxyjf.setId(object.getInt("id"));
				zxyjf.setBid(object.getInt("bid"));
				zxyjf.setBname(object.getString("bname"));
				zxyjf.setBtypes(object.getInt("btypes"));
				zxyjf.setSid(object.getString("sid"));
				zxyjf.setSname(object.getString("sname"));
				zxyjf.setJid(object.getInt("jid"));
				zxyjf.setJftj(object.getString("jftj"));
				zxyjf.setJfjd(object.getString("jfjd"));
				zxyjf.setJfdate(Timestamp.valueOf(object.getString("jfdate")));
				zxyjf.setJdsdate(Timestamp.valueOf(object.getString("jdsdate")));
				zxyjf.setFs((float) object.getDouble("fs"));
				zxyjf.setSl(object.getInt("sl"));
				zxyjf.setXsfajf((float) object.getDouble("xsfajf"));
				zxyjf.setHmzfjf((float) object.getDouble("hmzfjf"));
				zxyjf.setCpfkjf((float) object.getDouble("cpfkjf"));
				zxyjf.setDwzsjf((float) object.getDouble("dwzsjf"));
				zxyjfList.add(zxyjf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = zxyjfList.size();
		if(size>0) return zxyjfList.get(0); 
		else return null; 
	}

	
}
