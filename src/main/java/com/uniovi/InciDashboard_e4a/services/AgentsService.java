package com.uniovi.InciDashboard_e4a.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;
@Service
public class AgentsService {
	@Autowired
	private AgentsRepository agentsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void addAgent(Agent agent) {
		agent.setPassword(bCryptPasswordEncoder.encode(agent.getPassword()));
		this.agentsRepository.save(agent);
	}

	public Agent findAgentByUsername(String string) {
		return agentsRepository.findByUsername(string);
	}
	
	public Agent getActiveAgent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Agent activeUser = findAgentByUsername(username);
		return activeUser;
	}
}
