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

/*��Ա��Ȼ��ֹ���ҵ���߼���*/
public class ZxymjndService {
	
	//��ѯ��ǰ������л��ּ�����Ϣ
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
			
			//��ѯ��ǰ������в�����Ϣ
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
			
			
	/* ��Ӿ�Ա��Ȼ��� */
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

	/* ��ѯ��Ա��Ȼ��� */
	public List<Zxymjnd> QueryZxymjnd(Zxymjnd queryConditionZxymjnd) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxymjndServlet?action=query";
		if(queryConditionZxymjnd != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxymjnd.getBname(), "UTF-8") + "";
			urlString += "&sname=" + URLEncoder.encode(queryConditionZxymjnd.getSname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxymjnd.getJfjd(), "UTF-8") + "";
		}

		/* 2�����ݽ�����������һ������SAXParser����xml�ļ���ʽ
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
		//��2���ǻ���json���ݸ�ʽ���������ǲ��õ��ǵ�2��
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

	/* ���¾�Ա��Ȼ��� */
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

	/* ɾ����Ա��Ȼ��� */
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
			return "��Ա��Ȼ�����Ϣɾ��ʧ��!";
		}
	}

	/* ����id��ȡ��Ա��Ȼ��ֶ��� */
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
