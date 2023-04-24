package MilkStgo.example.demo;

import MilkStgo.example.demo.repositories.RegistroQuincenaRepository;
import MilkStgo.example.demo.services.ProveedorService;
import MilkStgo.example.demo.services.RegistroQuincenaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RegistroQuincenaTest {

    @Autowired
    RegistroQuincenaService registroQuincenaService;

    @Autowired
    ProveedorService proveedorService;
    @Autowired
    private RegistroQuincenaRepository registroQuincenaRepository;

    @Test
    void testGuardarRegistroQuincena(){
        String response = registroQuincenaService.guardarRegistroQuincena("0007","30","31","100");
        assertEquals("nueva quincena", response);
    }

    @Test
    void testsetAnteriorQuince(){
        proveedorService.guardarProveedor("7","name 7","B");
        String response = registroQuincenaService.setAnteriorQuince();
        assertEquals("set quincena anterior", response);

    }

    @Test
    void testGetKilosByCodigo(){
        proveedorService.guardarProveedor("8","name 8","B");
        registroQuincenaService.guardarRegistroQuincena("8","30","31","100");

        int response = registroQuincenaService.getKilosByCodigo("8");
        assertEquals(100,response);
    }
    @Test
    void testObtenerGrasaAntigua(){
        proveedorService.guardarProveedor("9","name 9","B");
        registroQuincenaService.guardarRegistroQuincena("9","30","31","100");

        int response = registroQuincenaService.obtenerGrasaAntigua("9");
        assertEquals(30,response);
    }
    @Test
    void testObtenerStAntigua(){
        proveedorService.guardarProveedor("10","name 10","B");
        registroQuincenaService.guardarRegistroQuincena("10","30","31","100");

        int response = registroQuincenaService.obtenerStAntigua("10");
        assertEquals(31,response);
    }

    @Test
    void testActualizarDatos(){
        proveedorService.guardarProveedor("11","name 11","B");
        registroQuincenaService.guardarRegistroQuincena("11","30","31","100");

        String response = registroQuincenaService.actualizarDatos("11",101,32,31);

        assertEquals("registro quincena actualizado",response);

    }
    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
