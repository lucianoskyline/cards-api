package com.cards.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountsCreateRequest {

    @NotBlank(message = "Informe o nome")
    @Length(min = 5, message = "Informe o nome corretamente")
    private String name;

    @NotBlank(message = "Informe o CPF")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @NotBlank(message = "Informe o celular")
    @Length(min = 1, message = "Informe o celular corretamente")
    private String cellphone;

    @NotBlank(message = "Informe o email")
    private String email;

    @NotBlank(message = "Informe o endereço")
    @Length(min = 1, message = "Informe o endereço corretamente")
    private String address;

    @NotBlank(message = "Informe o número")
    @Length(min = 1, message = "Informe o número corretamente")
    private String number;

    @NotBlank(message = "Informe o bairro")
    @Length(min = 5, message = "Informe o bairro corretamente")
    private String district;

    @NotBlank(message = "Informe a cidade")
    @Length(min = 5, message = "Informe a cidade corretamente")
    private String city;

    @NotBlank(message = "Informe a UF")
    @Length(min = 2, message = "Informe a UF corretamente")
    private String state;

    @NotBlank(message = "Informe o CEP")
    @Length(min = 9, message = "Informe o CEP corretamente")
    private String postalcode;

}
