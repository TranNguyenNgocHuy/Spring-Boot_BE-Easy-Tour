package com.easy.tour.repository;

import com.easy.tour.entity.Report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r WHERE MONTH(r.createdDate) = :month")
    public List<Report> findByMonth(String month);
}
