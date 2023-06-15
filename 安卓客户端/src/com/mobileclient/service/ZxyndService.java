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

/*��λ��Ȼ��ֹ���ҵ���߼���*/
public class ZxyndService {
	
	//��ѯ��ǰ������л��ּ�����Ϣ
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
			
			//��ѯ��ǰ������в�����Ϣ
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
	/* ��ӵ�λ��Ȼ��� */
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

	/* ��ѯ��λ��Ȼ��� */
	public List<Zxynd> QueryZxynd(Zxynd queryConditionZxynd) throws Exception {
		String urlString = HttpUtil.BASE_URL + "ZxyndServlet?action=query";
		if(queryConditionZxynd != null) {
			urlString += "&bname=" + URLEncoder.encode(queryConditionZxynd.getBname(), "UTF-8") + "";
			urlString += "&jfjd=" + URLEncoder.encode(queryConditionZxynd.getJfjd(), "UTF-8") + "";
		}

		/* 2�����ݽ�����������һ������SAXParser����xml�ļ���ʽ
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
		//��2���ǻ���json���ݸ�ʽ���������ǲ��õ��ǵ�2��
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

	/* ���µ�λ��Ȼ��� */
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

	/* ɾ����λ��Ȼ��� */
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
			return "��λ��Ȼ�����Ϣɾ��ʧ��!";
		}
	}

	/* ����id��ȡ��λ��Ȼ��ֶ��� */
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
