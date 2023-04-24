package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import MilkStgo.example.demo.services.PlanillaService;
import MilkStgo.example.demo.services.ProveedorService;
import MilkStgo.example.demo.services.SubirDataService;
import MilkStgo.example.demo.services.SubirPorcentajeService;
import org.junit.jupiter.api.Test;
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

    @Autowired
    SubirDataService subirDataService;
    @Test
    void testCalcularPagoCategoriaA(){
        int pagoCategoria = planillaService.calcularPagoCategoria("A");
        assertEquals(700, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaB(){
        int pagoCategoria = planillaService.calcularPagoCategoria("B");
        assertEquals(550, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaC(){
        int pagoCategoria = planillaService.calcularPagoCategoria("C");
        assertEquals(400, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaD(){
        int pagoCategoria = planillaService.calcularPagoCategoria("D");
        assertEquals(250, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaE(){
        int pagoCategoria = planillaService.calcularPagoCategoria("E");
        assertEquals(0, pagoCategoria);
    }

    @Test
    void testObtenerPagoPorcentajeGrasa30(){

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

        proveedorService.eliminarProveedor("0001");

    }

    @Test
    void testObtenerPagoPorcentajeGrasa80(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("25");
        sP.setCodigo("0002");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0002","prov 0002","B");
        proveedorService.guardarProveedor("0002","prov 0002","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0002");

        //comparar
        assertEquals(80,response);

        proveedorService.eliminarProveedor("0002");

    }
    @Test
    void testObtenerPagoPorcentajeGrasa120(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("60");
        sP.setCodigo("0003");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0003","prov 0003","B");
        proveedorService.guardarProveedor("0003","prov 0003","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0003");

        //comparar
        assertEquals(120,response);

        proveedorService.eliminarProveedor("0003");

    }
    @Test
    void testObtenerPagoPorcentajeGrasa0(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("-60");
        sP.setCodigo("0004");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0004","prov 0004","B");
        proveedorService.guardarProveedor("0004","prov 0004","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0004");

        //comparar
        assertEquals(0,response);

        proveedorService.eliminarProveedor("0004");

    }

    @Test
    void testObtenerPagoPorcentajeST_130(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("5");
        sP.setCodigo("0005");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0005","prov 0005","B");
        proveedorService.guardarProveedor("0005","prov 0005","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0005");

        //comparar
        assertEquals(-130,response);

        proveedorService.eliminarProveedor("0005");
    }

    @Test
    void testObtenerPagoPorcentajeST_90(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("15");
        sP.setCodigo("0006");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0006","prov 0006","B");
        proveedorService.guardarProveedor("0006","prov 0006","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0006");

        //comparar
        assertEquals(-90,response);

        proveedorService.eliminarProveedor("0006");
    }

    @Test
    void testObtenerPagoPorcentajeST_95(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("30");
        sP.setCodigo("0007");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0007","prov 0007","B");
        proveedorService.guardarProveedor("0007","prov 0007","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0007");

        //comparar
        assertEquals(95,response);

        proveedorService.eliminarProveedor("0007");
    }

    @Test
    void testObtenerPagoPorcentajeST_150(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("60");
        sP.setCodigo("0008");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0008","prov 0008","B");
        proveedorService.guardarProveedor("0008","prov 0008","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0008");

        //comparar
        assertEquals(150,response);

        proveedorService.eliminarProveedor("0008");
    }

    @Test
    void testObtenerPagoPorcentajeST_0(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("-10");
        sP.setCodigo("0009");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0009","prov 0009","B");
        proveedorService.guardarProveedor("0009","prov 0009","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0009");

        //comparar
        assertEquals(0,response);

        proveedorService.eliminarProveedor("0009");
    }

    @Test
    void testCalcularCantidadKilosLeche(){

        subirDataService.guardarDataDB("01/04/2023","M","0010","5");
        subirDataService.guardarDataDB("01/04/2023","T","0010","10");

        int response = planillaService.calcularCantidadKilosLeche("0010");

        assertEquals(15,response);
    }


}
