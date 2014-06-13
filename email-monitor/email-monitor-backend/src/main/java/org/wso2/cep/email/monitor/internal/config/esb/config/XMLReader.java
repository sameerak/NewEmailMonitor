package org.wso2.cep.email.monitor.internal.config.esb.config;

import org.apache.log4j.Logger;
import org.wso2.cep.email.monitor.exception.EmailMonitorServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Read content from xml files
 */
public class XMLReader {

    private static Logger logger = Logger.getLogger(XMLReader.class);


    public String readXML(String path) throws EmailMonitorServiceException {
        InputStream is;
        BufferedReader br;
        String line;
        String content = "";
        is = ProxyDeployer.class.getResourceAsStream(path);
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while (null != (line = br.readLine())) {
                content = content + line;
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new EmailMonitorServiceException("Error when reading form xml file", e);
        }

        return content;
    }
}
