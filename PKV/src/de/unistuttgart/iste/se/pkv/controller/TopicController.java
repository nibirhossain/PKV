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

import de.unistuttgart.iste.se.pkv.dao.Topic;
import de.unistuttgart.iste.se.pkv.model.TBETopic;


/**
 * Servlet implementation class TopicController
 */
@WebServlet("/TopicController")
public class TopicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RequestDispatcher topicDispatcher;

    public void init(ServletConfig config) throws ServletException {
       ServletContext context = config.getServletContext();
       topicDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/displayAllTopics.jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		List<Topic> topics = new ArrayList<Topic>();
		try {
			topics = TBETopic.getAllTopics();
			request.setAttribute("allTopics", topics);
			topicDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String topic_creation = "";
		String exam_id = "";
		String topic_name = "";
		if(request.getParameter("btnAddTopic") != null) topic_creation = request.getParameter("btnAddTopic"); 
		if(request.getParameter("ddlExam") != null) exam_id =request.getParameter("ddlExam"); 
		if(request.getParameter("txtTopic") != null) topic_name = request.getParameter("txtTopic");
		if(topic_creation.equals("Create New Topic"))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createTopic.jsp");
			return;
		}
		if(exam_id.equals("0") || topic_name.equals(""))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createTopic.jsp?isCreated=1");
			return;
		}
		else
		{
			try
			{
				int isCreated = TBETopic.saveTopic(Integer.parseInt(exam_id), topic_name);
				if(isCreated > 0)
				{
					//response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createTopic.jsp?isCreated=true");
					response.sendRedirect("TopicController?isCreated=true");
				}
				else
				{
					response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createTopic.jsp?isCreated=false");
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
