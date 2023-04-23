package tacos.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id;

    @Column
    private Date placedAt = new Date();

    @Column
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @Column
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @Column
    @NotBlank(message="City is required")
    private String deliveryCity;

    @Column
    @NotBlank(message = "City is required")
    private String deliveryState;

    @Column
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @Column
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Column
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Column
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @Column
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(TacoUDT taco){
        this.tacos.add(taco);
    }
}
