package com.example.Feedback.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // Annotation for modern Servlet mapping
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Maps this servlet to the URL pattern /FeedbackServlet
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/feedbackdb?";
    private static final String DB_USER = "root"; // <<<--- IMPORTANT: Change this
    private static final String DB_PASSWORD = "Vanakam!001"; // <<<--- IMPORTANT: Change this

    // SQL query to insert feedback
    private static final String INSERT_FEEDBACK_SQL =
            "INSERT INTO feedback (user_name, book_name, feedback_text) VALUES (?, ?, ?)";

    /**
     * Handles HTTP POST requests from the feedback form.
     *
     * @param request The HttpServletRequest object containing client request information.
     * @param response The HttpServletResponse object for sending a response to the client.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String userName = request.getParameter("userName");
        String bookName = request.getParameter("bookName");
        String feedbackText = request.getParameter("feedbackText");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load the JDBC driver (optional for modern JDBC, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL statement to prevent SQL injection
            preparedStatement = connection.prepareStatement(INSERT_FEEDBACK_SQL);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, bookName);
            preparedStatement.setString(3, feedbackText);

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>Feedback Status</title>");
                out.println("    <script src=\"https://cdn.tailwindcss.com\"></script>");
                out.println("    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap\" rel=\"stylesheet\">");
                out.println("    <style>body { font-family: 'Inter', sans-serif; }</style>");
                out.println("</head>");
                out.println("<body class=\"bg-gradient-to-br from-cyan-50 via-teal-50 to-emerald-50 min-h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8\">");
                out.println("    <div class=\"max-w-md w-full bg-white p-8 rounded-xl shadow-lg text-center\">");
                out.println("        <h2 class=\"mt-6 text-3xl font-extrabold text-gray-900\">Feedback Submitted!</h2>");
                out.println("        <p class=\"mt-4 text-xl text-green-600\">Feedback saved!</p>");
                out.println("        <div class=\"mt-8\">");
                out.println("            <a href=\"feedback.html\" class=\"inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md shadow-sm text-white bg-emerald-600 hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition duration-150 ease-in-out\">Submit More Feedback</a>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>Feedback Status</title>");
                out.println("    <script src=\"https://cdn.tailwindcss.com\"></script>");
                out.println("    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap\" rel=\"stylesheet\">");
                out.println("    <style>body { font-family: 'Inter', sans-serif; }</style>");
                out.println("</head>");
                out.println("<body class=\"bg-gradient-to-br from-cyan-50 via-teal-50 to-emerald-50 min-h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8\">");
                out.println("    <div class=\"max-w-md w-full bg-white p-8 rounded-xl shadow-lg text-center\">");
                out.println("        <h2 class=\"mt-6 text-3xl font-extrabold text-gray-900\">Feedback Submission Failed</h2>");
                out.println("        <p class=\"mt-4 text-xl text-red-600\">Error: Feedback could not be saved.</p>");
                out.println("        <div class=\"mt-8\">");
                out.println("            <a href=\"feedback.html\" class=\"inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md shadow-sm text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 transition duration-150 ease-in-out\">Try Again</a>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (ClassNotFoundException e) {
            out.println("<p style=\"color: red;\">JDBC Driver not found: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("<p style=\"color: red;\">Database error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            // Close resources in finally block to ensure they are closed even if exceptions occur
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
