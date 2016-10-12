package com.napkinstudio.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.stereotype.Component;

/**
 * Created by User1 on 05.08.2016.
 */
@Component
public class FileTransfer {
	
    private final static String USER_HOME = System.getProperty("user.home");

    private final static String UPLOAD_DIRECTORY = "napkinStorage";

    private final static String ORDERS_DIRECTORY = "orders";

    private final static String SEP = System.getProperty("file.separator");

    private final static String ORDERS_TMP_DIRECTORY = "temp";

    private final static String ORDERS_FINAL_DIRECTORY = "final";

    private final static String ORDERS_PREVIEW_DIRECTORY = "preview";

    private final static String ORDERS_PPTMP_DIRECTORY = "pptemp";

    private final static String ORDERS_PPFINAL_DIRECTORY = "ppfinal";
    
    
    
    public void transferOrdersFromFtpToLocalStorage(FTPSClient ftpClient) throws IOException {

        FTPFile[] orderDirrectories = ftpClient.listDirectories("/orders");
        FTPFile[] attachments = null;
        FTPFile[] printproofs = null;
        String sPathToLocalFile = null;
        String sPathToFtpFile = null;
        
        
        for(FTPFile orderDir : orderDirrectories) {
        	System.out.println("attachments");
        	attachments = ftpClient.listFiles("/orders/" + orderDir.getName() + "/final");
        	ftpClient.enterLocalPassiveMode();
        	for(FTPFile attachment : attachments) {
        		sPathToLocalFile = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderDir.getName() + SEP + ORDERS_FINAL_DIRECTORY + SEP + attachment.getName();
        		sPathToFtpFile = "/orders/" + orderDir.getName() + "/final/" + attachment.getName();
        		try(OutputStream os = saveFileToLocalDirrectory(sPathToLocalFile)) {
        			ftpClient.retrieveFile(sPathToFtpFile, os);
        			System.out.println(attachment.getName() + " : " + ftpClient.deleteFile(sPathToFtpFile));
        		}
        	}
        	ftpClient.enterLocalActiveMode();
        	System.out.println("\nremove dirrectory \"" + orderDir.getName() + "/final\": " + ftpClient.removeDirectory("/orders/" + orderDir.getName() + "/final"));
        	
        	printproofs = ftpClient.listFiles("/orders/" + orderDir.getName() + "/ppfinal");            	
        	System.out.println("printproof");
        	ftpClient.enterLocalPassiveMode();
        	for(FTPFile printproof: printproofs) {
        		sPathToLocalFile = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderDir.getName() + SEP + ORDERS_PPFINAL_DIRECTORY + SEP + printproof.getName();
        		sPathToFtpFile = "/orders/" + orderDir.getName() + "/ppfinal/" + printproof.getName();
        		try(OutputStream os = saveFileToLocalDirrectory(sPathToLocalFile)) {
        			
        			ftpClient.retrieveFile(sPathToFtpFile, os);
        			
        			System.out.println(printproof.getName() + " : " + ftpClient.deleteFile(sPathToFtpFile));
        			break;
        		}
        	}
        	ftpClient.enterLocalActiveMode();
        	System.out.println("\nremove dirrectory \"" + orderDir.getName() + "/ppfinal\": " + ftpClient.removeDirectory("/orders/" + orderDir.getName() + "/ppfinal"));
        	System.out.println("\nremove dirrectory \"" + orderDir.getName() + "\": " + ftpClient.removeDirectory("/orders/" + orderDir.getName()));
        }
    }
    private OutputStream saveFileToLocalDirrectory(String sPathToLocalFile) throws IOException {
        
    	Path pathToLocalFile = Paths.get(sPathToLocalFile);
    	
    	if(!Files.exists(pathToLocalFile)) {
    		Files.createDirectories(pathToLocalFile.getParent());
    		Files.createFile(pathToLocalFile);
    	}
    	OutputStream os = Files.newOutputStream(pathToLocalFile);
    	return os;
    }
}
