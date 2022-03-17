package com.devops.dxc.devops.model;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class Serie {

    LocalDateTime fecha;
    int valor;

}
