package com.lntormin.javaee.geo.xsl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author lntormin
 */
public class XslTransform {

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException, FileNotFoundException, UnsupportedEncodingException {
        Source xmlSource = new StreamSource("web/data/positions.xml");
        File xslFile = new File("web/xsl/gpx.xsl");
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(new StreamSource(xslFile));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        trans.transform(xmlSource, new StreamResult(bos));
        try (PrintWriter writer = new PrintWriter("web/data/positions_proc.gpx", "UTF-8")) {
            writer.print(bos);
        }
    }
}
