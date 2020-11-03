package ramoni.rulezzz.invitation.dto;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestInfoDto {

    @NotBlank
    @Valid
    @ApiModelProperty(value = "Имя гостя", required = true, example = "Иванов Иван Александрович/Лёха/Матроскин")
    private String name;

    @NotBlank
    @Valid
    @ApiModelProperty(value = "Контактный номер, чтобы мы вам могли позвонить. Без пробелов и дефисов", required = true, example = "+79016012021/9016012021/89016012021")
    @Size(min = 7, max = 12)
    private String contactPhone;

    @NotNull
    @Valid
    @ApiModelProperty(value = "Вы приедете с другом/подругой/мужем/женой/любовницей?")
    private boolean plusOne = false;

    @NotNull
    @Valid
    private boolean isAlcoholic = true;

    @NotNull
    @Valid
    @ApiModelProperty(value = "Вы приедете с детьми или без?")
    private boolean withChildren = false;

    @ApiModelProperty(value = "Если есть, что для нас сообщить, это поле для вас. Советую воспользоваться, если вы едете с детьми")
    private String comment;

    @Override
    public String toString() {
        return "вас зовут " + name + ", ваш номер телефона " + contactPhone +
                ", вы приедете " + (plusOne ? "с кем-то близким" : "один") +
                ", с вами " + (withChildren ? "будут дети" : "не будет детей") +
                ", вы " + (isAlcoholic ? "с удовольствием выпьете на нашем мероприятии" : "вы предпочитаете безалкогольные напитки") +
                (comment != null && !comment.isBlank() ? ", вы сообщаете нам следующее: " + comment + "." : ".");
    }

    public String forMail() {
        return "Информация о новом госте: " + name + ", номер телефона " + contactPhone +
                ", приедет " + (plusOne ? "с кем-то близким" : "один") +
                ", " + (withChildren ? "с детьми" : "без детей") +
                ", " + (isAlcoholic ? "будет выпивать" : "будет пить сок, воду и молоко") +
                (comment != null && !comment.isBlank() ? ", хочет сообщить следующее: " + comment + "." : ".");
    }
}
