package com.company.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
