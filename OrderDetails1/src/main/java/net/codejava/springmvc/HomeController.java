package net.codejava.springmvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/subscribe/{orderID}", method = RequestMethod.GET)
	public String getOrderDetails(@PathVariable String orderID, Model model) {
		
		
		String id = orderID;
		
		if(id.length()<=31){
			
			String trimId = getTrimString(id,31);
			model.addAttribute("trimId", trimId );
		}
		String trimId = getShortString(id,25);
				
		/*Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);*/
		
		//model.addAttribute("serverTime", formattedDate );
		model.addAttribute("trimId", trimId );
		
		return "home";
}
	
	public String getTrimString(String str, int width) {
		 if (str != null && str.length() > width) {
		      return str.substring(0, width) + "...";
		  } 
		  else {
		      return str;
		  }
		}
	
		
	public String getShortString(String str, int width) {
		 if (str != null && str.length() > width) {
		      return str.substring(0, width) + "...";
		  } 
		  else {
		      return str;
		  }
		}
	
	
	
	
}
