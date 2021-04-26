package com.sanvic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MATCH_DTLS")
public class MatchDataDBEntity {

	@Id
	private Long id;
	private String city;
	private LocalDate date;
	private String playerOfMatch;
	private String venue;
	private String team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String matchWinner;
	private String result;
	private String resultMargin;
	private String eliminator;
	private String method;
	private String umpire1;
	private String umpire2;
}
