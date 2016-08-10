package com.napkinstudio.util;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by User1 on 05.08.2016.
 */
public class FTPCommunicator {

    private static final String UPLOAD_PATH ="ftp://root:123@localhost//NapkinStudio_uploads/User.xml;type=i";
    private static final String DOWNLOAD_PATH ="ftp://root:123@localhost//SAP_uploads/User.xml;type=i";
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    private URLConnection getConnection(String path) throws IOException {
        URL url = new URL(path);
        return  url.openConnection();
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void convertToXMLAndUpload(Object object)
            throws IOException {
            OutputStream os = null;
        try {
             os = getConnection(UPLOAD_PATH).getOutputStream();
            getMarshaller().marshal(object, new StreamResult(os));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public Object convertToObjectAndDownload() throws IOException {
        InputStream is = null;
        try {
            is = getConnection(DOWNLOAD_PATH).getInputStream();
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

}
