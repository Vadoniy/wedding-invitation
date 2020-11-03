package ramoni.rulezzz.invitation.db.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import ramoni.rulezzz.invitation.dto.GuestInfoDto;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String comment;

    @CreatedDate
    private LocalDateTime created;

    public static GuestInfo convertToEntity(GuestInfoDto guestInfoDto) {
        final var guestInfo = new GuestInfo();
        guestInfo.setName(guestInfoDto.getName());
        guestInfo.setContactPhone(guestInfoDto.getContactPhone());
        guestInfo.setWithChildren(guestInfoDto.isWithChildren());
        guestInfo.setPlusOne(guestInfoDto.isPlusOne());
        guestInfo.setComment(guestInfoDto.getComment());
        guestInfo.setAlcoholic(guestInfoDto.isAlcoholic());
        return guestInfo;
    }

    public static GuestInfo updateEntity(GuestInfoDto guestInfoDto, GuestInfo guestInfo) {
        guestInfo.setName(guestInfoDto.getName());
        guestInfo.setPlusOne(guestInfoDto.isPlusOne());
        guestInfo.setWithChildren(guestInfoDto.isWithChildren());
        guestInfo.setComment(guestInfoDto.getComment());
        guestInfo.setAlcoholic(guestInfoDto.isAlcoholic());
        return guestInfo;
    }
}
