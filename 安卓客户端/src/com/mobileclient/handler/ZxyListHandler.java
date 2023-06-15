package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Zxy;
public class ZxyListHandler extends DefaultHandler {
	private List<Zxy> zxyList = null;
	private Zxy zxy;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (zxy != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	zxy.setId(new Integer(valueString).intValue());
            else if ("bid".equals(tempString)) 
            	zxy.setBid(new Integer(valueString).intValue());
            else if ("bname".equals(tempString)) 
            	zxy.setBname(valueString); 
            else if ("btypes".equals(tempString)) 
            	zxy.setBtypes(new Integer(valueString).intValue());
            else if ("jid".equals(tempString)) 
            	zxy.setJid(new Integer(valueString).intValue());
            else if ("jfjd".equals(tempString)) 
            	zxy.setJfjd(valueString); 
            else if ("jdsdate".equals(tempString)) 
            	zxy.setJdsdate(Timestamp.valueOf(valueString));
            else if ("jdedate".equals(tempString)) 
            	zxy.setJdedate(Timestamp.valueOf(valueString));
            else if ("xsfajf".equals(tempString)) 
            	zxy.setXsfajf(new Float(valueString).floatValue());
            else if ("hmzfjf".equals(tempString)) 
            	zxy.setHmzfjf(new Float(valueString).floatValue());
            else if ("cpfkjf".equals(tempString)) 
            	zxy.setCpfkjf(new Float(valueString).floatValue());
            else if ("dwzsjf".equals(tempString)) 
            	zxy.setDwzsjf(new Float(valueString).floatValue());
            else if ("hjf".equals(tempString)) 
            	zxy.setHjf(new Float(valueString).floatValue());
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Zxy".equals(localName)&&zxy!=null){
			zxyList.add(zxy);
			zxy = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		zxyList = new ArrayList<Zxy>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Zxy".equals(localName)) {
            zxy = new Zxy(); 
        }
        tempString = localName; 
	}

	public List<Zxy> getZxyList() {
		return this.zxyList;
	}
}
