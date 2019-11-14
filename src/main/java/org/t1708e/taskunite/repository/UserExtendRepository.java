package org.t1708e.taskunite.repository;
import org.t1708e.taskunite.domain.UserExtend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserExtend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtendRepository extends JpaRepository<UserExtend, Long> {

}
