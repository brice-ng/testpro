package com.pro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pro.model.Users;
import com.pro.service.ReadFileServicelp;


@Controller
public class ReadFileController {

	@Autowired private ReadFileServicelp readFileService;
	
	
	@GetMapping("/users")
	private String home(Model model) {
		
		 
		List <Users> usersList = readFileService.findAll();
		
		model.addAttribute("users", usersList);
		
		return "users";
	}
	
	
	
	@PostMapping("/fileupload")
	public String uploadFile(@ModelAttribute Users users,RedirectAttributes redirectAttributes) {
		
		boolean isFlag=readFileService.saveDataFromUploadfile(users.getFile());
		
		if(isFlag) {
			
			String message ="File Upload Successfully";
			redirectAttributes.addFlashAttribute("successmessage",message);
		
		}else {
			String message ="File Upload not done ,please try again !";
			
			redirectAttributes.addFlashAttribute("errormessage",message);
			
		}

		
		
		
		return "redirect:/users";
	}
	
	
	@RequestMapping(value="users/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String Delete(Integer id) {
		
		readFileService.deleteAll();
		
		return"redirect:/users";
		
	} 
	
}
