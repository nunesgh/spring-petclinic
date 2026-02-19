package org.springframework.samples.petclinic.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

	@ModelAttribute("currentUserRole")
	public String populateUserRole(HttpSession session) {
		String role = (String) session.getAttribute("currentUserRole");
		return (role != null) ? role : "GUEST";
	}

	@ModelAttribute("currentUserId")
	public Integer populateUserId(HttpSession session) {
		Integer id = (Integer) session.getAttribute("currentUserId");
		return (id != null) ? id : -1;
	}

}
