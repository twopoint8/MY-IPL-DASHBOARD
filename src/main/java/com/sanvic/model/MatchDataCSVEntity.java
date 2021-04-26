package com.sanvic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class MatchDataCSVEntity {

	@Id
	private String id;
	private String city;
	private String date;
	private String playerOfMatch;
	private String venue;
	private String neutral_venue;
	private String team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String winner;
	private String result;
	private String resultMargin;
	private String eliminator;
	private String method;
	private String umpire1;
	private String umpire2;
}
