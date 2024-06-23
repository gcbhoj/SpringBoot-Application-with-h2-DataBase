package ca.sheridancollege.ghartich.controllers;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.ghartich.beans.Ticket;
import ca.sheridancollege.ghartich.repository.TicketRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private TicketRepository ticketRepo;

	@GetMapping("/")
	public String goToHomePage() {
		return "home.html";
	}

	@GetMapping("/add")
	public String goToAddPage(Model model) {
		model.addAttribute("myTicket", new Ticket());
		return "add.html";
	}
	
	@PostMapping("/add")
	public String processTicket(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.addTicket(ticket);
		return "redirect:/add";
	}

	@GetMapping("/view")
	public String goToViewPage(Model model) {
		ArrayList<Ticket> ticket =  ticketRepo.getTicket();
		model.addAttribute("myTicket", ticket);
		return "view.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editTicket(@PathVariable int id, Model model) {
		Ticket ticket = ticketRepo.findByID(id);
		model.addAttribute("myTicket", ticket);
		return "edit.html";
	}
	
	@PostMapping("/edit")
	public String processEdit(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.editTicket(ticket);
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id, Model model) {
		ticketRepo.deleteTicket(id);
		return "redirect:/view";
	}
	
	
	@GetMapping("/stats")
	public String goToStatsPage(Model model) {
		ArrayList<Ticket> ticket = ticketRepo.getNumberOfAttendees();
		ArrayList<Ticket> ticke = ticketRepo.getListOfAttendees();
		ArrayList<Ticket> tick = ticketRepo.getRevenueByEvent();
		ArrayList<Ticket> tic = ticketRepo.getMaxAttendanceBySection();
		ArrayList<Ticket> ti = ticketRepo.getNumberOfParkingNeeded();
		model.addAttribute("hello", ticket);
		model.addAttribute("hi", ticke);
		model.addAttribute("my", tick);
		model.addAttribute("mine", tic);
		model.addAttribute("parking", ti);
		return "stats.html";
	}
	
	
	
	
	
}

