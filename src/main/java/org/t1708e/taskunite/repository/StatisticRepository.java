package org.t1708e.taskunite.repository;
import org.t1708e.taskunite.domain.Statistic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Statistic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

}
