package com.sanvic.batchprocessing;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sanvic.model.MatchDataCSVEntity;
import com.sanvic.model.MatchDataDBEntity;

public class MatchBatchProcessor implements ItemProcessor<MatchDataCSVEntity, MatchDataDBEntity> {


		  private static final Logger log = LoggerFactory.getLogger(MatchBatchProcessor.class);

		 

		@Override
		public MatchDataDBEntity process(final MatchDataCSVEntity item) throws Exception {
			
			MatchDataDBEntity match = new MatchDataDBEntity();
			match.setId(Long.parseLong(item.getId()));
			match.setCity(item.getCity());
			match.setDate(LocalDate.parse(item.getDate()));
			match.setPlayerOfMatch(item.getPlayerOfMatch());
			match.setVenue(item.getVenue());
			String firstInningTeam, secondInningTeam;
			if("bat".equals(item.getTossDecision()))
			{
				firstInningTeam = item.getTossWinner();
				secondInningTeam = item.getTossWinner().equals(item.getTeam1()) ? item.getTeam2() : item.getTeam1();
			}
			else {
				secondInningTeam = item.getTossWinner();
				firstInningTeam = item.getTossWinner().equals(item.getTeam1()) ? item.getTeam2() : item.getTeam1();
			}
			match.setTeam1(firstInningTeam);
			match.setTeam2(secondInningTeam);	
			match.setTossWinner(item.getTossWinner());
			match.setTossDecision(item.getTossDecision());
			match.setMatchWinner(item.getWinner());
			match.setResult(item.getResult());
			match.setResultMargin(item.getResultMargin());
			match.setEliminator(item.getEliminator());
			match.setMethod(item.getMethod());
			match.setUmpire1(item.getUmpire1());
			match.setUmpire2(item.getUmpire2());
			
			return match;
		}

}

