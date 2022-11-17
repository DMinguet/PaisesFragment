package com.example.paisesfragment;

import android.content.Context;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ParserPaises {
    private Pais[] paises;
    private final InputStream ficheroPaises;

    public ParserPaises(Context context) {
        this.ficheroPaises = context.getResources().openRawResource(R.raw.countries);
    }

    public boolean parse() {
        boolean parsed = false;
        paises = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document dom = documentBuilder.parse(ficheroPaises);

            Element root = dom.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("country");
            paises = new Pais[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                String codigo = node.getAttributes().getNamedItem("countryCode").getNodeValue();
                String nombre = node.getAttributes().getNamedItem("countryName").getNodeValue();
                int poblacion = Integer.parseInt(node.getAttributes().getNamedItem("population").getNodeValue());
                String capital = node.getAttributes().getNamedItem("capital").getNodeValue();
                String isoAlpha3 = node.getAttributes().getNamedItem("isoAlpha3").getNodeValue();

                paises[i] = new Pais(codigo, nombre, poblacion, capital, isoAlpha3);
            }

            parsed = true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return parsed;
    }

    public Pais[] getPaises() {
        return this.paises;
    }
}
