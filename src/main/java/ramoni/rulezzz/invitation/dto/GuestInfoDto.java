package ramoni.rulezzz.invitation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ramoni.rulezzz.invitation.db.model.GuestInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Вадим Курбатов (kurbatov_1989@inbox.ru)
 */
@Getter
@Setter
public class GuestInfoDto {

    @ApiModelProperty(value = "Идентификатор гостя в БД, заполняется только при выгрузке списка гостей", hidden = true)
    private String id;

    @NotBlank
    @ApiModelProperty(value = "Имя гостя", required = true, example = "Иванов Иван Александрович")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "Контактный номер, чтобы мы вам могли позвонить. Без пробелов и дефисов", required = true, example = "+79016012021")
    @Size(min = 7, max = 12)
    private String contactPhone;

    @NotNull
    @ApiModelProperty(value = "Вы приедете с другом/подругой/мужем/женой/любовницей?")
    private Boolean plusOne;

    @NotNull
    private Boolean isAlcoholic;

    @NotNull
    private Boolean isOvernight;

    @NotNull
    @ApiModelProperty(value = "Вы приедете с детьми или без?")
    private Boolean withChildren;

    @ApiModelProperty(value = "Если есть, что для нас сообщить, это поле для вас.")
    private String comment;

    @Override
    public String toString() {
        return "вас зовут " + name + ", ваш номер телефона " + contactPhone +
                ", вы приедете " + (plusOne ? "с кем-то близким" : "один") +
                ", вы " + (isOvernight ? "останетесь на ночь" : "уедете сразу после праздника") +
                ", с вами " + (withChildren ? "будут дети" : "не будет детей") +
                ", вы " + (isAlcoholic ? "с удовольствием выпьете на нашем мероприятии" : "вы предпочитаете безалкогольные напитки") +
                (comment != null && !comment.isBlank() ? ", вы сообщаете нам следующее: " + comment + "." : ".");
    }

    public String forMail() {
        return "Информация о новом госте: " + name + ", номер телефона " + contactPhone +
                ", приедет " + (plusOne ? "с кем-то близким" : "один") +
                ", " + (withChildren ? "с детьми" : "без детей") +
                ", " + (isOvernight ? "останется на ночь" : "уедет после праздника") +
                ", " + (isAlcoholic ? "будет выпивать" : "будет пить сок, воду и молоко") +
                (comment != null && !comment.isBlank() ? ", хочет сообщить следующее: " + comment + "." : ".");
    }

    public static GuestInfoDto toDto(GuestInfo guestInfo) {
        final var dto = new GuestInfoDto();
        dto.setId(guestInfo.getId().toString());
        dto.setName(guestInfo.getName());
        dto.setContactPhone(guestInfo.getContactPhone());
        dto.setPlusOne(guestInfo.isPlusOne());
        dto.setIsAlcoholic(guestInfo.isAlcoholic());
        dto.setIsOvernight(guestInfo.isOvernight());
        dto.setWithChildren(guestInfo.isWithChildren());
        dto.setComment(guestInfo.getComment());
        return dto;
    }
}
