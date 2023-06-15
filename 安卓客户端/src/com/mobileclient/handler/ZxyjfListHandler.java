package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Zxyjf;
public class ZxyjfListHandler extends DefaultHandler {
	private List<Zxyjf> zxyjfList = null;
	private Zxyjf zxyjf;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (zxyjf != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	zxyjf.setId(new Integer(valueString).intValue());
            else if ("bid".equals(tempString)) 
            	zxyjf.setBid(new Integer(valueString).intValue());
            else if ("bname".equals(tempString)) 
            	zxyjf.setBname(valueString); 
            else if ("btypes".equals(tempString)) 
            	zxyjf.setBtypes(new Integer(valueString).intValue());
            else if ("sid".equals(tempString)) 
            	zxyjf.setSid(valueString); 
            else if ("sname".equals(tempString)) 
            	zxyjf.setSname(valueString); 
            else if ("jid".equals(tempString)) 
            	zxyjf.setJid(new Integer(valueString).intValue());
            else if ("jftj".equals(tempString)) 
            	zxyjf.setJftj(valueString); 
            else if ("jfjd".equals(tempString)) 
            	zxyjf.setJfjd(valueString); 
            else if ("jfdate".equals(tempString)) 
            	zxyjf.setJfdate(Timestamp.valueOf(valueString));
            else if ("jdsdate".equals(tempString)) 
            	zxyjf.setJdsdate(Timestamp.valueOf(valueString));
            else if ("fs".equals(tempString)) 
            	zxyjf.setFs(new Float(valueString).floatValue());
            else if ("sl".equals(tempString)) 
            	zxyjf.setSl(new Integer(valueString).intValue());
            else if ("xsfajf".equals(tempString)) 
            	zxyjf.setXsfajf(new Float(valueString).floatValue());
            else if ("hmzfjf".equals(tempString)) 
            	zxyjf.setHmzfjf(new Float(valueString).floatValue());
            else if ("cpfkjf".equals(tempString)) 
            	zxyjf.setCpfkjf(new Float(valueString).floatValue());
            else if ("dwzsjf".equals(tempString)) 
            	zxyjf.setDwzsjf(new Float(valueString).floatValue());
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Zxyjf".equals(localName)&&zxyjf!=null){
			zxyjfList.add(zxyjf);
			zxyjf = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		zxyjfList = new ArrayList<Zxyjf>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Zxyjf".equals(localName)) {
            zxyjf = new Zxyjf(); 
        }
        tempString = localName; 
	}

	public List<Zxyjf> getZxyjfList() {
		return this.zxyjfList;
	}
}
