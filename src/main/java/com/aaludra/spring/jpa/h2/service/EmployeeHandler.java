package com.aaludra.spring.jpa.h2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.xml.sax.SAXException;

import com.aaludra.spring.jpa.h2.enum1.StatusEnum;
import com.aaludra.spring.jpa.h2.model.Employee;

import com.aaludra.spring.jpa.h2.repository.EmployeeRepository;
import com.aaludra.spring.jpa.h2.util.DateUtil;
import com.aaludra.spring.jpa.h2.util.Dateconvert;
import com.aaludra.spring.jpa.h2.view.EmployeeDetailView;
import com.aaludra.spring.jpa.h2.view.EmployeeInput;
import com.aaludra.spring.jpa.h2.view.EmployeeInputObjtoJson;
import com.aaludra.spring.jpa.h2.view.EmployeeInputObjtoXml;
import com.aaludra.spring.jpa.h2.view.EmployeeJsonInput;
import com.aaludra.spring.jpa.h2.view.EmployeeList;
import com.aaludra.spring.jpa.h2.view.EmployeeViewOut;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class EmployeeHandler {

	@Inject
	EmployeeRepository employeeRepository;
	 
	
	
//postmapping/create
	public EmployeeViewOut createEmployee(EmployeeDetailView employeeDetailsView) {
			Employee emp = this.buildEmployee(employeeDetailsView);
	 //employeeRepository.save(emp); 
	 EmployeeViewOut employeeViewout= this.buildEmployee( employeeRepository.save(emp));
	 return employeeViewout;
	}
	

//deleteAll
	public ResponseEntity<HttpStatus> deleteAll() {
		employeeRepository.deleteAll();
		return null;
	}

//putmapping/update
	public EmployeeViewOut updateEmployee(int id, EmployeeDetailView employeeDetailsView) {
		
		Optional<Employee> employeeData = employeeRepository.findById(id);
	
		if (employeeData.isPresent()) {
			Employee employee = this.buildEmployee(employeeDetailsView);
			employee.setId(id);
			EmployeeViewOut employeeViewout= this.buildEmployee(employee);
			return  employeeViewout;
		}
		return null;
	
}
//deletemappingById
	public Object deleteEmployee(int id) {
		employeeRepository.deleteById(id);
		return null;
	}

//putmapping setmethod
	public Optional<EmployeeViewOut> getEmployeeById(int id) {
	    Optional<Employee> employeeData = employeeRepository.findById(id);
		// employeeRepository.findById(id);
	    
		EmployeeViewOut employeeViewout= this.buildEmployee(employeeData.get());
		
	return Optional.of(employeeViewout);
			
	}


	


//getmapping
	public List<EmployeeViewOut> getAllEmployee() {
		List<Employee> listEmployee = employeeRepository.findAll();
	//	employeeRepository.findAll().forEach(listEmployee::add);
		List<EmployeeViewOut> empviewout = new ArrayList();
		for(Employee emp : listEmployee) {
			EmployeeViewOut empview = this.buildEmployee(emp);
			empviewout.add(empview);
		}
		return empviewout;
	}

//putmapping
	private Employee buildEmployee(EmployeeDetailView employeeDetailsView) {
		
		Employee employeeObj = new Employee();
		//for(LogSchemaFieldModel f : fields)
			
		employeeObj.setEmpName(employeeDetailsView.getEmpName());
		employeeObj.setEmpId(employeeDetailsView.getEmpId());
		employeeObj.setEmpPhonenumber(Long.parseLong(employeeDetailsView.getEmpPhonenumber()));
		employeeObj.setEmpAddress(employeeDetailsView.getEmpAddress());
		employeeObj.setEmpDoj(DateUtil.convertStringToDate(employeeDetailsView.getEmpDoj()));
		employeeObj.setStatus("Active");
		employeeObj.setCreatedBy("Admin");
		employeeObj.setCreatedDate(DateUtil.getCurrentTimeStamp());
		employeeObj.setUpdatedBy("Admin");
		employeeObj.setUpdatedDate(DateUtil.getCurrentTimeStamp());
		employeeObj.setEmpGrade(employeeDetailsView.getEmpGrade());
		employeeObj.setSalary(Integer.parseInt(employeeDetailsView.getSalary()));
		return employeeObj;
		
	
	}

	private EmployeeViewOut buildEmployee(Employee emp) {
		EmployeeViewOut empview = new EmployeeViewOut();
		empview.setEmpName(emp.getEmpName());
		empview.setEmpId(emp.getEmpId());  
		empview.setEmpPhonenumber(Long.toString(emp.getEmpPhonenumber()));
		empview.setEmpAddress(emp.getEmpAddress()); 
		empview.setEmpDoj(Dateconvert.convertStringToDate(emp.getEmpDoj()));
		empview.setStatus(emp.getStatus()); 
		empview.setCreatedBy( emp.getCreatedBy());
	    empview.setCreatedDate(emp.getCreatedDate().toString());  
		empview.setUpdatedBy(emp.getUpdatedBy()); 
		empview.setUpdatedDate(emp.getUpdatedDate().toString()); 
		empview.setEmpGrade(emp.getEmpGrade()); 
		empview.setSalary(Integer.toString (emp.getSalary())); 

	return empview;
}	
	public void testXmlToObject() throws JAXBException, FileNotFoundException {
		File file = new File("D:\\git\\SpringBootNew\\Spring_Boot_H2DB\\employeeInput.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeInput.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		EmployeeInput que = (EmployeeInput) jaxbUnmarshaller.unmarshal(file);

		System.out.println(que.getId());
		List<EmployeeList> elist = new ArrayList<>();
		List<EmployeeList> list = que.getEmployeelist();

		for (EmployeeList emp : list) {
			System.out.println(emp.getEmpName() + " " + emp.getEmpId() + " " + emp.getEmpPhonenumber() + " "
					+ emp.getEmpAddress() + " " + emp.getEmpDoj() + " " + emp.getStatus() + " " + emp.getCreatedBy()
					+ " " + DateUtil.getCurrentTimeStamp() + " " + emp.getUpdatedBy() + " "
					+ DateUtil.getCurrentTimeStamp() + " " + emp.getEmpGrade() + " " + emp.getSalary());
			elist.add(emp);

			employeeRepository.save(new Employee(emp.getEmpName(), emp.getEmpId(),
					Long.valueOf(emp.getEmpPhonenumber()), emp.getEmpAddress(),Date.valueOf("2021-12-29"), StatusEnum.Active.name(), "Admin",
					DateUtil.getCurrentTimeStamp(), "Admin", DateUtil.getCurrentTimeStamp(), emp.getEmpGrade(),
					Integer.parseInt(emp.getSalary())));

		}

	}
	public void testJsonToObject1() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		EmployeeJsonInput employeeview = objectMapper.readValue(
				new File("D:\\git\\SpringBootNew\\Spring_Boot_H2DB\\employeefile.json"), EmployeeJsonInput.class);
		List<EmployeeList> asd = new ArrayList();
		List<EmployeeList> jsonlist = employeeview.getEmployeejsonlist();

		System.out.println("asd"+jsonlist.size());
		
		for (EmployeeList emp : jsonlist) {
			System.out.println(emp.getEmpName() + " " + emp.getEmpId() + " " + emp.getEmpPhonenumber() + " "
					+ emp.getEmpAddress() + " " + emp.getEmpDoj() + " " + emp.getStatus() + " " + emp.getCreatedBy()
					+ " " + DateUtil.getCurrentTimeStamp() + " " + emp.getUpdatedBy() + " "
					+ DateUtil.getCurrentTimeStamp() + " " + emp.getEmpGrade() + " " + emp.getSalary());
			asd.add(emp);

			
			//Employee empp = new Employee();
			//empp.setStatus(emp.getStatus());
			
			
			//asd.add(empp);
			employeeRepository.save(new Employee(emp.getEmpName(), emp.getEmpId(),
					Long.valueOf(emp.getEmpPhonenumber()), emp.getEmpAddress(), Date.valueOf("2021-12-29"), StatusEnum.Active.name(), "Admin",
					DateUtil.getCurrentTimeStamp(), "Admin", DateUtil.getCurrentTimeStamp(),emp.getEmpGrade(),
					Integer.parseInt(emp.getSalary())));
		}

		//employeeRepository.saveAll(asd);
	}


	public void  testObjectToXml() throws JAXBException{
	
		EmployeeInputObjtoXml listEmployee = new EmployeeInputObjtoXml(getAllEmployee());
        JAXBContext empobj = JAXBContext.newInstance(EmployeeInputObjtoXml.class);
        Marshaller jaxbmarshaller = empobj.createMarshaller();
        jaxbmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        
        jaxbmarshaller.marshal( listEmployee, new File("employeeinputobjtoxml.xml"));
         
		}


	public void testObjectToJson() throws JsonGenerationException, JsonMappingException, IOException {
		List<EmployeeViewOut> listEmployee=this.getAllEmployee();
		File jsonfile = new File ("D:\\git\\SpringBootNew\\Spring_Boot_H2DB\\employeeInputObjtoJson.json");
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.writeValue(jsonfile, listEmployee);
	}   
         
	}
	
	/*
	 *   JAXBContext context;
    BufferedWriter writer = null;
    writer = new BufferedWriter(new FileWriter(outputFile));
    context = JAXBContext.newInstance(ClassName.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    m.marshal(classData, writer);
    writer.close();	
	 */
	


