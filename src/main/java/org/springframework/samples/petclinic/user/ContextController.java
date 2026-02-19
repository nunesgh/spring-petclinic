package org.springframework.samples.petclinic.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContextController {

	@GetMapping("/switch-user")
	public String switchUser(@RequestParam String role, HttpSession session) {
		session.setAttribute("currentUserRole", role.toUpperCase());
		if (role.toUpperCase().equals("OWNER")) {
			session.setAttribute("currentUserId", 3);
		}
		else if (role.toUpperCase().equals("VET")) {
			session.setAttribute("currentUserId", 2);
		}
		else {
			session.setAttribute("currentUserId", -1); // Admin and Guest.
		}
		// System.out.println(session.getAttribute("currentUserId")); // For testing only!
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
