package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import MilkStgo.example.demo.services.PlanillaService;
import MilkStgo.example.demo.services.ProveedorService;
import MilkStgo.example.demo.services.SubirPorcentajeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PlanillaTest {

    @Autowired
    PlanillaService planillaService;


    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    @Autowired
    ProveedorService proveedorService;

    @Test
    void testCalcularPagoCategoria(){
        int pagoCategoria = planillaService.calcularPagoCategoria("A");
        assertEquals(700, pagoCategoria);
    }

    @Test
    void testObtenerPagoPorcentajeGrasa(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("10");
        sP.setCodigo("0001");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0001","prov 0001","B");
        proveedorService.guardarProveedor("0001","prov 0001","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0001");

        //comparar
        assertEquals(30,response);


    }
}
