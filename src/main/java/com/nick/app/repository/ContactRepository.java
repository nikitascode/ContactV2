package com.nick.app.repository;

import com.nick.app.domain.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findByEmailLike(String emailPattern);
}
