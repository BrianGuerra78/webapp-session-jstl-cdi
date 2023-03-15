package org.bguerra.apiserclet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.bguerra.apiserclet.webapp.headers.configs.MysqlConn;
import org.bguerra.apiserclet.webapp.headers.services.ServiceJdbcException;
import org.bguerra.apiserclet.webapp.headers.util.ConexionBaseDatos;
import org.bguerra.apiserclet.webapp.headers.util.ConexionBaseDatosDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    /*@Inject
    @Named("conn")
    private Connection conn;*/
    /*@Inject
    @MysqlConn
    private Connection conn;*/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

       /* try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        //try (Connection conn = ConexionBaseDatosDS.getConnection()) {
        // try (Connection connRequest = this.conn) {
       /* try {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }*/
            try {
                //servletRequest.setAttribute("conn", connRequest);
                filterChain.doFilter(servletRequest, servletResponse);
                // connRequest.commit();
                //} catch (SQLException | ServiceJdbcException e) {
            } catch (ServiceJdbcException e) {
                //connRequest.rollback();
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
            // } catch (SQLException | NamingException e) {
       /* } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
