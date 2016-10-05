package com.l2jwalker.util.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXContentHandler;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public final class XmlUtils
{
    private static final Logger LOG = Logger.getLogger(XmlUtils.class);
    private static final OutputFormat FORMAT = OutputFormat.createPrettyPrint();


    private static final ThreadLocal < SAXReader >  THREAD_LOCAL_SAX_READER =
            new ThreadLocal < SAXReader > () {
                @Override protected SAXReader initialValue()
                {
                    SAXReader reader = new SAXReader();
                    try
                    {
                        XMLReader xmlReader = reader.getXMLReader();
                        SAXContentHandler contentHandler = new SAXContentHandler(DocumentFactory.getInstance());
                        xmlReader.setContentHandler(contentHandler);
                    }
                    catch (Exception e)
                    {
                        return null;
                    }
                    return reader;

                }
            };


    private XmlUtils()
    {
    }

    /**
     * @param xml
     * @return
     */
    public static String prettyPrintXml(String xml)
    {
        final Document doc = parseXml(xml);
        return prettyPrintXml(doc);
    }

    /**
     * @param doc
     * @return
     */
    public static String prettyPrintXml(Document doc)
    {
        Writer resultWriter = null;
        XMLWriter xmlWriter = null;
        String result = null;

        try
        {
            resultWriter = new StringWriter();
            xmlWriter = new XMLWriter(resultWriter, FORMAT);

            xmlWriter.write(doc);

            result = resultWriter.toString();
        }
        catch (final IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            try
            {
                if (xmlWriter != null)
                {
                    xmlWriter.close();
                }

                if (resultWriter != null)
                {
                    resultWriter.close();
                }
            }
            catch (final IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    /**
     * @param doc
     * @return
     */
    public static Document read(InputSource in) throws DocumentException
    {
        try
        {
            SAXReader reader = THREAD_LOCAL_SAX_READER.get();

            XMLReader xmlReader = reader.getXMLReader();
            SAXContentHandler contentHandler = (SAXContentHandler)xmlReader.getContentHandler();
            contentHandler.setInputSource(in);

            xmlReader.parse(in);

            return contentHandler.getDocument();

        }
        catch (Exception e)
        {
            throw new DocumentException(e.getMessage(), e);
        }
    }

    /**
     * @param xml
     * @return
     */
    public static Document parseXml(String xml)
    {
        try
        {
            Document result = null;


            String encoding = getEncoding(xml);

            InputSource source = new InputSource(new StringReader(xml));
            source.setEncoding(encoding);

            //result = THREAD_LOCAL_SAX_READER.get().read(source);
            result = read(source);

            // if the XML parser doesn't provide a way to retrieve the encoding,
            // specify it manually
            if (result.getXMLEncoding() == null)
            {
                result.setXMLEncoding(encoding);
            }

            return result;
        }
        catch (final DocumentException e)
        {
            LOG.error(xml);
            throw new RuntimeException(e);
        }
    }

    // copied from org.dom4j.DocumentHelper
    private static String getEncoding(String text)
    {
        String result = null;

        String xml = text.trim();

        if (xml.startsWith("<?xml"))
        {
            int end = xml.indexOf("?>");
            String sub = xml.substring(0, end);
            StringTokenizer tokens = new StringTokenizer(sub, " =\"\'");

            while (tokens.hasMoreTokens())
            {
                String token = tokens.nextToken();

                if ("encoding".equals(token))
                {
                    if (tokens.hasMoreTokens())
                    {
                        result = tokens.nextToken();
                    }

                    break;
                }
            }
        }

        return result;
    }
}