package com.sanvic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sanvic.model.TeamWinDtlsEntity;
import com.sanvic.service.TeamWinDtlsServiceImpl;

@RestController
@CrossOrigin
public class TeamWinDtlsRestController {

	private TeamWinDtlsServiceImpl service;
	
	@Autowired
	public TeamWinDtlsRestController(TeamWinDtlsServiceImpl service) {
		
		this.service = service;
	}
	@GetMapping(value = "/team/{teamName}")
	public TeamWinDtlsEntity fetchTeamData(@PathVariable String teamName) {
		TeamWinDtlsEntity team = service.getTeamWinDataByTeamName(teamName);
		team.setMatches(service.getFourLatestMatch(teamName, teamName));
		if(team == null) {
			return null;
		}
		else	
		return team;
	}
	
	@GetMapping("/team")
	public Iterable<TeamWinDtlsEntity> fetchAllTeams(){
		
		return service.getAllTeams();
		
	}
}
