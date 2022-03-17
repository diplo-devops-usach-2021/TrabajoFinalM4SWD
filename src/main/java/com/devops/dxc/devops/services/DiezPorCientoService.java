package com.devops.dxc.devops.services;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Tramo;
import com.devops.dxc.devops.model.Uf;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class DiezPorCientoService {

    public Dxc calcular(int ahorro, int sueldo) {
        int montoDiezPorCiento = this.calcularDiezPorCiento(ahorro);
        int saldo = ahorro - montoDiezPorCiento;
        Tramo tramo = calculaTramo(sueldo);
        int impuesto = calculaImpuesto(montoDiezPorCiento, tramo);
        return Dxc.builder()
                .ahorro(ahorro)
                .sueldo(sueldo)
                .dxc(montoDiezPorCiento)
                .saldo(saldo)
                .impuesto(impuesto)
                .detalleImpuesto(tramo.getDetalleTramo())
                .porcentajeImpuesto(tramo.getPorcentajeImpuesto())
                .build();
    }

    RestTemplate restTemplate;

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     *
     * @param ahorro
     * @return
     */
    public int calcularDiezPorCiento(int ahorro) {
        if (((ahorro * 0.1) / obtieneUF()) > 150) {
            return (int) (150 * obtieneUF());
        } else if ((ahorro * 0.1) <= 1000000 && ahorro >= 1000000) {
            return (int) 1000000;
        } else if (ahorro <= 1000000) {
            return (int) ahorro;
        } else {
            return (int) (ahorro * 0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     *
     * @return
     */
    public int obtieneUF() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        Uf uf = restTemplate.getForObject("https://mindicador.cl/api/uf/{fecha}", Uf.class, formatter.format(LocalDate.now()));
        return uf.getSerie()[0].getValor();
    }

    public Tramo calculaTramo(int sueldo) {
        int sualdoAnual = sueldo * 12;
        if (sualdoAnual >= Tramo.TRAMO1.getLimiteInferior() && sualdoAnual < Tramo.TRAMO1.getLimiteSuperior()) {
            return Tramo.TRAMO1;
        } else if (sualdoAnual >= Tramo.TRAMO2.getLimiteInferior() && sualdoAnual < Tramo.TRAMO2.getLimiteSuperior()) {
            return Tramo.TRAMO2;
        } else if (sualdoAnual >= Tramo.TRAMO3.getLimiteInferior() && sualdoAnual < Tramo.TRAMO3.getLimiteSuperior()) {
            return Tramo.TRAMO3;
        } else if (sualdoAnual >= Tramo.TRAMO4.getLimiteInferior() && sualdoAnual < Tramo.TRAMO4.getLimiteSuperior()) {
            return Tramo.TRAMO4;
        } else if (sualdoAnual >= Tramo.TRAMO5.getLimiteInferior()) {
            return Tramo.TRAMO5;
        } else {
            return Tramo.TRAMO0;
        }
    }

    public int calculaImpuesto(int montoDiezPorCiento, Tramo tramo) {
        return (int) (montoDiezPorCiento * tramo.getPorcentajeImpuesto());
    }
}
