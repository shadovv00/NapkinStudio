package com.napkinstudio.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;

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
    
    
    
    public void transferOrdersFromFtpToLocalStorage(String rootFolder, ChannelSftp sftpChannel) throws IOException {
    	Vector attachments = null;
    	Vector printproofs = null;
        String sPathToLocalFile = null;
        String sPathToFtpFile = null;
        String dirName = null, attachName = null, prproofName = null;
        try {
        	System.out.println();
        	System.out.println("Get orders files from \"orders\"");
			Vector orderDirrectories = sftpChannel.ls(rootFolder + "sftp/orders");
	        for (Object entry : orderDirrectories) {
	            ChannelSftp.LsEntry e = (ChannelSftp.LsEntry) entry;
	            dirName = e.getFilename();
	            if(".".equals(dirName) || "..".equals(dirName)) {
	            	continue;
	            }
	            
	            System.out.println("\nAttachments: " + dirName + "/final");
	            try {
	            	System.out.print("Get file list within: sftp/orders/" + dirName + "/final");
	            	attachments = sftpChannel.ls(rootFolder + "sftp/orders/" + dirName + "/final");
	            	System.out.println(" success!");
	            } catch(SftpException s1) {
	            	attachments = null;
	            	System.out.println(" fail! " + s1.getMessage());
	            }
	            if(attachments != null) {
	            	for (Object entry2 : attachments) {
	            		ChannelSftp.LsEntry e2 = (ChannelSftp.LsEntry) entry2;
	            		attachName = e2.getFilename();
	            		if(".".equals(attachName) || "..".equals(attachName)) {
	            			continue;
	            		}
	            		sPathToLocalFile = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + dirName + SEP + ORDERS_FINAL_DIRECTORY + SEP + attachName;
	            		sPathToFtpFile = rootFolder + "sftp/orders/" + dirName + "/final/" + attachName;
	            		try(OutputStream os = saveFileToLocalDirectory(sPathToLocalFile)) {
	            			try {
	            				System.out.print("Copy \"" + attachName + "\":");
	            				sftpChannel.get(sPathToFtpFile, os);
	            				System.out.println(" success! ");
	            			} catch(SftpException s1) {
	            				System.out.println(" fail! " + s1.getMessage());
	            			}
	            			try {
	            				System.out.print("Remove file \"" + attachName + "\":");
	            				sftpChannel.rm(sPathToFtpFile);
	            				System.out.println(" success!");
	            			} catch(SftpException s1) {
	            				System.out.println(" fail! " + s1.getMessage());
	            			}
	            		}
	            		
	            	}
	            	try{
	            		this.count = 200;
	            		System.out.println("No more vital items within \"" + dirName + "/final\"");
	            		System.out.print("Remove directory \"" + dirName + "/final\":");
	            		deleteDirectory(sftpChannel, rootFolder + "sftp/orders/" + dirName + "/final");
	            		System.out.println(" success!");
	            	} catch(SftpException s1) {
	            		System.out.println(" fail! " + s1.getMessage());
	            	}
	            }
	        	
	        	System.out.println("\nPrintproof: " + dirName + "/ppfinal");
	        	try {
	            	System.out.print("Get file list within: sftp/orders/" + dirName + "/ppfinal");
	            	printproofs = sftpChannel.ls(rootFolder + "sftp/orders/" + dirName + "/ppfinal");
	            	System.out.println(" success!");
	            } catch(SftpException s1) {
	            	printproofs = null;
	            	System.out.println(" fail! " + s1.getMessage());
	            }
	        	if(printproofs != null) {
	        		for(Object entry3: printproofs) {
	        			ChannelSftp.LsEntry e3 = (ChannelSftp.LsEntry) entry3;
	        			prproofName = e3.getFilename();
	        			if(".".equals(prproofName) || "..".equals(prproofName)) {
	        				continue;
	        			}
	        			sPathToLocalFile = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + dirName + SEP + ORDERS_PPFINAL_DIRECTORY + SEP + prproofName;
	        			sPathToFtpFile = rootFolder + "sftp/orders/" + dirName + "/ppfinal/" + prproofName;
	        			try(OutputStream os = saveFileToLocalDirectory(sPathToLocalFile)) {
	        				try{
	        					System.out.print("Copy file \"" + prproofName + "\":");
	        					sftpChannel.get(sPathToFtpFile, os);
	        					System.out.println(" success! ");
	        				} catch(SftpException s1) {
	        					System.out.println(" fail! " + s1.getMessage());
	        				}
	        				try {
	        					System.out.print("Remove directory \"" + prproofName + "\":");
	        					sftpChannel.rm(sPathToFtpFile);
	        					System.out.println(" success!");
	        				} catch(SftpException s1) {
	        					System.out.println(" fail! " + s1.getMessage());
	        				}
	        				//must be one file max only!
	        				break;
	        			}
	        		}
	        		try {
	        			this.count = 200;
	        			System.out.println("No more vital items within \"" + dirName + "/ppfinal\"");
	        			System.out.print("Remove directory \"" + dirName + "/ppfinal\": ");
	        			deleteDirectory(sftpChannel, rootFolder + "sftp/orders/" + dirName + "/ppfinal");
	        			System.out.println(" success!");
	        		} catch(SftpException s1) {
	        			System.out.println(" fail! " + s1.getMessage());
	        		}
	        	}
	        	
	        	try {
	        		this.count = 200;
	        		System.out.println();
	        		System.out.println("No more vital items within \"" + dirName + "\"");
	        		System.out.print("Remove directory \"" + dirName + "\": ");
	        		deleteDirectory(sftpChannel, rootFolder + "sftp/orders/" + dirName);
	        		System.out.println(" success!");
	        	} catch(SftpException s1) {
	        		System.out.println(" fail! " + s1.getMessage());
	        		s1.printStackTrace();
	        	}
	        	
	        }
		} catch (SftpException e) {
			System.out.println("Fail! Directory \"orders\" " + e.getMessage());
		}
        System.out.println();
    }
    private OutputStream saveFileToLocalDirectory(String sPathToLocalFile) throws IOException {
        
    	Path pathToLocalFile = Paths.get(sPathToLocalFile);
    	
    	if(!Files.exists(pathToLocalFile)) {
    		Files.createDirectories(pathToLocalFile.getParent());
    		Files.createFile(pathToLocalFile);
    	}
    	OutputStream os = Files.newOutputStream(pathToLocalFile);
    	return os;
    }
    private static int count;
    private static void deleteDirectory(ChannelSftp sftp, String oldestBackup) throws SftpException {
    	if(count-- <= 0) return;
        if (isDir(sftp, oldestBackup)) {
            sftp.cd(oldestBackup);
            @SuppressWarnings("unchecked")
			Vector < LsEntry > entries = sftp.ls(".");
            for (LsEntry entry: entries) {
            	if(".".equals(entry.getFilename()) || "..".equals(entry.getFilename())) {
            		continue;
            	}
                deleteDirectory(sftp, entry.getFilename());
            }
            sftp.cd("..");
            sftp.rmdir(oldestBackup);
        } else {
            sftp.rm(oldestBackup);
        }
    }

    private static boolean isDir(ChannelSftp sftp, String entry) throws SftpException {
        return sftp.stat(entry).isDir();
    }
}
