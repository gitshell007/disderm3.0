package com.disderm.servlets;

/**
 * Created by gitshell on 03/11/16.
 */

import com.disderm.conn.DBConnection;
import com.disderm.model.CustomAnswerModel;
import com.disderm.utils.SSLog;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * A Java servlet that handles file upload from client.
 *
 * @author www.codejava.net
 */
@WebServlet(name = "UploadBoquillas", urlPatterns = {"/upload-boquillas"})
public class UploadExcelBoquillas extends HttpServlet {
    int custom_success = 0;
    String custom_message = "";

    private static final SSLog ssLog = new SSLog();
    private static final long serialVersionUID = 1L;
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "/opt/tomcat/latest/resources/uploads";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private File uploadDir;

    /**
     * Upon receiving file upload submission, parses the request to read
     * upload data and saves the file on disk.
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ssLog.info("UploadBoquillas");
        boolean success = true;
        String reply = "";
        Gson gson = new Gson();
        CustomAnswerModel csam = new CustomAnswerModel();
        if (!ServletFileUpload.isMultipartContent(request)) {
            csam.setSuccess(0);
            csam.setMessage("Error: Form must has enctype=multipart/form-data.");
            OutputStream os = response.getOutputStream();

            os.write(reply.getBytes());
            return;
        }


        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        //String uploadPath = getServletContext().getRealPath("")
        String uploadPath = UPLOAD_DIRECTORY;
        ssLog.info("vamos a analizar el nombre de los archivos0");
        // creates the directory if it does not exist
        uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            ssLog.info("No existia el directorio");
            uploadDir.mkdir();
        }
        ssLog.info("vamos a analizar el nombre de los archivos1");
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            String itemValue = "";
            List<FileItem> formItems = upload.parseRequest(request);
            ssLog.info("vamos a analizar el nombre de los archivos");
            String filename = "";
            if (formItems != null && formItems.size() > 0) {
                ssLog.info("1 vamos a analizar el nombre de los archivos");
                // SI ES ARCHIVO
                for (FileItem item : formItems) {

                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();

                        ssLog.info("TEDDYUPLOAD archivo: " + fileName);
                        //System.out.println
                        String filePath = uploadDir + File.separator + "boquillas.xlsx";
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                        uploadPath = uploadDir + File.separator + fileName;

                    }
                }
            }
        } catch (Exception ex) {
            csam.setSuccess(0);
            csam.setMessage("Hubo un error en el proceso intentelo de nuevo");
        }

        // Vamos a leer el excel

        try {
            java.sql.Connection conn = null;
            long start = System.currentTimeMillis();
            uploadPath = uploadDir + File.separator + "boquillas.xlsx";
            FileInputStream inputStream = new FileInputStream(uploadPath);

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();

            conn = new com.disderm.conn.DBConnection(DBConnection.MAINDB).getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO `boquillas` (`boquillas_id`, `index`, `marca`, `modelo`, `submodelo`, `tipo`, `color`, `codref`, `caudal`, `pmax`, `pmin`, `pmaxrecomendada`, `pminrecomendada`, `angulochorro`, `codfoto`) VALUES (NULL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            int count = 0;

            rowIterator.next(); // skip the header row

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            String index = nextCell.getStringCellValue();
                            statement.setString(1, nextCell.getStringCellValue());
                        case 1:
                            statement.setString(2, nextCell.getStringCellValue());
                        case 2:
                            statement.setString(3, nextCell.getStringCellValue());
                        case 3:
                            statement.setString(4, nextCell.getStringCellValue());
                        case 4:
                            statement.setString(5, nextCell.getStringCellValue());
                        case 5:
                            statement.setString(6, nextCell.getStringCellValue());
                        case 6:
                            statement.setString(7, nextCell.getStringCellValue());
                        case 7:
                            statement.setString(8, nextCell.getStringCellValue());
                        case 8:
                            statement.setString(9, nextCell.getStringCellValue());
                        case 9:
                            statement.setString(10, nextCell.getStringCellValue());
                        case 10:
                            statement.setString(11, nextCell.getStringCellValue());
                        case 11:
                            statement.setString(12, nextCell.getStringCellValue());
                        case 12:
                            statement.setString(13, nextCell.getStringCellValue());
                        case 13:
                            statement.setString(14, nextCell.getStringCellValue());

                    }

                }

                statement.addBatch();
                statement.executeBatch();
            }

            workbook.close();

            // execute the remaining queries
            statement.executeBatch();

            conn.commit();
            conn.close();

            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
            custom_success = 1;

        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
            custom_success = 0;
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
            custom_success = 0;
        } catch (Exception e) {
            e.printStackTrace();
            custom_success = 0;
        }

        if (success) {
            csam.setSuccess(1);
            csam.setMessage("Datos actualizados correctamente");
        } else {
            csam.setSuccess(0);
            csam.setMessage("Hubo un error en el proceso intentelo de nuevo");
        }


        response.sendRedirect("/panel/utiles/carga_boquillas.jsp?isSubmit=1&success=" + custom_success + "&message=" + custom_message);
    }
}