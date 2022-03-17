package com.devops.dxc.devops.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Uf {

    String version;
    String autor;
    String codigo;
    String nombre;
    @JsonProperty(value = "unidad_medida")
    String unidadMedida;
    Serie[] serie;
}
