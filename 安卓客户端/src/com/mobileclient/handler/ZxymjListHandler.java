package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Zxymj;
public class ZxymjListHandler extends DefaultHandler {
	private List<Zxymj> zxymjList = null;
	private Zxymj zxymj;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (zxymj != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	zxymj.setId(new Integer(valueString).intValue());
            else if ("bid".equals(tempString)) 
            	zxymj.setBid(new Integer(valueString).intValue());
            else if ("bname".equals(tempString)) 
            	zxymj.setBname(valueString); 
            else if ("sid".equals(tempString)) 
            	zxymj.setSid(new Integer(valueString).intValue());
            else if ("sname".equals(tempString)) 
            	zxymj.setSname(valueString); 
            else if ("btypes".equals(tempString)) 
            	zxymj.setBtypes(new Integer(valueString).intValue());
            else if ("jid".equals(tempString)) 
            	zxymj.setJid(new Integer(valueString).intValue());
            else if ("jfjd".equals(tempString)) 
            	zxymj.setJfjd(valueString); 
            else if ("jdsdate".equals(tempString)) 
            	zxymj.setJdsdate(Timestamp.valueOf(valueString));
            else if ("jdedate".equals(tempString)) 
            	zxymj.setJdedate(Timestamp.valueOf(valueString));
            else if ("xsfajf".equals(tempString)) 
            	zxymj.setXsfajf(new Float(valueString).floatValue());
            else if ("hmzfjf".equals(tempString)) 
            	zxymj.setHmzfjf(new Float(valueString).floatValue());
            else if ("cpfkjf".equals(tempString)) 
            	zxymj.setCpfkjf(new Float(valueString).floatValue());
            else if ("dwzsjf".equals(tempString)) 
            	zxymj.setDwzsjf(new Float(valueString).floatValue());
            else if ("hjf".equals(tempString)) 
            	zxymj.setHjf(new Float(valueString).floatValue());
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Zxymj".equals(localName)&&zxymj!=null){
			zxymjList.add(zxymj);
			zxymj = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		zxymjList = new ArrayList<Zxymj>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Zxymj".equals(localName)) {
            zxymj = new Zxymj(); 
        }
        tempString = localName; 
	}

	public List<Zxymj> getZxymjList() {
		return this.zxymjList;
	}
}
