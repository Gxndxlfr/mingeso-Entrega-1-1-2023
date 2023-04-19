package MilkStgo.example.demo;

import MilkStgo.example.demo.services.RegistroQuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistroQuincenaTest {

    @Autowired
    RegistroQuincenaService registroQuincenaService;
    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
