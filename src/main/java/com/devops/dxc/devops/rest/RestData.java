package com.devops.dxc.devops.rest;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.services.DiezPorCientoService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping(path = "/rest/msdxc")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class RestData {

    DiezPorCientoService diezPorCientoService;

    @GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Dxc getData(@RequestParam(name = "sueldo") int sueldo, @RequestParam(name = "ahorro") int ahorro) {
        log.info("< Trabajo DevOps - DXC > <Consultado Diez por ciento>");
        Dxc dxc = diezPorCientoService.calcular(ahorro, sueldo);
        return dxc;
    }
}