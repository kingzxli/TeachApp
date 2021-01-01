package com.song.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.song.pojo.PaymentDto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtil {
	
	public static Map<String,Object> parseXML(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,Object> map=new HashMap<String,Object>();
        /* 通过IO获得Document */
        SAXReader reader = new SAXReader();
        Document doc = reader.read(request.getInputStream());
        //得到xml的根节点
        Element root=doc.getRootElement();
        recursiveParseXML(root,map);
        return map;
    }
    private static void recursiveParseXML(Element root,Map<String,Object> map){
        //得到根节点的子节点列表
        List<Element> elementList=root.elements();
        //判断有没有子元素列表
        if(elementList.size()==0){
            map.put(root.getName(), root.getTextTrim());
        }
        else{
            //遍历
            for(Element e:elementList){
                recursiveParseXML(e,map);
            }
        }
    }
 
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点都增加CDATA标记
                boolean cdata = true;
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write(text);
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    public static String messageToXML(PaymentDto paymentPo){
        xstream.alias("xml",PaymentDto.class);
        return xstream.toXML(paymentPo);
    }
    
    /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		/*=============  !!!!注意，修复了微信官方反馈的漏洞，更新于2018-10-16  ===========*/
		try {
            Map<String, String> data = new HashMap<String, String>();
            // TODO 在这里更换
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setExpandEntityReferences(false);
 
            InputStream stream = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = (Node) nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }
	}
	
	public static String map2Xml(Map<String , Object> paras) {
        SortedMap<String, Object> paraMap = new TreeMap<String, Object>();
        paraMap.putAll(paras);
        String xmlStr = "<xml>";
        Set es = paraMap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            xmlStr += "<" + k + ">" + v + "</" + k + ">";
        }
        xmlStr += "</xml>";
        return xmlStr;
    }


}
