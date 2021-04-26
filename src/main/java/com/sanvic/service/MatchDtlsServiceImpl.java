package com.sanvic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanvic.model.MatchDataDBEntity;
import com.sanvic.repository.MatchDataDBRepo;
@Service
public class MatchDtlsServiceImpl implements MatchDtlsService {

	private MatchDataDBRepo matchRepo;
	
	@Autowired
	public MatchDtlsServiceImpl(MatchDataDBRepo matchRepo) {
		this.matchRepo = matchRepo;
	
	}
	
	@Override
	public List<MatchDataDBEntity> getAllMatchesByTeamAndYear(String teamName, Integer year) {
		LocalDate stDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year+1, 1, 1);
		List<MatchDataDBEntity> matchList = matchRepo.getMatchesByTeamBetweenMatches(teamName, stDate, endDate);
		return matchList;
	}

}
