package com.project.company.works_with;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorksWithRepository extends JpaRepository<WorksWith, WorksWithId> {

	Optional<WorksWith> findById(WorksWithId worksWithId);
}
