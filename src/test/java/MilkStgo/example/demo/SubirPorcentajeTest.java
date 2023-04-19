package MilkStgo.example.demo;

import MilkStgo.example.demo.services.SubirPorcentajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SubirPorcentajeTest {

    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    @Test
    void testLeerCsv(){
        String response = subirPorcentajeService.leerCsv("porcentajes.csv");
        assertEquals("Archivo leido exitosamente 2",response);
    }
    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
