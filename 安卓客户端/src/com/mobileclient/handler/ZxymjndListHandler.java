package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Zxymjnd;
public class ZxymjndListHandler extends DefaultHandler {
	private List<Zxymjnd> zxymjndList = null;
	private Zxymjnd zxymjnd;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (zxymjnd != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	zxymjnd.setId(new Integer(valueString).intValue());
            else if ("bid".equals(tempString)) 
            	zxymjnd.setBid(new Integer(valueString).intValue());
            else if ("bname".equals(tempString)) 
            	zxymjnd.setBname(valueString); 
            else if ("sid".equals(tempString)) 
            	zxymjnd.setSid(new Integer(valueString).intValue());
            else if ("sname".equals(tempString)) 
            	zxymjnd.setSname(valueString); 
            else if ("btypes".equals(tempString)) 
            	zxymjnd.setBtypes(new Integer(valueString).intValue());
            else if ("jfjd".equals(tempString)) 
            	zxymjnd.setJfjd(valueString); 
            else if ("xsfajf".equals(tempString)) 
            	zxymjnd.setXsfajf(new Float(valueString).floatValue());
            else if ("hmzfjf".equals(tempString)) 
            	zxymjnd.setHmzfjf(new Float(valueString).floatValue());
            else if ("cpfkjf".equals(tempString)) 
            	zxymjnd.setCpfkjf(new Float(valueString).floatValue());
            else if ("dwzsjf".equals(tempString)) 
            	zxymjnd.setDwzsjf(new Float(valueString).floatValue());
            else if ("hjf".equals(tempString)) 
            	zxymjnd.setHjf(new Float(valueString).floatValue());
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Zxymjnd".equals(localName)&&zxymjnd!=null){
			zxymjndList.add(zxymjnd);
			zxymjnd = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		zxymjndList = new ArrayList<Zxymjnd>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Zxymjnd".equals(localName)) {
            zxymjnd = new Zxymjnd(); 
        }
        tempString = localName; 
	}

	public List<Zxymjnd> getZxymjndList() {
		return this.zxymjndList;
	}
}
