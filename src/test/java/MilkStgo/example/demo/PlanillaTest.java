package MilkStgo.example.demo;

import MilkStgo.example.demo.services.PlanillaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PlanillaTest {

    @Autowired
    PlanillaService planillaService;

    @Test
    void testCalcularPagoCategoria(){
        int pagoCategoria = planillaService.calcularPagoCategoria("A");
        assertEquals(700, pagoCategoria);
    }
}
