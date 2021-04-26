package com.sanvic.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "TEAMS_WIN_DTLS")
public class TeamWinDtlsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String teamName;
	private Long toalMatchPlayed;
	private Long totalWinMatch;
	
	@Transient
	private List<MatchDataDBEntity> matches;
	
	public TeamWinDtlsEntity(String teamName, Long toalMatchPlayed) {
		this.teamName = teamName;
		this.toalMatchPlayed = toalMatchPlayed;
	}
	
	public TeamWinDtlsEntity() {
		// TODO Auto-generated constructor stub
	}
	
}
