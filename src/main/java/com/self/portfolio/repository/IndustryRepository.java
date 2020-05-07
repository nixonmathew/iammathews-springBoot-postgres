package com.self.portfolio.repository;

import java.util.List;

import com.self.portfolio.entity.Industry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    @Query("SELECT i from Industry i where i.isActive=1 AND LOWER(i.description) like %:searchString%")
    public List<Industry> searchByDesc(@Param("searchString") String searchString);

    @Query("SELECT i from Industry i where i.isActive=1 AND LOWER(i.code) like %:numericRequest")
    public List<Industry> searchByNumericCode(@Param("numericRequest") int numericRequest);

    public List<Industry> findByisActive(Boolean isActive);
}