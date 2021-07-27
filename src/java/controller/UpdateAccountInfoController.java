/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author dell
 */
@WebServlet(name = "UpdateAccountInfoController", urlPatterns = {"/UpdateAccountInfoController"})
public class UpdateAccountInfoController extends HttpServlet {
    private final String RESULT_PAGE = "manageUser";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        

        
        String email = request.getParameter("txtAccEmail");
        String password = request.getParameter("txtAccPassword");
        String fullName = request.getParameter("txtAccFullName");
        String phoneNumber = request.getParameter("txtAccPhoneNumber");
        String address = request.getParameter("txtAccAddress");
        String roleId = request.getParameter("txtAccRole");
//        String status = request.getParameter("txtAccStatus");
        String gender = request.getParameter("txtAccGender");

//        boolean statusBoolean = false;     
//        if(status.equalsIgnoreCase("true")) statusBoolean = true;
        
        String url = "UpdateAccount?Email=" + email;

        try{

                UserDAO dao = new UserDAO();
                boolean result = dao.updateAccountInfo(address, password, fullName, phoneNumber, gender, true, Integer.parseInt(roleId), email);

                if (result) {
                    System.out.println("True");
                    HttpSession session = request.getSession();
                    UserDTO user = dao.getUserByEmail(email);
                    session.setAttribute("CURRENT_USER", user);
                }
                else{
                    System.out.println("False");
                }
            
        }
        catch(SQLException ex){
            log("UpdateAccountInfoServlet _ SQL " + ex.getCause());
        }
        catch(NamingException ex){
            log("UpdateAccountInfoServlet _ Naming " + ex.getCause());
        }
        finally{
            //Dung ReqDispatcher se gay loi, do Delete dc goi boi Dispatch roi nen khong dc goi Dispatch tiep
            response.sendRedirect(url);
            //Van dam bao Security, chi khong con luu object
            //Sau khi xoa khong can luu thong tin nua, nen response
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
