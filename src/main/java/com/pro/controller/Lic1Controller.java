package com.pro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pro.model.Licence1;
import com.pro.service.ReadLic1Service;


@Controller
public class Lic1Controller {

	@Autowired private ReadLic1Service readLic1Service;
	
	@GetMapping("/licence1")
	private String home(Model model) {
		
		 
		List <Licence1> usersList = readLic1Service.findAll();
		
		model.addAttribute("users", usersList);
		model.addAttribute("numberl1", usersList.size());

				
		return "lic1";
		
	}
	
	@RequestMapping("licence1/findById")
	@ResponseBody
	public Optional<Licence1> findById(Long id) {
		
		
		
		return	readLic1Service.findById(id);
		
		
	}
	
	
	
	@RequestMapping(value="licence1/deleteBy", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String Delete(Long id) {
		
		readLic1Service.delete(id);
		
		return"redirect:/licence1";
	}
	
	
	
	@PostMapping("/lic1fileupload")
	public String uploadFile(@ModelAttribute Licence1 users,RedirectAttributes redirectAttributes) {
		
		boolean isFlag=readLic1Service.saveDataFromUploadfile(users.getFile());
		
		if(isFlag) {
			
			String message ="File Upload Successfully";
			redirectAttributes.addFlashAttribute("successmessage",message);
		
		}else {
			String message ="File Upload not done ,please try again !";
			
			redirectAttributes.addFlashAttribute("errormessage",message);
			
		}
		
		
		return "redirect:/licence1";
	}
	
	
	@RequestMapping(value="licence1/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String Delete() {
		
		readLic1Service.deleteAll();
		
		return"redirect:/licence1";
		
	}
	
	
	
}
