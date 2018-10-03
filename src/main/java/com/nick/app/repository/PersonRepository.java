package com.nick.app.repository;

import com.nick.app.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByBirthDateBetweenAndBirthDate(Date firstDate, Date secondDate);
}
