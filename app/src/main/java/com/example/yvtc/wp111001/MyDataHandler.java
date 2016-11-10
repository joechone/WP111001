package com.example.yvtc.wp111001;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yvtc on 2016/11/10.
 */

public class MyDataHandler extends DefaultHandler {
    boolean inTitle, inItem;
    String strTitle;
    ArrayList<String> mylist = new ArrayList<>();
    ArrayList<Map<String,Object>> mylist3 = new ArrayList<Map<String,Object>>();
    HashMap<String,Object> m1 =new HashMap<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        strTitle = qName;
        if (qName.equals("title"))
        {
            inTitle = true;

        }
        if (qName.equals("item"))
        {
            m1 =new HashMap<>();
            inItem = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("title"))
        {
            inTitle = false;
        }
        if (qName.equals("item"))
        {
            inItem = false;
            mylist3.add(m1);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (inTitle & inItem)
        {
            String str = new String(ch).substring(start, start + length);
            mylist.add(str);
            Log.d("NET", str);
        }
        if(inItem)
        {
            String str = new String(ch).substring(start, start + length);
            m1.put(strTitle,str);
            Log.d("strTitle", strTitle);
            Log.d("str", str);
        }
    }
}
