package com.wzw.springcloud.handler;

import com.sun.xml.internal.ws.message.AbstractHeaderImpl;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author wuzhiwei
 * @create 2020-11-29 3:15
 */
public class CloudHandler extends AbstractHeaderImpl {
    @Override
    public String getNamespaceURI() {
        return null;
    }

    @Override
    public String getLocalPart() {
        return null;
    }

    @Override
    public String getAttribute(String s, String s1) {
        return null;
    }

    @Override
    public XMLStreamReader readHeader() throws XMLStreamException {
        return null;
    }

    @Override
    public void writeTo(XMLStreamWriter xmlStreamWriter) throws XMLStreamException {

    }

    @Override
    public void writeTo(SOAPMessage soapMessage) throws SOAPException {

    }

    @Override
    public void writeTo(ContentHandler contentHandler, ErrorHandler errorHandler) throws SAXException {

    }
}
