package org.t1708e.taskunite.repository;
import org.t1708e.taskunite.domain.PaymentInformation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentInformationRepository extends JpaRepository<PaymentInformation, Long> {

}
