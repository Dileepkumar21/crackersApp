package com.pos.crackers.repo;

import com.pos.crackers.model.Crackers;
import com.pos.crackers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrackersRepository extends JpaRepository<Crackers, Long> {

    public Optional<Crackers> findByName(String name);
}
