package com.sanvic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sanvic.model.MatchDataDBEntity;
import com.sanvic.model.TeamWinDtlsEntity;
import com.sanvic.repository.MatchDataDBRepo;
import com.sanvic.repository.TeamWinDtlsRepo;

@Service
public class TeamWinDtlsServiceImpl implements TeamWinDtlsService{

	private TeamWinDtlsRepo teamRepo;
	private MatchDataDBRepo matchRepo;
	
	@Autowired
	public TeamWinDtlsServiceImpl(TeamWinDtlsRepo teamRepo, MatchDataDBRepo matchRepo) {
		this.teamRepo = teamRepo;
		this.matchRepo = matchRepo;
	}
	@Override
	public TeamWinDtlsEntity getTeamWinDataByTeamName(String teamName) {

		return teamRepo.findByTeamName(teamName);
	}
	@Override
	public List<MatchDataDBEntity> getFourLatestMatch(String team1, String team2) {
		final int PAGE_SIZE = 4;
		final int PAGE_NO = 0;
		Pageable pageable = PageRequest.of(PAGE_NO, PAGE_SIZE);
		 List<MatchDataDBEntity> list = matchRepo.findByTeam1OrTeam2OrderByDateDesc(team1, team2, pageable);
		 
		 return list;
	}
	
	@Override
	public List<TeamWinDtlsEntity> getAllTeams(){
		return teamRepo.findAll();
	}
	

	
}
