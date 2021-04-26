package com.sanvic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanvic.model.MatchDataDBEntity;
import com.sanvic.service.MatchDtlsServiceImpl;

@RestController
@CrossOrigin
public class MatchDtlsRestController {

	private MatchDtlsServiceImpl matchService;
	
	@Autowired
	public MatchDtlsRestController(MatchDtlsServiceImpl matchService) {
		this.matchService = matchService;
	}
	
	@GetMapping("/team/{teamName}/year/{year}")
	public List<MatchDataDBEntity> fetchMatchesByYear(@PathVariable String teamName, @PathVariable Integer year)
	{
		List<MatchDataDBEntity> matchList = matchService.getAllMatchesByTeamAndYear(teamName, year);
		return matchList;
	}
}
