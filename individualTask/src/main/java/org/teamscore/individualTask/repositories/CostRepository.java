package org.teamscore.individualTask.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.teamscore.individualTask.models.entity.Cost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CostRepository extends CrudRepository<Cost, Long> {
    List<Cost> findAll(Pageable pageable);
    @Query("SELECT c FROM Cost c WHERE c.dateTimePay BETWEEN :from AND :to")
    List<Cost> findAllByPeriod(@Param("from") LocalDateTime from, @Param("to")LocalDateTime to);
}
