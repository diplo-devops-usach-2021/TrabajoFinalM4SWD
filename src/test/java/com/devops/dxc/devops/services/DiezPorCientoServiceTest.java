package com.devops.dxc.devops.services;

import com.devops.dxc.devops.model.Serie;
import com.devops.dxc.devops.model.Tramo;
import com.devops.dxc.devops.model.Uf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiezPorCientoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    DiezPorCientoService diezPorCientoService;

    Uf uf;

    @BeforeEach
    void init(){
        uf = new Uf();
        Serie serie = new Serie();
        serie.setValor(30000);
        uf.setSerie(new Serie[]{serie});
    }

    @Test
    void calcular() {

    }

    @Test
    void calcularDiezPorCiento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        Mockito.when(restTemplate.getForObject("https://mindicador.cl/api/uf/{fecha}", Uf.class, formatter.format(LocalDate.now()))).thenReturn(uf);
        int diezPorCiento = diezPorCientoService.calcularDiezPorCiento(1000000);
        assertEquals(1000000, diezPorCiento);

        diezPorCiento = diezPorCientoService.calcularDiezPorCiento(40000000);
        assertEquals(4000000, diezPorCiento);

        diezPorCiento = diezPorCientoService.calcularDiezPorCiento(50000000);
        assertEquals(4500000, diezPorCiento);

        diezPorCiento = diezPorCientoService.calcularDiezPorCiento(900000);
        assertEquals(900000, diezPorCiento);
    }

    @Test
    void obtieneUF() {
    }

    @Test
    void calculaTramo() {
        Tramo tramo = diezPorCientoService.calculaTramo(1000000);
        assertEquals(Tramo.TRAMO0, tramo);

        tramo = diezPorCientoService.calculaTramo(1500000);
        assertEquals(Tramo.TRAMO1, tramo);

        tramo = diezPorCientoService.calculaTramo(3400000);
        assertEquals(Tramo.TRAMO2, tramo);

        tramo = diezPorCientoService.calculaTramo(4000000);
        assertEquals(Tramo.TRAMO3, tramo);

        tramo = diezPorCientoService.calculaTramo(5000000);
        assertEquals(Tramo.TRAMO4, tramo);

        tramo = diezPorCientoService.calculaTramo(10000000);
        assertEquals(Tramo.TRAMO5, tramo);
    }

    @Test
    void calculaImpuesto() {
        int i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO0);
        assertEquals(0, i);

        i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO1);
        assertEquals(80000, i);

        i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO2);
        assertEquals(135000, i);

        i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO3);
        assertEquals(230000, i);

        i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO4);
        assertEquals(304000, i);

        i = diezPorCientoService.calculaImpuesto(1000000, Tramo.TRAMO5);
        assertEquals(350000, i);
    }
}