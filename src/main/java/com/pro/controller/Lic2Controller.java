package com.pro.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

import com.pro.model.Licence2;
import com.pro.service.ReadLic2Service2;


@Controller
public class Lic2Controller {

	@Autowired private ReadLic2Service2 readLic2Service2 ;
	
	@GetMapping("/licence2")
	private String home(Model model) {
		
		
		List <Licence2> usersList = readLic2Service2.findAll();
		 
		try {
			
		 Random rand = new Random();
		 Licence2 randomElement = usersList.get(rand.nextInt(usersList.size()));
		
		}catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		model.addAttribute("users", usersList);
		model.addAttribute("numberl2", usersList.size());
		 
				
		return "lic2";
		
		
	}
	
	@PostMapping("/lic2fileupload")
	public String uploadFile(@ModelAttribute Licence2 users,RedirectAttributes redirectAttributes) {
		
		boolean isFlag=readLic2Service2.saveDataFromUploadfile(users.getFile());
		
		if(isFlag) {
			
			String message ="File Upload Successfully";
			redirectAttributes.addFlashAttribute("successmessage",message);
		
		}else {
			String message ="File Upload not done ,please try again !";
			
			redirectAttributes.addFlashAttribute("errormessage",message);
			
		}
		
		
		return "redirect:/licence2";
	}
	
	@RequestMapping("licence2/findById")
	@ResponseBody
	public Optional<Licence2> findById(Long id) {
		
		return	readLic2Service2.findById(id);
		
	}
	
	
	@RequestMapping(value="licence2By/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String Delete(Long id) {
		
		readLic2Service2.delete(id);
		
		return"redirect:/licence2";
	}
	
	
	
	@RequestMapping(value="lic2/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String Delete() {
		
		readLic2Service2.deleteAll();
		
		return"redirect:/licence2";
		
	} 
	
}
