package com.telusko.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telusko.springmvc.dao.AlienDao;
import com.telusko.springmvc.model.Alien;

/**
 * To make a controller just put annotation to the Class. The front
 * Controller(Servlet Dispatcher) send the request to this controller. No need
 * of Configuration is required in Spring Boot for that.
 */

@Controller
public class HomeController {

	/**
	 * Creates AlienDao object and fetch the data
	 */

	@Autowired
	private AlienDao dao;

	/**
	 * This method is called with /(slash) in URL. Since the Dispatcher is
	 * responsible to call the jsp, so we just have to return the jsp in return to
	 * call the jsp page.
	 */
	@RequestMapping("/")
	public String home() {

		System.out.println("Home page requested..");
		return "index";
	}

	// Using Servlet way, where we use HttpservletRequest and HtttpServeletResponse
	// earlier in servlet, so here spring gives us the object of HttpServletRequest
	// by IOC which inject the object in tomcat so we can use it.
	@RequestMapping("add1")
	public String add1(HttpServletRequest req) {

		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));

		int num3 = i + j;
		/*
		 * now how to send this num3 to result.jsp, we have multiple option like in
		 * session or request dispatcher.
		 */
		// we are putting data in session
		HttpSession session = req.getSession();
		session.setAttribute("num3", num3);

		// and we can get this num3 value in result.jsp using java code getting from
		// session or using jstl(JavaServer Pages Standard Tag Library)
		return "result.jsp";
	}

	// the above add1 method is using servlet but we want using spring. so here we
	// have done it.

	@RequestMapping("add2")
	public String add2(@RequestParam("num1") int i, @RequestParam("num2") int j, HttpSession session) {

		int num3 = i + j;
		/*
		 * now how to send this num3 to result.jsp, we have multiple option like in
		 * session or request dispatcher.
		 */
		// we are putting data in session
		session.setAttribute("num3", num3);

		return "result.jsp";
	}

	// Now from above we want to remove that httpSession object also. so for that we
	// use a class called ModelAndView of spring framework.
	/** By using ModelAndView class */

	@RequestMapping("add3")
	public ModelAndView add3(@RequestParam("num1") int i, @RequestParam("num2") int j) {
		ModelAndView mv = new ModelAndView();

		// we are putting view in mv object through setViewName() method.

		// here we have removed .jsp extension of the files coz we have created a new
		// folder called
		// views inside webapp and put jsp files there so that it should not be public
		// and not
		// accessible to the world. because if it is inside webapp folder it is
		// accessible to the world that's why we made this. also we can put it inside
		// private folder web-inf but better is to make new folder for views

		/**
		 * we also put properties for that inside application.properties file in a key
		 * value pair, through which it will be secured and no will access them without
		 * path. we have defined path in prefix and extension in suffix.
		 * 1.)spring.mvc.view.prefix=/views/ 2.)spring.mvc.view.suffix=.jsp
		 * 
		 * we have different properties like this like for logging and many more.
		 */

		mv.setViewName("result");
		int num3 = i + j;
		// we are putting data in mv object through addObject() method.
		mv.addObject("num3", num3);

		return mv;
	}

	/** By using ModelMap class */
	// Instead of ModelAndView , we can use Model and ModelMap classes also.
	// ModelMap supports feature of map.
	@RequestMapping("add4")
	public String add4(@RequestParam("num1") int i, @RequestParam("num2") int j, ModelMap m) {
		int num3 = i + j;
		m.addAttribute("num3", num3);
		return "result";
	}

	/** By using Model class */
	@RequestMapping("add")
	public String add(@RequestParam("num1") int i, @RequestParam("num2") int j, Model m) {
		int num3 = i + j;
		m.addAttribute("num3", num3);
		return "result";
	}

	@RequestMapping("addAlien1")
	public String addAlien1(@RequestParam("aid") int aid, @RequestParam("aname") String aname, Model m) {
		Alien a = new Alien();
		a.setAid(aid);
		a.setAname(aname);

		m.addAttribute("alien", a);// then name must be same as we are called in jsp
		return "result";
	}

	/**
	 * We are can directly assign the value of input to our modal Alien using modal
	 * attribute.
	 */
	@RequestMapping("addAlien2")
	public String addAlien2(@RequestParam("aid") int aid, @RequestParam("aname") String aname, Model m) {
		Alien a = new Alien();
		a.setAid(aid);
		a.setAname(aname);

		m.addAttribute("alien", a);// then name must be same as we are called in jsp
		return "result";
	}

	/**
	 * ModalAttribute annotation is also responsible to add data in Model, so we
	 * don't need model object here.
	 */

	@RequestMapping("addAlien3")
	public String addAlien3(@ModelAttribute("a1") Alien a) {

		return "result";
	}

	/**
	 * ModelAttribute annotation at method level. Before calling any requestMapping
	 * method this modelattribute annotation method is called and it assigns the
	 * name, this is used when we want something common at our jsp page.
	 */
	@ModelAttribute
	public void modelData(Model m) {
		m.addAttribute("name", "Aliens");
	}

	/** #### METHODS USED FOR HIBERNATES #### */
	/**
	 * This method is using Hibernate to fetch list of the data from table alien, we
	 * have created in telusko database in mysql. we can get like -
	 * http://localhost/springMVC/getAliens
	 */
	@GetMapping("getAliens")
	public String getAliens(Model m) {

		m.addAttribute("results", dao.getAliens());
		return "showAliens";
	}

	/** This method is getting value from user and saving the data in database */
	@RequestMapping("addAlien")
	public String addAlien(@ModelAttribute("results") Alien a) {
		dao.addAlien(a);
		return "showAliens";
	}

	/** This method fetch single record from database with matched Id. */
	@GetMapping("getAlien")
	public String getAlien(@RequestParam int aid, Model m) {

		m.addAttribute("result", dao.getAlien(aid));
		return "showAliens";
	}

	/** this method deletes value from database with matched Alien Id. */
	@GetMapping("deleteAlien")
	public String deleteAlien(@RequestParam int aid, Model m) {
		dao.deleteAlien(aid);
		m.addAttribute("deletedValue", aid + " has been deleted!");
		return "showAliens";
	}
}
