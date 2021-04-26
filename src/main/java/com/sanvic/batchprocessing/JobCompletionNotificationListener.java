package com.sanvic.batchprocessing;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sanvic.model.TeamWinDtlsEntity;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

private final EntityManager em;
private final JdbcTemplate jt;

  @Autowired
  public JobCompletionNotificationListener(EntityManager em, JdbcTemplate jt) {
	  this.em = em;
	  this.jt= jt;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");
      
//      jt.query("select umpire1, umpire2 from match_dtls", 
//    		 (rs, row) -> "U1 "+ rs.getString(1) + "U2 " + rs.getString(2) 
//    		  ).forEach(str -> System.out.println(str));
      
      	Map<String, TeamWinDtlsEntity> teamData =  new HashMap<>(); 
      
       em.createQuery("select m.team1, count(*) from MatchDataDBEntity m group by m.team1", Object[].class)
       .getResultList()
       .stream()
       .map(e -> (new TeamWinDtlsEntity((String)e[0], (Long)e[1])))
       .forEach(team -> teamData.put(team.getTeamName(), team));
       
       em.createQuery("select m.team2, count(*) from MatchDataDBEntity m group by m.team2", Object[].class)
       .getResultList()
       .stream()
       .forEach(e -> {
    	   TeamWinDtlsEntity team = teamData.get((String) e[0]);
    	   team.setToalMatchPlayed(team.getToalMatchPlayed() + (Long) e[1]);
       });
       
       em.createQuery("select m.matchWinner, count(*) from MatchDataDBEntity m group by m.matchWinner", Object[].class)
       .getResultList()
       .stream()
       .forEach(e -> {
    	   TeamWinDtlsEntity team = teamData.get((String) e[0]);
    	   if(team !=  null) team.setTotalWinMatch( (Long) e[1]);
       });
     
      teamData
      .values()
      .forEach( team -> em.persist(team));
      
    }
  }
}