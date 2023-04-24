package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.repositories.ProveedorRepository;
import MilkStgo.example.demo.services.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProveedorTest {

    @Autowired
    ProveedorService proveedorService;
    @Autowired
    ProveedorRepository proveedorRepository;

    @Test
    void testObtenerProveedores(){
        ArrayList<ProveedorEntity> proveedoresAux = new ArrayList<>();
        ProveedorEntity prov = new ProveedorEntity();
        prov.setId(10L);
        prov.setCodigo("1");
        prov.setNombre("name 1");
        prov.setCategoria("A");
        proveedoresAux.add(prov);

        proveedorService.guardarProveedor("1","name 1","A");
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        proveedores.remove(1);
        assertArrayEquals(new ArrayList[]{proveedoresAux}, new ArrayList[]{proveedores});

    }

    @Test
    void testGuardarProveedor(){
        ProveedorEntity prov = new ProveedorEntity();
        prov.setId(1L);
        prov.setCodigo("2");
        prov.setNombre("name 2");
        prov.setCategoria("B");

        String response = proveedorService.guardarProveedor("1","name 1","A");

        assertEquals("Nuevo Proveedor", response);

        proveedorService.eliminarProveedor("2");
    }

    @Test
    void testEliminarProveedor(){
        ProveedorEntity prov = new ProveedorEntity();
        prov.setId(2L);
        prov.setCodigo("3");
        prov.setNombre("name 3");
        prov.setCategoria("B");

        proveedorService.guardarProveedor("3", "name 3", "B");

        String response = proveedorService.eliminarProveedor("3");

        assertEquals("proveedor eliminado",response);
    }




    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
