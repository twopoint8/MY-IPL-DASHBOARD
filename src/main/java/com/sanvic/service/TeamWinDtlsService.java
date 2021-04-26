package com.sanvic.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sanvic.model.MatchDataDBEntity;
import com.sanvic.model.TeamWinDtlsEntity;

public interface TeamWinDtlsService {
	
	public TeamWinDtlsEntity getTeamWinDataByTeamName(String teamName);
	
	public List<MatchDataDBEntity> getFourLatestMatch(String team1, String team2);
	
	public List<TeamWinDtlsEntity> getAllTeams();
}
