package com.devops.dxc.devops.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Builder
public class Dxc {

    int dxc;
    int saldo;
    int impuesto;
    String detalleImpuesto;
    float porcentajeImpuesto;
    int sueldo;
    int ahorro;

}
