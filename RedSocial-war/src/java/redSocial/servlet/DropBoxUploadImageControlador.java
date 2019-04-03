///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Servlet;
//
//import com.dropbox.core.DbxAuthInfo;
//import com.dropbox.core.DbxException;
//import com.dropbox.core.DbxRequestConfig;
//import com.dropbox.core.json.JsonReader;
//import com.dropbox.core.v2.DbxClientV2;
//import com.dropbox.core.v2.files.FileMetadata;
//import com.dropbox.core.v2.files.ListFolderResult;
//import com.dropbox.core.v2.files.Metadata;
//import com.dropbox.core.v2.files.UploadErrorException;
//import com.dropbox.core.v2.users.DbxUserUsersRequests;
//import com.dropbox.core.v2.users.FullAccount;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
///**
// *
// * @author damdm
// */
//@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
//public class UploadServlet extends HttpServlet {
//    
//    private static final String ACCESS_TOKEN = "f5A37wyBYpAAAAAAAAAADmKuStsF6qt24CEobvD5MJm3co9mgD-zMSUsAoFU8zmP";    
//    public static final String UPLOAD_DIRECTORY = "upload";
//    public static final String DEFAULT_FILENAME = "default.file";
//
//    public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
//    public static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
//    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
//    
//    DbxClientV2 client = null;
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        if (ServletFileUpload.isMultipartContent(request)) {
//
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//            
//                 
//
//
//            try {
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    for (FileItem item : formItems) {
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            item.write(storeFile);
//                            Logger.getLogger(UploadServlet.class.getName()).log(Level.INFO, "File " + fileName + " has uploaded successfully!");                            
//                            try (InputStream in = new FileInputStream(filePath)) {
//                                FileMetadata metadata = client.files().uploadBuilder(fileName)
//                                                                    .uploadAndFinish(in);
//                            }            
//                            
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                 Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, "There was an error: ", ex.getMessage()); 
//                 ex.printStackTrace();
//            }                         
//            
//            getServletContext().getRequestDispatcher("/nuevoPerfil.jsp").forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            this.dropbox();
//        } catch (DbxException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        super.init(); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//    
//    protected void dropbox() throws DbxException, FileNotFoundException, UploadErrorException, IOException {
//        // Lee el archivo donde esta la informacion de tu carpeta de dropbox
//        DbxAuthInfo authInfo;
//        try {
//            authInfo = DbxAuthInfo.Reader.readFromFile("/Users/damdm/Downloads/test.app");
//            // Crea DbxClientV2, que es lo que se  usa para hacer llamadas a la API.
//            DbxRequestConfig requestConfig = new DbxRequestConfig("red_social");
//            client = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
//        } catch (JsonReader.FileLoadException ex) {
//             Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, "Error loading <auth-file>: ", ex.getMessage());
//        }
//        FullAccount account = null;
//        try {
//            DbxUserUsersRequests users = client.users();
//            account = users.getCurrentAccount();
//        } catch (DbxException ex) {
//            Logger.getLogger(LoginControlador.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Logger.getLogger(LoginControlador.class.getName()).log(Level.INFO, account.getName().getDisplayName());        
//
//        // Obtener archivos y metadatos de carpetas del directorio raíz de Dropbox.
//        ListFolderResult result = client.files().listFolder("");
//        while (true) {
//            for (Metadata metadata : result.getEntries()) {
//                System.out.println(metadata.getPathLower());
//            }
//
//            if (!result.getHasMore()) {
//                break;
//            }
//
//            result = client.files().listFolderContinue(result.getCursor());
//        }
//
//    }
//    
//
//}
