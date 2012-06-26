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

import de.unistuttgart.iste.se.pkv.dao.*;
import de.unistuttgart.iste.se.pkv.model.TBETopic;
import de.unistuttgart.iste.se.pkv.model.TBEQuestion;
import de.unistuttgart.iste.se.pkv.model.TopicBasedExam;
import de.unistuttgart.iste.se.pkv.utils.Consts;


/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/QuestionController")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RequestDispatcher questionDispatcher;

    public void init(ServletConfig config) throws ServletException {
       ServletContext context = config.getServletContext();
       questionDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/displayAllQuestions.jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Question> questions = new ArrayList<Question>();
		try {
			questions = TBEQuestion.getAllQuestions();
			request.setAttribute("allQuestions", questions);
			questionDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		String question_creation ="";
		String btn_exam_start = "";		
		String btn_create_question = "";
		if(request.getParameter("btnAddQuestion") != null) question_creation = request.getParameter("btnAddQuestion");
		if(request.getParameter("btnStartExam") != null) btn_exam_start = request.getParameter("btnStartExam");
		if(request.getParameter("btnCreateQuestion") != null) btn_create_question=request.getParameter("btnCreateQuestion");
		
		if(question_creation.equals("Add New Question"))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createQuestion.jsp");
			return;
		}
		if(btn_exam_start.equals(Consts.EXAM_GENERATE))
		{
			displayQuestions(request, response);
		}
		
		else if(btn_create_question.equals("Create"))
		{
			createQuestion(request, response);
		}
	}
	
	private void displayQuestions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		List<Question> questions_for_one_topic = new ArrayList<Question>();
		List<Question> allQuestions = new ArrayList<Question>();
		
		List<String> all_topic_id = new ArrayList<String>();
		RequestDispatcher dispatcher = null;
		String exam_id = request.getParameter("ddlExam");
		
		Exam exam = new Exam();
		try {
			exam = TopicBasedExam.getExamByID(Integer.parseInt(exam_id));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(request.getParameterValues("ddlTopic") != null)
		{
			all_topic_id.clear();
			String[] topic_id1 = request.getParameterValues("ddlTopic");
			for(int i = 0; i < topic_id1.length; i++)
				all_topic_id.add(topic_id1[i]);
		}
		
		if(exam_id.equals("-1"))
		{
			response.sendRedirect("index.jsp?isShow=1");
			return;				
		}
		else if(!exam_id.equals("-1") && request.getParameterValues("ddlTopic") == null)
		{
			List<Topic> topics = new ArrayList<Topic>();
			try 
			{				
				topics = TBETopic.getTopics(Integer.parseInt(exam_id));
				
				for(Topic topic: topics)
				{
					all_topic_id.add(topic.getID() + "");						
				}
			} 
			catch ( Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		try 
		{				
			for(int i = 0; i < all_topic_id.size(); i++)
			{			
				questions_for_one_topic = TBEQuestion.getQuestions(Integer.parseInt(exam_id), Integer.parseInt(all_topic_id.get(i)));
				for(Question question : questions_for_one_topic)
				{
					allQuestions.add(question);
				}
				questions_for_one_topic.clear();
			}
			request.setAttribute("exam", exam);
			request.setAttribute("Questions", allQuestions);
			dispatcher = request.getRequestDispatcher("de.unistuttgart.iste.se.pkv.view/DisplayTopicBasedQuestion.jsp");
			dispatcher.forward(request, response);
		} 
		catch (Exception e) 
		{		
			e.printStackTrace();
		}
	}
	
	private void createQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String topic_id = request.getParameter("ddlTopic");
		String question_name = request.getParameter("txtQuestion");
		
		if(topic_id.equals("-1") || question_name.equals(""))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createQuestion.jsp?isCreated=1");
			return;
		}
		else
		{
			try
			{
				int isCreated = TBEQuestion.saveQuestion(question_name, Integer.parseInt(topic_id));
				if(isCreated > 0)
				{
					response.sendRedirect("QuestionController?isCreated=true");
				}
				else
				{
					response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createQuestion.jsp?isCreated=false");
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
