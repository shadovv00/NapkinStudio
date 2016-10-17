package com.napkinstudio.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.napkinstudio.entity.Attachment;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.User;
import com.napkinstudio.simplemodel.FileInfo;
import com.napkinstudio.util.SessionUtil;

@Service
public class OrderPageManager {
	
	private final static String USER_HOME = System.getProperty("user.home");
	
	private final static String UPLOAD_DIRECTORY = "napkinStorage";
	
	private final static String ORDERS_DIRECTORY = "orders";
	
	private final static String SEP = System.getProperty("file.separator");
	
	private final static String ORDERS_TMP_DIRECTORY = "temp";
	
	private final static String ORDERS_FINAL_DIRECTORY = "final";
	
	private final static String ORDERS_PREVIEW_DIRECTORY = "preview";
	
	private final static String ORDERS_PPTMP_DIRECTORY = "pptemp";
	
	private final static String ORDERS_PPFINAL_DIRECTORY = "ppfinal";
	
	@Autowired
    private SessionUtil sessionUtil;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
    private OrderManager orderManager;
	
	@Autowired
	private AttachmentManager attachmentManager;
	
	
//	public static void main(String[] args) {
//		new OrderPageManager().clearTmpDirectory(1402130001);
//	}
	
	
	
	public ResponseEntity<String> savePrintProofToTmpDirectory(@RequestParam("files[]") MultipartFile multipartFile, @PathVariable int orderId) {
        System.out.println("###### PRINTPROOF ###### " + multipartFile.getOriginalFilename());
        String fileName = multipartFile.getOriginalFilename();

        String tmpPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_PPTMP_DIRECTORY;

        Path directory = Paths.get(tmpPath);

        if (Files.notExists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<String>(fileName, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            //remove all files from tmp directory
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                for (Path path : directoryStream) {
                    try {
                        Files.delete(Paths.get(path.toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException ex) {
            }

        }
        try {
            File file = new File(tmpPath + SEP + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(fileName, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(fileName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(fileName, HttpStatus.OK);
    }
	
	
	
	public ResponseEntity<String> hasPriptproof(HttpServletResponse response, int orderId) {
    	System.out.println(orderId + " has printproof");
    	String ppFnlDirPathStr = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_PPFINAL_DIRECTORY;
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(ppFnlDirPathStr))) {
			if(directoryStream.iterator().hasNext()) {
				return new ResponseEntity<>("ok", HttpStatus.OK);
			}
		} catch (IOException ex) {}
		return new ResponseEntity<>("no", HttpStatus.OK);
    }
	
	
	
	public void getPrintproof(HttpServletResponse response, @PathVariable int orderId) {
    	System.out.println("get printproof");
    	String mimeType = null;
    	InputStream is = null;
    	String ppFnlDirPathStr = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_PPFINAL_DIRECTORY;
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(ppFnlDirPathStr))) {
			for (Path path : directoryStream) {
				try {
					is = Files.newInputStream(path);
					mimeType = Files.probeContentType(path);
					String[] tokens = path.getFileName().toString().split("\\.(?=[^\\.]+$)");
					System.out.println(Arrays.toString(tokens));
					if(tokens.length == 2) {
						if(tokens[1].toLowerCase().equals("svg")) {
							mimeType = "image/svg+xml";
						}
					}
					if (mimeType == null) {
			            System.out.println("mimetype is not detectable, will take default");
			            mimeType = "application/octet-stream";
			        }
			        System.out.println("mimetype : " + mimeType);
			        response.setContentType(mimeType);
			        response.setContentLength((int) Files.size(path));
			        FileCopyUtils.copy(is, response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
				break;
			}
		} catch (IOException ex) {
		} finally {
			try {
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    }
	
	
	
	public ResponseEntity<String> clearTmpDirectory(int orderId) {
		
		Path pathToTmpDirectory = Paths.get(USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_TMP_DIRECTORY);
		
		File folder = new File(pathToTmpDirectory.toString());
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles) {
			System.out.println("delete file \"" + file.getName() + "\": " + file.delete());
		}
		
		if(folder.listFiles().length == 0) {
			return new ResponseEntity<>("ok", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.CONFLICT);
		}
	}
	
	
	
	public ResponseEntity<String> saveFileToTmpDirectory(MultipartFile multipartFile, int orderId) {
		UserDetails 	userDetails = sessionUtil.getUserDetails();
		String 			fileName = multipartFile.getOriginalFilename();
		
		Path 	pathToTmpDirectory = Paths.get(USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_TMP_DIRECTORY + SEP),
				pathToTmpFile = pathToTmpDirectory.resolve(fileName);
		
		
		if(userDetails != null) {
			System.out.println("save to tmp: " + fileName);
			
		    try {
		    	
		    	//ensure the appropriate temporary directory exist
				if(!Files.exists(pathToTmpDirectory)) {
					try {
						Files.createDirectories(pathToTmpDirectory);
					} catch(FileAlreadyExistsException faee) {
						System.out.println("directory exists!");
					}
				}
		    	
		    	
				//create an empty file
				if(!Files.exists(pathToTmpDirectory)) {
					try {
						Files.createFile(pathToTmpFile);
					} catch(FileAlreadyExistsException faee) {
						System.out.println("file exists!");
					}
				}
		    	
				
				Files.copy(multipartFile.getInputStream(), pathToTmpFile, StandardCopyOption.REPLACE_EXISTING);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return new ResponseEntity<String>(fileName, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		    return new ResponseEntity<String>(fileName, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(fileName, HttpStatus.FORBIDDEN);
		}
	}
	
	
	
	public ResponseEntity<List<String>> removeAllAtachments(int orderId, List<String> files) {
        System.out.println("> remove all temps " + files);
        String tmpPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_TMP_DIRECTORY + SEP;

        List<String> removedfiles = new ArrayList<>();
        Path filePath = null;
        for (String fileName : files) {
            filePath = Paths.get(tmpPath + fileName);
            try {
                Files.delete(filePath);
                removedfiles.add(fileName);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such file or directory%n", filePath);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", filePath);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        return new ResponseEntity<>(removedfiles, HttpStatus.OK);
    }
	
	
	
	public ResponseEntity<String> removeAtachment(int orderId, String fileName) {
        System.out.println("> remove-temp-file " + fileName);
        String tmpPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_TMP_DIRECTORY + SEP + fileName;
        try {
            File file = new File(tmpPath);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
                return new ResponseEntity<String>(fileName, HttpStatus.OK);
            } else {
                System.out.println(file.getName() + " not found!");
                return new ResponseEntity<String>(fileName, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(fileName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	
	@Transactional
	public ResponseEntity<Map<String, Object>> getAllOrderAttachments(String orderId) {
		
		Map<String, Object> respMap = new HashMap<>();
        List<FileInfo> fileInfoList = new ArrayList<>();
        respMap.put("attachmentList", fileInfoList);
        
        FileInfo fileInfo;
        File file;
        
        
        UserDetails userDetails = sessionUtil.getUserDetails();
        if(userDetails == null) {
        	respMap.put("authentication", new String("user must not be anonimus"));
        	return new ResponseEntity<>(respMap, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        
        
        User user = userManager.findByLogin(userDetails.getUsername());
        
        Role role = user.getRole();
        System.out.println(role);
        Map<String, Attachment> mapAttachment = attachmentManager.findAttachmentMapByOrderIdRoleId(Integer.parseInt(orderId), role.getId());
        Map<String, Attachment> mapAllAttachment = attachmentManager.findAttachmentMapByOrderId(Integer.parseInt(orderId));
        Attachment a;
        
        String fnlDirPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_FINAL_DIRECTORY + SEP;

        System.out.println(fnlDirPath);

        File folder = new File(fnlDirPath);
        if (folder.exists()) {
            File[] listOfFiles = folder.listFiles();
            Map<String, File> mapOfFiles = new HashMap<>();
            for(File f : listOfFiles) {
            	mapOfFiles.put(f.getName(), f);
            }
            for(String attachmentName : mapAllAttachment.keySet()) {
            	a = mapAllAttachment.get(attachmentName);
            	System.out.println("\t\t" + attachmentName);
            	file = mapOfFiles.get(a.getName());
            	if(file != null) {
            		if (file.isFile()) {
                        System.out.println("File: " + file.getName());
                        fileInfo = new FileInfo();
                        fileInfo.setName(file.getName());
                        fileInfo.setSize(file.length());
                        fileInfo.setLastModified(new Date(file.lastModified()));
                        fileInfo.setAllowDelete(allowDeleteAttachment(file.getName(), mapAttachment));
                        fileInfoList.add(fileInfo);
                    } else if (file.isDirectory()) {
                        System.out.println("Directory " + file.getName());
                    }
            	} else {
            		System.out.println("file was not found in folder, thus it must be deleted from db either!");
            		try {
						throw new FileNotFoundException("file was not found in folder, thus it must be deleted from db either!");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						attachmentManager.deleteAttachment(a);
					}
            	}
            	mapOfFiles.remove(attachmentName);
            }
            
            //delete all files from folder which is absent in the database
            if(!mapOfFiles.isEmpty()) {
            	try {
            		throw new Exception("some files are present in database, hovewer they are not present in apropriate folder!");
            	} catch (Exception e) {
            		e.printStackTrace();
            		for (String key : mapOfFiles.keySet()) {
            			file = mapOfFiles.get(key);
            			System.out.println("This file is absent in db \"" + file.getName() + "\", so I'm deleting it: "+ file.delete());
            		}
            	}            	
            }
            
        } else {
            System.out.println("for this order any attachment is not available!");
        }
        return new ResponseEntity<>(respMap, HttpStatus.OK);
    }
	private boolean allowDeleteAttachment(String attachmentName, Map<String, Attachment> availableUserAttachmentMap) {
		boolean allow = false;
		if(availableUserAttachmentMap.get(attachmentName) != null) {
			allow = true;
		}
		return allow;
	}
	
	
	
	public void downloadFile(HttpServletResponse response, String orderId, String fileName) throws IOException {
        System.out.println("!! DOWNLOAD !!");
        File file = null;

        String fnlDirPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_FINAL_DIRECTORY + SEP;

        System.out.println(fnlDirPath + fileName);
        file = new File(fnlDirPath + fileName);

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);
        response.addHeader("X-Frame-Options", "SAMEORIGIN");
	  /*
	   * "Content-Disposition : inline" will show viewable types [like
	   * images/text/pdf/anything viewable by browser] right on browser while
	   * others(zip e.g) will be directly downloaded [may provide save as
	   * popup, based on your browser setting.]
	   */
        
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

	  /*
	   * "Content-Disposition : attachment" will be directly download, may
	   * provide save as popup, based on your browser setting
	   */
		// response.setHeader("Content-Disposition", String.format("attachment;
		// filename=\"%s\"", file.getName()));
		
		response.setContentLength((int) file.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	
	@Transactional
	public ResponseEntity<String> removeOrderAttachment(String orderId, String fileName) {
        String fnlDirPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_FINAL_DIRECTORY + SEP;
        String path = fnlDirPath + fileName;
        
        
        UserDetails userDetails = sessionUtil.getUserDetails();
        if(userDetails == null) {
        	return new ResponseEntity<>("user must not be anonimus", HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        
        
        User user = userManager.findByLogin(userDetails.getUsername());
        Role role = user.getRole();
        Map<String, Attachment> mapAttachment = attachmentManager.findAttachmentMapByOrderIdRoleId(Integer.parseInt(orderId), role.getId());
        Attachment attachment;
        
        Path pathToAttachment = Paths.get(path);
        if (Files.exists(pathToAttachment)) {
        	if((attachment = mapAttachment.get(fileName)) instanceof Attachment) {
        		try {
					Files.delete(pathToAttachment);
					attachmentManager.deleteAttachment(attachment);
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
        		return new ResponseEntity<String>(HttpStatus.OK);
        	} else {
        		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
        	}
        } else {
            System.out.println("file not found!");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	
	@Transactional
	public ResponseEntity<Map<String, Object>> approve(int orderId, List<String> files, String printproof) {
        System.out.println("> approve");
        System.out.println(files);

        System.out.println(printproof);
        Map<String, Object> respMap = new HashMap<>();

        String tmpDirPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_TMP_DIRECTORY + SEP;
        String fnlDirPath = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_FINAL_DIRECTORY + SEP;

        Path 	pathFrom,
        		parhTo;
        
        UserDetails userDetails = sessionUtil.getUserDetails();
        if(userDetails == null) {
        	respMap.put("authentication", new String("user must not be anonimus"));
        	return new ResponseEntity<>(respMap, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
        
        
        User user = userManager.findByLogin(userDetails.getUsername());
        Order order = orderManager.findById(orderId);
        Role role = user.getRole();
        Attachment attachment;
        
        try {
            Files.createDirectories(Paths.get(fnlDirPath));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (files != null) {
            List<String> movedFiles = new ArrayList<>();
            List<Attachment> attachmentsList = new ArrayList<>();
            for (String fileName : files) {
                pathFrom = Paths.get(tmpDirPath + fileName);
                parhTo = Paths.get(fnlDirPath + fileName);
                 try {
                	 Files.move(pathFrom, parhTo, StandardCopyOption.REPLACE_EXISTING);
                	 movedFiles.add(fileName);
                	 attachment = new Attachment();
                	 attachment.setName(fileName);
                	 attachment.setAppendDate(new Date());
                	 attachment.setOrder(order);
                	 attachment.setRole(role);
                	 attachmentsList.add(attachment);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            attachmentManager.save(attachmentsList);
            respMap.put("attachments", movedFiles);

        } else {
            respMap.put("attachments", null);

        }

        String 	pptpmDirPathStr = null,
        		ppFnlDirPathStr = null;
        Path 	ppFnlDir = null,
                ppTmpFile = null,
                ppFnlFile = null;
         if (printproof != null) {
            pptpmDirPathStr = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_PPTMP_DIRECTORY;
            ppFnlDirPathStr = USER_HOME + SEP + UPLOAD_DIRECTORY + SEP + ORDERS_DIRECTORY + SEP + orderId + SEP + ORDERS_PPFINAL_DIRECTORY;
            ppFnlDir = Paths.get(ppFnlDirPathStr);
             if (Files.notExists(ppFnlDir)) {
                 try {
                    Files.createDirectory(ppFnlDir);
                } catch (IOException e) {
                    e.printStackTrace();
                    respMap.put("printproof", "error");
                    return new ResponseEntity<>(respMap, HttpStatus.INTERNAL_SERVER_ERROR);

                }
            }
            try {
		     	//remove all files from pp final directory
        	 	try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(ppFnlDir)) {
	         		for (Path path : directoryStream) {
             			try {
             				Files.delete(Paths.get(path.toString()));
             			} catch (IOException e) {
             				e.printStackTrace();
             			}
             		}
        	 	} catch (IOException ex) {}
			     	ppTmpFile = Paths.get(pptpmDirPathStr + SEP + printproof);
			     	ppFnlFile = Paths.get(ppFnlDirPathStr + SEP + printproof);
	
	             	if(!printproof.equals("remove")) {
	         		Files.move(ppTmpFile, ppFnlFile, StandardCopyOption.REPLACE_EXISTING);
	         	}
				respMap.put("printproof", printproof);
			} catch (IOException e) {
				e.printStackTrace();
				respMap.put("printproof", "fail");
		 	}
        } else {
		 	respMap.put("printproof", null);
        }
        return new ResponseEntity<>(respMap, HttpStatus.OK);
    }
}
