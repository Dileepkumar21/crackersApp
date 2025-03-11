package com.pos.crackers.repo;

import com.pos.crackers.model.BlogItem;
import com.pos.crackers.model.Crackers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogItem, Long>{
}
