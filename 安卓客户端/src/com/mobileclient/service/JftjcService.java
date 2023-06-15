package com.mobileclient.service;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mobileclient.domain.Jftjc;
import com.mobileclient.util.HttpUtil;

/*������������ҵ���߼���*/
public class JftjcService {
	/* ��ӻ������� */
	public String AddJftjc(Jftjc jftjc) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", jftjc.getId() + "");
		params.put("jftj", jftjc.getJftj());
		params.put("fs", jftjc.getFs() + "");
		params.put("typeid", jftjc.getTypeid() + "");
		params.put("mtypeid", jftjc.getMtypeid() + "");
		params.put("bz", jftjc.getBz());
		params.put("action", "add");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "JftjcServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ��ѯ�������� */
	public List<Jftjc> QueryJftjc(Jftjc queryConditionJftjc) throws Exception {
		String urlString = HttpUtil.BASE_URL + "JftjcServlet?action=query";
		if(queryConditionJftjc != null) {
			urlString += "&jftj=" + URLEncoder.encode(queryConditionJftjc.getJftj(), "UTF-8") + "";
		}

		/* 2�����ݽ�����������һ������SAXParser����xml�ļ���ʽ
		URL url = new URL(urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();

		JftjcListHandler jftjcListHander = new JftjcListHandler();
		xr.setContentHandler(jftjcListHander);
		InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
		InputSource is = new InputSource(isr);
		xr.parse(is);
		List<Jftjc> jftjcList = jftjcListHander.getJftjcList();
		return jftjcList;*/
		//��2���ǻ���json���ݸ�ʽ���������ǲ��õ��ǵ�2��
		List<Jftjc> jftjcList = new ArrayList<Jftjc>();
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(urlString, null, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Jftjc jftjc = new Jftjc();
				jftjc.setId(object.getInt("id"));
				jftjc.setJftj(object.getString("jftj"));
				jftjc.setFs((float) object.getDouble("fs"));
				jftjc.setTypeid(object.getInt("typeid"));
				jftjc.setMtypeid(object.getInt("mtypeid"));
				jftjc.setBz(object.getString("bz"));
				jftjcList.add(jftjc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jftjcList;
	}

	/* ���»������� */
	public String UpdateJftjc(Jftjc jftjc) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", jftjc.getId() + "");
		params.put("jftj", jftjc.getJftj());
		params.put("fs", jftjc.getFs() + "");
		params.put("typeid", jftjc.getTypeid() + "");
		params.put("mtypeid", jftjc.getMtypeid() + "");
		params.put("bz", jftjc.getBz());
		params.put("action", "update");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "JftjcServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/* ɾ���������� */
	public String DeleteJftjc(int id) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "delete");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "JftjcServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "����������Ϣɾ��ʧ��!";
		}
	}

	/* ���ݻ���id��ȡ������������ */
	public Jftjc GetJftjc(int id)  {
		List<Jftjc> jftjcList = new ArrayList<Jftjc>();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", id + "");
		params.put("action", "updateQuery");
		byte[] resultByte;
		try {
			resultByte = HttpUtil.SendPostRequest(HttpUtil.BASE_URL + "JftjcServlet?", params, "UTF-8");
			String result = new String(resultByte, "UTF-8");
			JSONArray array = new JSONArray(result);
			int length = array.length();
			for (int i = 0; i < length; i++) {
				JSONObject object = array.getJSONObject(i);
				Jftjc jftjc = new Jftjc();
				jftjc.setId(object.getInt("id"));
				jftjc.setJftj(object.getString("jftj"));
				jftjc.setFs((float) object.getDouble("fs"));
				jftjc.setTypeid(object.getInt("typeid"));
				jftjc.setMtypeid(object.getInt("mtypeid"));
				jftjc.setBz(object.getString("bz"));
				jftjcList.add(jftjc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int size = jftjcList.size();
		if(size>0) return jftjcList.get(0); 
		else return null; 
	}
}
