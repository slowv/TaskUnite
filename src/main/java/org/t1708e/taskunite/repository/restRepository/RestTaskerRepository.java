package org.t1708e.taskunite.repository.restRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.t1708e.taskunite.domain.Address;
import org.t1708e.taskunite.domain.Tasker;

import java.util.List;

public interface RestTaskerRepository extends JpaRepository<Tasker, Long> {
    @Query(value = "select * from Tasker t where t.user = :address", nativeQuery = true)
    List<Tasker> findRelatedTasker(@Param("address") Address address);

    List<Tasker> findAllByUserWorkingAddresses(Address address);
//    List<Tasker> findAllByUserWorkingAddressesAndSchedulesFrom(Address address);
}
