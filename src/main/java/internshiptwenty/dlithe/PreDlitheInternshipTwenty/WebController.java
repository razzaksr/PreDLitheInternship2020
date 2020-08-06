package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Controller
public class WebController 
{
	@Autowired
	CandidateService service;
	List<Candidates> all;
	String reportFormat;
	HttpSession session;
	HttpServletRequest request;
	@GetMapping("/")
	public ModelAndView sign()
	{
		return new ModelAndView("index");
	}
	@GetMapping("/home")
	public ModelAndView hme()
	{
		if(session.getAttribute("user")!=null) {return new ModelAndView("home");}
		else {return new ModelAndView("index");}
	}
	@GetMapping("/logout")
	public ModelAndView out()
	{
		session.removeAttribute("user");//session.invalidate();
		//session=request.getSession();
		session.setAttribute("user", null);
		return new ModelAndView("index").addObject("msg", "LoggedOutSuccessfully");
	}
	@PostMapping("/log")
	public ModelAndView home(@RequestParam("user") String user,@RequestParam("pass") String pass,HttpServletRequest request)
	{
		if(user.equalsIgnoreCase("razak")&&pass.equalsIgnoreCase("mohamed"))
		{
			session=request.getSession();
			session.setAttribute("user", user);
			return new ModelAndView("home");
		}
		else {return new ModelAndView("index").addObject("msg", "Invalid SignIn");}
	}
	@GetMapping("/list")
	public ModelAndView all()
	{
		if(session.getAttribute("user")!=null) 
		{
			all=service.listAll();
			/*List<Candidate> all=new Vector<Candidate>();
			service.listAll().forEach(all::add);*/
			return new ModelAndView("all").addObject("all", all);
		}
		else {return new ModelAndView("index");}		
	}
	@GetMapping("/new")
	public ModelAndView enroll()
	{
		if(session.getAttribute("user")!=null) {return new ModelAndView("fill");}
		else {return new ModelAndView("index");}
	}
	@PostMapping("/add")
	public ModelAndView enrolled(@Valid Candidates candidates,BindingResult bind)
	{
		if(session.getAttribute("user")!=null) 
		{
			if(bind.hasErrors()) {return new ModelAndView("fill");}
			service.newone(candidates);
			return new ModelAndView("fill").addObject("msg", "New Candidate added");
		}
		else {return new ModelAndView("index");}
	}
	@GetMapping("/edit")
	public ModelAndView get(@RequestParam("id") Long id)
	{
		if(session.getAttribute("user")!=null) {
		Candidates cand=service.readOne(id).orElse(new Candidates());
		return new ModelAndView("editable").addObject("got", cand);
		}else {return new ModelAndView("index");}
	}
	@PostMapping("/update")
	public ModelAndView update(Candidates candidates)
	{
		 if(session.getAttribute("user")!=null) 
		 {
			 service.update(candidates);
			 return all();
		 }
		 else {return new ModelAndView("index");}
	}
	@GetMapping("/delete")
	public ModelAndView erase(@RequestParam("id") Long id)
	{
		if(session.getAttribute("user")!=null)
		{
			service.remove(service.readOne(id).orElse(new Candidates()));
			return all();
		}
		else {return new ModelAndView("index");}
	}
	@GetMapping("/find")
	public ModelAndView finding()
	{
		if(session.getAttribute("user")!=null)
		{
			return new ModelAndView("search");
		}
		else {return new ModelAndView("index");}
	}
	@PostMapping("/read")
	public ModelAndView read(@RequestParam("regno") String regno,@RequestParam("department") String department,@RequestParam("career") String career,@RequestParam("status") String status)
	{
		if(session.getAttribute("user")!=null)
		{
			all=new Vector<Candidates>();
			if(!regno.equalsIgnoreCase("")&&department.equals("Select Any Department")&&career.equals("Select Any Career")&&status.equals("Select Any Status"))
			{
				all.add(service.readOne(Long.parseLong(regno)).orElse(new Candidates()));
			}
			else if(regno.equalsIgnoreCase("")&&!department.equals("Select Any Department")&&career.equals("Select Any Career")&&status.equals("Select Any Status"))
			{
				service.curd.findAllByDepartment(department).forEach(all::add);
			}
			else if(regno.equalsIgnoreCase("")&&department.equals("Select Any Department")&&!career.equals("Select Any Career")&&status.equals("Select Any Status"))
			{
				service.curd.findAllByCareer(career).forEach(all::add);
			}
			else if(regno.equalsIgnoreCase("")&&department.equals("Select Any Department")&&career.equals("Select Any Career")&&!status.equals("Select Any Status"))
			{
				service.curd.findAllByStatus(status).forEach(all::add);
			}
			return new ModelAndView("all").addObject("all", all);
		}
		else {return new ModelAndView("index");}
	}
	@RequestMapping("/report")
	public ModelAndView report()
	{
		if(session.getAttribute("user")!=null)
		{
			ModelAndView m=new ModelAndView("all");
			try
			{
				File file = ResourceUtils.getFile("classpath:newcam.jrxml");
		        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		        System.out.println("Before assign all: "+all);
		        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(all);
		        Map<String, Object> parameters = new HashMap<>();
		        parameters.put("createdBy", "Razak Mohamed");
		        System.out.println("Before fill all: "+all);
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		        System.out.println("After Fill all: "+all);
		        /*if (reportFormat.equalsIgnoreCase("html")) {
		            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
		        }
		        if (reportFormat.equalsIgnoreCase("pdf")) {
		            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
		        }*/
		        /*JasperViewer view=new JasperViewer(jasperPrint,false);
				view.setVisible(true);*/
		        File fgen=new File("candidate.pdf");
		        JasperExportManager.exportReportToPdfFile(jasperPrint, fgen.getAbsolutePath());
		        System.out.println("REport generated @ "+fgen.getAbsolutePath());
		        String hai="REport generated @ "+fgen.getAbsolutePath();
		        m.addObject("msg", hai);
			}
			catch(JRException j)
			{j.printStackTrace();} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.addObject("all", all);
			return m;
		}
		else {return new ModelAndView("index");}
	}
}
