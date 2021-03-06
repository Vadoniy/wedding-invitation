package ramoni.rulezzz.invitation.db.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import ramoni.rulezzz.invitation.dto.GuestInfoDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
@Data
@Entity
@Table(name = "GUESTS")
public class GuestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "GUEST_INFO_GEN", sequenceName = "GUEST_INFO_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String contactPhone;

    @NotNull
    private boolean plusOne = false;

    @NotNull
    private boolean withChildren = false;

    @NotNull
    private boolean isAlcoholic = true;

    @NotNull
    private boolean isOvernight = true;

    private String comment;

    @CreationTimestamp
    private LocalDateTime created;

    public static GuestInfo convertToEntity(GuestInfoDto guestInfoDto) {
        final var guestInfo = new GuestInfo();
        guestInfo.setName(guestInfoDto.getName());
        guestInfo.setContactPhone(guestInfoDto.getContactPhone());
        guestInfo.setWithChildren(guestInfoDto.getWithChildren());
        guestInfo.setPlusOne(guestInfoDto.getPlusOne());
        guestInfo.setOvernight(guestInfoDto.getIsOvernight());
        guestInfo.setComment(guestInfoDto.getComment());
        guestInfo.setAlcoholic(guestInfoDto.getIsAlcoholic());
        return guestInfo;
    }

    public static GuestInfo updateEntity(GuestInfoDto guestInfoDto, GuestInfo guestInfo) {
        guestInfo.setName(guestInfoDto.getName());
        guestInfo.setPlusOne(guestInfoDto.getPlusOne());
        guestInfo.setOvernight(guestInfoDto.getIsOvernight());
        guestInfo.setWithChildren(guestInfoDto.getWithChildren());
        guestInfo.setComment(guestInfoDto.getComment());
        guestInfo.setAlcoholic(guestInfoDto.getIsAlcoholic());
        return guestInfo;
    }
}
