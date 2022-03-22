package com.devops.dxc.devops.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public enum Tramo {

    TRAMO0(0, "libre de impuesto", 0,0),
    TRAMO1(0.08f, "renta anual entre $17.8 y $29.7 millones de pesos anuales", 17800000,29700000),
    TRAMO2(0.135f, "renta anual entre 29,7 a 41,6 millones de pesos anuales",29700000, 41600000),
    TRAMO3(0.23f, "renta anual entre 41,6 y 53,5 millones de pesos anuales",41600000, 53500000),
    TRAMO4(0.304f, "renta anual entre 53,5 y 71,4 millones de pesos anuales", 53500000,71400000),
    TRAMO5(0.35f, "renta anual más de 71,4 millones de pesos anuales",71400000,0);

    float porcentajeImpuesto;
    String detalleTramo;
    int limiteInferior;
    int limiteSuperior;
}