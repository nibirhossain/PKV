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

import de.unistuttgart.iste.se.pkv.dao.Exam;
import de.unistuttgart.iste.se.pkv.dao.Question;
import de.unistuttgart.iste.se.pkv.dao.Topic;
import de.unistuttgart.iste.se.pkv.model.TBEQuestion;
import de.unistuttgart.iste.se.pkv.model.TBETopic;
import de.unistuttgart.iste.se.pkv.model.TopicBasedExam;

/**
 * Servlet implementation class ExamController
 */
@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private RequestDispatcher examDispatcher;
    private ServletContext context;
    
    public void init(ServletConfig config) throws ServletException {
       context = config.getServletContext();
       //examDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/displayAllExams.jsp");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String exam_action = "";
		String exam_id = "";
		
		if(request.getParameter("action") != null) exam_action = request.getParameter("action");
		if(request.getParameter("examID") != null) exam_id = request.getParameter("examID").trim();
		
		if(exam_action.equals("edit"))
		{
			Exam exam = new Exam();
			try {				
				exam = TopicBasedExam.getExamByID(Integer.parseInt(exam_id));				
				if(exam != null)
				{
						request.setAttribute("ExamByID", exam);
						examDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/editExam.jsp");
						examDispatcher.forward(request, response);											
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception in Exam Controller : " + e.getMessage());
				
			}
		}		
		if(exam_action.equals("delete"))
		{		
			List<Topic> topics = new ArrayList<Topic>();
			List<Question> questions = new ArrayList<Question>();
			try {
				topics = TBETopic.getTopics(Integer.parseInt(exam_id));
				if(topics != null)
				{
					for(Topic topic : topics)
					{
						questions = TBEQuestion.getQuestions(0, topic.getID());
						if(questions != null)
						{
							for(Question question : questions)
							{
								TBEQuestion.deleteQuestionByID(question.getID());
							}
						}
						TBETopic.deleteTopicByID(topic.getID());
						questions.clear();
					}
				}
				int isDeleted = TopicBasedExam.deleteExamByID(Integer.parseInt(exam_id));				
				if(isDeleted > 0)
				{
					response.sendRedirect("ExamController?isDeleted=true");										
				}
				else
				{
					response.sendRedirect("ExamController?isDeleted=false");
				}
				return;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception in Exam Controller : " + e.getMessage());
				
			}
		}
		else
		{
			List<Exam> exams = new ArrayList<Exam>();
			try {
				exams = TopicBasedExam.getExams();
				request.setAttribute("allExams", exams);
				examDispatcher = context.getRequestDispatcher("/de.unistuttgart.iste.se.pkv.view/displayAllExams.jsp");
				examDispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception in Exam Controller : " + e.getMessage());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String exam_update = "";
		String exam_cancel = "";
		String exam_save = "";
		String exam_creation = "";
		
		if(request.getParameter("btnUpdate") != null) exam_update = request.getParameter("btnUpdate");
		if(request.getParameter("btnCancel") != null) exam_cancel = request.getParameter("btnCancel");
		if(request.getParameter("ExamCreatecButton") != null) exam_save = request.getParameter("ExamCreatecButton");		
		if(request.getParameter("btnAddExam") != null) exam_creation = request.getParameter("btnAddExam");
		
		if(exam_creation.equals("Create New Exam"))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createExam.jsp");
			return;
		}
		else if(exam_update.equals("Update"))
		{
			updateExamByID(request, response);
			return;
		}
		else if(exam_cancel.equals("Cancel"))
		{
			response.sendRedirect("ExamController?isCancelled=true");
			return;
		}
		else if(exam_save.equals("Create"))
		{
			saveExam(request, response);
			return;
		}		
	}
	
	protected void saveExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String exam_name = request.getParameter("txtExam");		
		if(exam_name.equals(""))
		{
			response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createExam.jsp?isCreated=1");
			return;
		}
		else
		{
			try
			{
				int isCreated = TopicBasedExam.saveExam(exam_name);
				if(isCreated > 0)
				{
					response.sendRedirect("ExamController?isCreated=true");
				}
				else
				{
					response.sendRedirect("de.unistuttgart.iste.se.pkv.view/createExam.jsp?isCreated=false");
				}
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				System.out.println("Exception in Exam Controller : " + e.getMessage());
			} 			
		}
	}
	
	private void updateExamByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String exam_id = "";
		String exam_name = "";
		
		if(request.getParameter("txtExamID") != null) exam_id = request.getParameter("txtExamID");
		if(request.getParameter("txtEditExam") != null) exam_name = request.getParameter("txtEditExam");
	
		
		try {
			Exam exam = new Exam();
			exam = TopicBasedExam.getExamByID(Integer.parseInt(exam_id));
			exam.setName(exam_name);
			//exam.setName("Java Programming 2");
			int isUpdated = TopicBasedExam.updateExamByID(exam);
			System.out.println("Exam Name: " + exam_id);
			if(isUpdated > 0)
			{
				response.sendRedirect("ExamController?isUpdated=true");
			}
			else
			{
				response.sendRedirect("de.unistuttgart.iste.se.pkv.view/editExam.jsp?isUpdated=false");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in Exam Controller : " + e.getMessage());
		}
	}
}
