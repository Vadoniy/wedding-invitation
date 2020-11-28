package ramoni.rulezzz.invitation.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ramoni.rulezzz.invitation.db.model.GuestInfo;

import java.util.Optional;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
public interface GuestInfoRepository extends JpaRepository<GuestInfo, Long> {

    Optional<GuestInfo> findByContactPhone(String phoneNumber);
}
