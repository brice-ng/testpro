 package com.pro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.model.Licence1;
import com.pro.model.Licence2;
import com.pro.service.ReadLic1Service;
import com.pro.service.ReadLic2Service2;

import javassist.bytecode.stackmap.BasicBlock.Catch;




/**
 * @author Brice
 *
 */
@Controller  
public class ChoicesController {
	
		String val= "";
		String valL2= "";
		
		String v2= "";
		private int index =0;
		@Autowired private ReadLic1Service readLic1Service;
		@Autowired private ReadLic2Service2 readLic2Service2 ;

		List<Licence1> list1 = new ArrayList<Licence1>();
		List<Licence2> list2 = new ArrayList<Licence2>();
		List<String> vueL2 = new ArrayList<String>();
		List<String> vueL1 = new ArrayList<String>();
		
		List<String> VuL2 = new ArrayList<String>();
		List<String> VuL1 = new ArrayList<String>();
	
	@GetMapping("/choice")
	public String getChoice(Model model) {
		
		List<Licence1> visualList1 = readLic1Service.findAll();

		

		model.addAttribute("vueL2", vueL2);
	    model.addAttribute("vueL1", vueL1);
		model.addAttribute("users", list1);
		model.addAttribute("val", val);
		model.addAttribute("v2", v2);
		model.addAttribute("visualList1", visualList1);
		
 
		
		return "ioa/modal";
	}
	
	
	
	  @GetMapping("/choice/vue") 
	  public String getlist(Model model) {
	  
	  List<Licence1> visualList1 = readLic1Service.findAll();
	  
		
	  //on actualise le box comportant Licence1&Licence2
	  
	  vueL1 = VuL1;
	  vueL2 = VuL2;  
	  
	  	  
	  return "redirect:/choice"; 
	  
	  }
	 
	
	
	@GetMapping("/choice/try")
	public String getChoi(Model model) {
		
		
		List <Licence1> usersList = readLic1Service.findAll();

		//
		if(!usersList.isEmpty()) {
			
			Licence1 rand = getcharger(usersList);
			
			list1.add(rand);
			//variable pour etre affiché dans le modal de la vue
			 val = rand.getFirstname()+" "+rand.getLastname();
			 VuL1.add(rand.getFirstname());
			//model.addAttribute("val", val);
			 
			List<Licence2> list2 = readLic2Service2.findAll();
			
			
			if(!usersList.isEmpty()) {
				
				readLic1Service.delete(rand.getId());			

			}
				
			//condition pour list licence 2/
			
			if(!list2.isEmpty()) {
				
			 v2 = list2.get(index).getFirstname()+""+list2.get(index).getLastname();
			 
			 VuL2.add(list2.get(index).getFirstname());
			 
				System.out.println(""+v2);

				index++;
				
				if(list2.size()-index ==0) {
					
					index=0;	
				}
					
			}
			
			
			
			
		}
		

		
			
		
		return "redirect:/choice";
	}
	
	
	
		

		public Licence1 getcharger(List usersList) {
			
			 
			 usersList = readLic1Service.findAll();
			
			 Random rand = new Random();
			 Licence1 randomElement = (Licence1) usersList.get(rand.nextInt(usersList.size()));
			

			System.out.println("\n\n executé!!!"+randomElement.getFirstname());
			System.out.println("executé!!!"+randomElement.getId());
			
			return randomElement;
		} 
	
	
	
}
