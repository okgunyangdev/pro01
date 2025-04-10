package com.company.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.app.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
