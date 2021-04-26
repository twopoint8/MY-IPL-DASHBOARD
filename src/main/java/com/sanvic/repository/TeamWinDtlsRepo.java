package com.sanvic.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanvic.model.TeamWinDtlsEntity;

public interface TeamWinDtlsRepo extends JpaRepository<TeamWinDtlsEntity, Serializable> {

	public TeamWinDtlsEntity findByTeamName(String teamName);
}
