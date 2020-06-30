package com.telusko.springmvcboot;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.telusko.springmvcboot.dao.AlienRepo;
import com.telusko.springmvcboot.modal.Alien;

/**
 * To make a controller just put annotation to the Class. The front
 * Controller(Servlet Dispatcher) send the request to this controller. No need
 * of Configuration is required in Spring Boot for that.
 */
@Controller
public class HomeController {

	@Autowired
	AlienRepo repo;

	/**
	 * This method is called with /(slash) in URL. Since the Dispather is
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
	@RequestMapping(value = "addAlien1", method = RequestMethod.POST)
	public String addAlien1(@ModelAttribute("a1") Alien a) {

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

	/**
	 * Note:By default the return statement returns the name of the page always. So
	 * that's why we made a page called showAliens
	 */
	@GetMapping("getAliens1")
	public String getAlien1(Model m) {
		List<Alien> aliens = Arrays.asList(new Alien(61, "XYZ"), new Alien(32, "ABC"));

		m.addAttribute("aliens", aliens);

		return "showAliens";
	}

	/** #### METHODS USED FOR DATABASE JPA #### */
	/**
	 * http://localhost/getAliens
	 * 
	 * gives the all records in the table.
	 */
	@GetMapping("getAliens")
	public String getAliens(Model m) {

		m.addAttribute("aliens", repo.findAll());

		return "showAliens";
	}

	/** This method fetch the single record with matched Id */
	@GetMapping("getAlien")
	public String getAlien(@RequestParam("aid") int aid, Model m) {

		m.addAttribute("aliens", repo.getOne(aid));

		return "showAliens";
	}

	/** This method adds new record in data base */
	// Here Model m parameter, i am taking just to show message on resul.jsp page.
	// otherwise there is no need of it.
	@PostMapping(value = "addAlien")
	public String addAlien(@ModelAttribute Alien a, Model m) {

		repo.save(a);
		m.addAttribute("result", a + " saved into Database.");
		return "result";
	}

	/** This method removes recored with matched Id */
	@GetMapping("deleteAlien")
	// Here Model m parameter, i am taking just to show message on resul.jsp page.
	// otherwise there is no need of it.
	public String delete(@RequestParam("aid") int aid, Model m) {
		repo.deleteById(aid);
		m.addAttribute("result", aid + " Deleted from Database.");
		return "result";
	}

	/** This method fetch the record with matched name */
	@GetMapping("getAlienByName")
	public String getAlienByName(@RequestParam("aname") String aname, Model m) {

		m.addAttribute("aliens", repo.findByAname(aname));
		/*
		 * m.addAttribute("aliens", repo.findByAnameOrderByAid(aname));
		 * m.addAttribute("aliens", repo.findByAnameOrderByAidDesc(aname));
		 * 
		 */
		
		/*Using Query 
		 * m.addAttribute("aliens", repo.find(aname));
		 */
		return "showAliens";
	}

}
