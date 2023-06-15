package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Zxynd;
public class ZxyndListHandler extends DefaultHandler {
	private List<Zxynd> zxyndList = null;
	private Zxynd zxynd;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (zxynd != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	zxynd.setId(new Integer(valueString).intValue());
            else if ("bid".equals(tempString)) 
            	zxynd.setBid(new Integer(valueString).intValue());
            else if ("bname".equals(tempString)) 
            	zxynd.setBname(valueString); 
            else if ("btypes".equals(tempString)) 
            	zxynd.setBtypes(new Integer(valueString).intValue());
            else if ("jfjd".equals(tempString)) 
            	zxynd.setJfjd(valueString); 
            else if ("xsfajf".equals(tempString)) 
            	zxynd.setXsfajf(new Float(valueString).floatValue());
            else if ("hmzfjf".equals(tempString)) 
            	zxynd.setHmzfjf(new Float(valueString).floatValue());
            else if ("cpfkjf".equals(tempString)) 
            	zxynd.setCpfkjf(new Float(valueString).floatValue());
            else if ("dwzsjf".equals(tempString)) 
            	zxynd.setDwzsjf(new Float(valueString).floatValue());
            else if ("hjf".equals(tempString)) 
            	zxynd.setHjf(new Float(valueString).floatValue());
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Zxynd".equals(localName)&&zxynd!=null){
			zxyndList.add(zxynd);
			zxynd = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		zxyndList = new ArrayList<Zxynd>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Zxynd".equals(localName)) {
            zxynd = new Zxynd(); 
        }
        tempString = localName; 
	}

	public List<Zxynd> getZxyndList() {
		return this.zxyndList;
	}
}
