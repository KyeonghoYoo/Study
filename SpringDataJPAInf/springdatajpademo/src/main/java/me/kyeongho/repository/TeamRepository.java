package me.kyeongho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.kyeongho.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
