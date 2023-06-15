package com.mobileclient.handler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mobileclient.domain.Jftjc;
public class JftjcListHandler extends DefaultHandler {
	private List<Jftjc> jftjcList = null;
	private Jftjc jftjc;
	private String tempString;
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (jftjc != null) { 
            String valueString = new String(ch, start, length); 
            if ("id".equals(tempString)) 
            	jftjc.setId(new Integer(valueString).intValue());
            else if ("jftj".equals(tempString)) 
            	jftjc.setJftj(valueString); 
            else if ("fs".equals(tempString)) 
            	jftjc.setFs(new Float(valueString).floatValue());
            else if ("typeid".equals(tempString)) 
            	jftjc.setTypeid(new Integer(valueString).intValue());
            else if ("mtypeid".equals(tempString)) 
            	jftjc.setMtypeid(new Integer(valueString).intValue());
            else if ("bz".equals(tempString)) 
            	jftjc.setBz(valueString); 
        } 
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if("Jftjc".equals(localName)&&jftjc!=null){
			jftjcList.add(jftjc);
			jftjc = null; 
		}
		tempString = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		jftjcList = new ArrayList<Jftjc>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
        if ("Jftjc".equals(localName)) {
            jftjc = new Jftjc(); 
        }
        tempString = localName; 
	}

	public List<Jftjc> getJftjcList() {
		return this.jftjcList;
	}
}
