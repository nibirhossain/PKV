package de.unistuttgart.iste.se.pkv.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.unistuttgart.iste.se.pkv.dao.User;
import de.unistuttgart.iste.se.pkv.model.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private RequestDispatcher userDispatcher;

    public void init(ServletConfig config) throws ServletException {
       ServletContext context = config.getServletContext();
       userDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/displayAllUsers.jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<User> users = new ArrayList<User>();
		try {
			users = UserService.getAllUsers();
			request.setAttribute("allUsers", users);
			userDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String user_addition = "";
		String user_creation = "";		
		String user_login = ""; 
		if(request.getParameter("btnAddUser") != null) user_addition = request.getParameter("btnAddUser");
		if(request.getParameter("btnUserCreation") != null) user_creation = request.getParameter("btnUserCreation");
		if(request.getParameter("btnUserLogin") != null) user_login = request.getParameter("btnUserLogin");
		
		if(user_login.equals("Login"))
		{
			String username, password;
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			// Call LoginService class
			
			try 
			{
				user = UserService.authenticateUser(user);
				if(user.getUsername() != null && user.getPassword() != null && user.getUsername().equals(username) && user.getPassword().equals(password))
				{
					HttpSession session = request.getSession(true);
					session.setAttribute("CurrentUser", user);
					session.setMaxInactiveInterval(2000);
					response.sendRedirect("de.unistuttgart.iste.se.pkv.view/loginSuccess.jsp");
				}				
				else
				{
					response.sendRedirect("login.jsp?isLogin=1");
					return;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(user_addition.equals("Create New Admin"))
		{
			response.sendRedirect("register.jsp");
			return;
		}
		else if(user_creation.equals("Create"))
		{
			String username = request.getParameter("txtUsername");
			String password = request.getParameter("txtPassword");
			String retype_password = request.getParameter("txtRetypePassword");
			String first_name = request.getParameter("txtFirstName");
			String last_name = request.getParameter("txtLastName");
		
			if(username.equals("") || password.equals("") || retype_password.equals("") || first_name.equals("") || last_name.equals(""))
			{
				response.sendRedirect("register.jsp?isCreated=1");
				return;
			}
			else
			{
				if(!password.equals(retype_password))
				{
					response.sendRedirect("register.jsp?isCreated=2");
					return;
				}
				try
				{
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setFirstName(first_name);
					user.setLastName(last_name);
					int isCreated = UserService.saveUser(user);					
					if(isCreated > 0)
					{
						//response.sendRedirect("register.jsp?isCreated=true");
						response.sendRedirect("UserController?isCreated=true");
					}
					else
					{
						response.sendRedirect("register.jsp?isCreated=false");
					}
				} 
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 			
			}		
		}
	}	
}
