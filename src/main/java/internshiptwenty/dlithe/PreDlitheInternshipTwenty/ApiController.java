package internshiptwenty.dlithe.PreDlitheInternshipTwenty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class ApiController 
{
	@Autowired
	CandidateService service;
	List<Candidates> hey;
	@GetMapping("/every")
	public List<Candidates> every()
	{
		return service.listAll();
	}
	@PostMapping("/fresh")
	public Candidates add(@RequestBody Candidates candidates)
	{
		return service.newone(candidates);
	}
	@PutMapping("/alter")
	public Candidates change(@RequestBody Candidates candidates)
	{
		return service.update(candidates);
	}
	@DeleteMapping("/remove")
	public String del(@RequestBody Candidates candidates)
	{
		service.remove(candidates);
		return "Cndidate Deleted";
	}
	@GetMapping("/oneById/{id}")
	public Candidates fetchOne(@PathVariable("id") Long id)
	{
		return service.readOne(id).orElse(new Candidates());
	}
	@GetMapping("/find/{constrain}/{data}")
	public List<Candidates> read(@PathVariable("constrain") String constrain,@PathVariable("data") String data)
	{
		if(constrain.equalsIgnoreCase("regno"))
		{
			hey=new Vector<Candidates>();
			hey.add(service.readOne(Long.parseLong(data)).orElse(new Candidates()));
		}
		else if(constrain.equalsIgnoreCase("department"))
		{
			hey=service.curd.findAllByDepartment(data);
		}
		else if(constrain.equalsIgnoreCase("career"))
		{
			hey=service.curd.findAllByCareer(data);
		}
		else if(constrain.equalsIgnoreCase("status"))
		{
			hey=service.curd.findAllByStatus(data);
		}
		return hey;
	}
	@GetMapping("/find/{constrain}/{data}/report/{format}")
	public String report(@PathVariable("constrain") String constrain,@PathVariable("data") String data,@PathVariable("format") String format)
	{
		if(constrain.equalsIgnoreCase("regno"))
		{
			hey=new Vector<Candidates>();
			hey.add(service.readOne(Long.parseLong(data)).orElse(new Candidates()));
		}
		else if(constrain.equalsIgnoreCase("department"))
		{
			hey=service.curd.findAllByDepartment(data);
		}
		else if(constrain.equalsIgnoreCase("career"))
		{
			hey=service.curd.findAllByCareer(data);
		}
		else if(constrain.equalsIgnoreCase("status"))
		{
			hey=service.curd.findAllByStatus(data);
		}
		File fgen=null;
		try
		{
			File file = ResourceUtils.getFile("classpath:newcam.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hey);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "Razak Mohamed");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (format.equalsIgnoreCase("html")) {
	        	fgen=new File("candidate.html");
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, fgen.getAbsolutePath());
	        }
	        if (format.equalsIgnoreCase("pdf")) {
	        	fgen=new File("candidate.pdf");
	            JasperExportManager.exportReportToPdfFile(jasperPrint, fgen.getAbsolutePath());
	        }
	        /*JasperViewer view=new JasperViewer(jasperPrint,false);
			view.setVisible(true);*/
	        
	        /*JasperExportManager.exportReportToPdfFile(jasperPrint, fgen.getAbsolutePath());
	        System.out.println("REport generated @ "+fgen.getAbsolutePath());
	        String hai="REport generated @ "+fgen.getAbsolutePath();*/
		}
		catch(JRException j)
		{j.printStackTrace();} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Report generated @ "+fgen.getAbsolutePath();
	}
}
