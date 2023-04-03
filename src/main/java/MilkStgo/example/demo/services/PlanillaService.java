package MilkStgo.example.demo.services;

import MilkStgo.example.demo.entities.PlanillaEntity;
import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlanillaService {


    @Autowired
    ProveedorService proveedorService;

    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    public ArrayList<PlanillaEntity> calcularPagos() {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();

        //recorrer proveedores
        for(ProveedorEntity proveedor:proveedores){
            //Pago de kilo leche según categoria
            int pagoCategoria = calcularPagoCategoria(proveedor);
            System.out.println("pagoCategoria = ");
            System.out.println(pagoCategoria);
            //%grasa asociado al proveedor
            int pagoGrasa = obtenerPagoPorcentajeGrasa(proveedor);
            System.out.println("pagoGrasa = ");
            System.out.println(pagoGrasa);

            //%solidos Totales asociados al proveedor
            int pagoST = obtenerPagoPorcentajeST(proveedor);
            System.out.println("pagoST = ");
            System.out.println(pagoST);
            //calcular bonificación por frecuencia de entrega

            //calcular descuento variación según quincena anterior

            //ver si paga o no impuestos
        }




        return null;
    }

    private int obtenerPagoPorcentajeST(ProveedorEntity proveedor) {

        String codigo = proveedor.getCodigo();
        SubirPorcentajeEntity porcentajes = subirPorcentajeService.obtenerPorcentajesPorCodigo(codigo);

        System.out.println("------ PORCENTAJE ST ------");
        System.out.println("proveedor: " + codigo);
        System.out.println("porcentaje de solidos totales: " + porcentajes.getSolidoTotal());

        int porcentajeST = Integer.parseInt(porcentajes.getSolidoTotal());

        if(porcentajeST >= 0 && porcentajeST <= 7){
            return -130;
        }else if(porcentajeST >= 8 && porcentajeST <= 18){
            return -90;
        }else if(porcentajeST >= 19 && porcentajeST <= 35){
            return 95;
        }else if(porcentajeST >= 36){
            return 150;
        }

        return 0;
    }

    private int obtenerPagoPorcentajeGrasa(ProveedorEntity proveedor) {

        String codigo = proveedor.getCodigo();
        SubirPorcentajeEntity porcentajes = subirPorcentajeService.obtenerPorcentajesPorCodigo(codigo);

        System.out.println("------ PORCENTAJE GRASA ------");
        System.out.println("proveedor: " + codigo);
        System.out.println("porcentaje de grasa: " + porcentajes.getGrasa());

        int porcentajeGrasa = Integer.parseInt(porcentajes.getGrasa());

        if(porcentajeGrasa >= 0 && porcentajeGrasa <= 20){
            return 30;
        }else if(porcentajeGrasa >= 21 && porcentajeGrasa <= 45){
            return 80;
        }else if(porcentajeGrasa >= 46){
            return 120;
        }

        return 0;
    }

    private int calcularPagoCategoria(ProveedorEntity proveedor) {
        String categoria = proveedor.getCategoria();

        if(categoria.equals("A")){
            return 700;
        }
        else if(categoria.equals("B")){
            return 550;
        }
        else if(categoria.equals("C")){
            return 400;
        }
        else if(categoria.equals("D")){
            return 250;
        }
        return 0;
    }
}
