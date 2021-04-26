package com.sanvic.repository;

import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanvic.model.MatchDataDBEntity;

public interface MatchDataDBRepo extends JpaRepository<MatchDataDBEntity, Serializable>{

	public List<MatchDataDBEntity> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);
	
	@Query("from MatchDataDBEntity where (team1=:teamName or team2=:teamName) and date between :stDate and :endDate order by date desc")
	public List<MatchDataDBEntity> getMatchesByTeamBetweenMatches(
			@Param("teamName") String teamName, 
			@Param("stDate") LocalDate stDate, 
			@Param("endDate") LocalDate endDate);
	
	//public List<MatchDataDBEntity> findByTeam1OrTeam2AndDateInOrderByDateDesc(String teamName1, String teamName2, LocalDate stDate,  LocalDate endDate);
}
