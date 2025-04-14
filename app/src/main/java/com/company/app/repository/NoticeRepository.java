package com.company.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	List<Notice> findByOrderByResdateDesc();
	List<Notice> findTop3ByOrderByResdateDesc();
}
