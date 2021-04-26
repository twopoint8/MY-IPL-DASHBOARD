package com.sanvic.service;

import java.util.List;

import com.sanvic.model.MatchDataDBEntity;

public interface MatchDtlsService {

	public List<MatchDataDBEntity> getAllMatchesByTeamAndYear(String teamName, Integer year); 
}
