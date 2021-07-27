/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import category.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import order.OrderDAO;
import product.ProductDAO;

/**
 *
 * @author RiceShower
 */
@WebServlet(name = "GetSaleStatisticController", urlPatterns = {"/GetSaleStatisticController"})
public class GetSaleStatisticController extends HttpServlet {

    private final String SALE_HOME_PAGE = "HomeSale.jsp";

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
        String url = SALE_HOME_PAGE;

        try {
            //Get current date and the date 7 days ago
            long millis = System.currentTimeMillis();
            java.sql.Date date2 = new java.sql.Date(millis);
            String DateString = String.valueOf(date2);
            LocalDate dateLocal1 = LocalDate.parse(DateString);
            LocalDate dateLocal2 = dateLocal1.minusDays(7);
            java.sql.Date date1 = java.sql.Date.valueOf(dateLocal2);

            //Get stat for order in the past 7 days
            OrderDAO dao = new OrderDAO();
            int cancelOrder = dao.totalOfOrderByStatusInOneWeek("cancel", date1, date2);
            request.setAttribute("CANCEL_ORDER_NUMBER", cancelOrder);

            int completedOrder = dao.totalOfOrderByStatusInOneWeek("completed", date1, date2);
            request.setAttribute("COMPLETED_ORDER_NUMBER", completedOrder);

            double revenue = dao.getTheRevenuePass7Days("completed", date1, date2);
            request.setAttribute("REVENUE_SEVEN_DAY", revenue);

            LinkedHashMap<Date, Double> detailRevenue = new LinkedHashMap<Date, Double>();
            for (int i = 6; i >= 0; i--) {
                //Get time
                long milli = System.currentTimeMillis();
                java.sql.Date date2nd = new java.sql.Date(milli);
                String DateString2 = String.valueOf(date2nd);
                LocalDate dateLocal1st = LocalDate.parse(DateString2);
                LocalDate dateLocal2nd = dateLocal1st.minusDays(i);
                java.sql.Date date = java.sql.Date.valueOf(dateLocal2nd);
                //Get Revenue for the day
                double rev = dao.getTotalRevenueForOneDay(date);
                //Add the info to the hash map
                detailRevenue.put(date, rev);
            }
            request.setAttribute("DETAIL_REVENUE", detailRevenue);
            LinkedHashMap maxMap = detailRevenue;
            request.setAttribute("MAX_REVENUE", Collections.max(maxMap.values()));

            //Get category stat
            CategoryDAO cateDao = new CategoryDAO();
            int numberOfCategory = cateDao.numberOfCategories("cake");
            request.setAttribute("TOTAL_CATEGORY", numberOfCategory);
            
            //Get product stat
            ProductDAO productDao = new ProductDAO();
            int numberOfProduct = productDao.numberOfProduct("true");
            request.setAttribute("TOTAL_PRODUCT", numberOfProduct);

        } catch (SQLException ex) {
            log("GetSaleStatisticController_SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
