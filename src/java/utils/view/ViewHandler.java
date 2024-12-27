package utils.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ViewHandler provides methods for handling the forwarding of requests to different views in a servlet.
 * It helps in adding a message to the HTTP request before forwarding it to another view page.
 */
public class ViewHandler {
    
    /**
     * Forwards the request to a new view and includes a message with the request.
     * 
     * @param request The HttpServletRequest object containing the information of the incoming client request.
     * @param response The HttpServletResponse object used to send the response back to the client.
     * @param path The path to the view (JSP, HTML, etc.) to which the request will be forwarded.
     * @param message The message to be included with the request.
     * @throws ServletException If an error occurs during the request processing.
     * @throws IOException If an error occurs while reading or writing data.
     */
    public static void forwardWithMessage(HttpServletRequest request, HttpServletResponse response, String path, String message) throws ServletException, IOException {
        // Set the "message" attribute in the request so it can be accessed by the view.
        request.setAttribute("message", message);
        
        // Forward the request to the specified view (JSP, HTML, etc.).
        request.getRequestDispatcher(path).forward(request, response);
    }
}
