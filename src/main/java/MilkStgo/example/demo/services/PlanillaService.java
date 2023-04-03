package MilkStgo.example.demo.services;

import MilkStgo.example.demo.entities.PlanillaEntity;
import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirDataEntity;
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

    @Autowired
    SubirDataService subirDataService;

    public ArrayList<PlanillaEntity> calcularPagos() {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();

        //recorrer proveedores
        for(ProveedorEntity proveedor:proveedores){
            //Pago de kilo leche según categoria
            int multiplicadorCategoria = calcularPagoCategoria(proveedor);
            System.out.println("multiplicadorCategoria = "+ multiplicadorCategoria);
            //%grasa asociado al proveedor
            int multiplicadorGrasa = obtenerPagoPorcentajeGrasa(proveedor);
            System.out.println("multiplicadorGrasa = "+multiplicadorGrasa);

            //%solidos Totales asociados al proveedor
            int multiplicadorST = obtenerPagoPorcentajeST(proveedor);
            System.out.println("multiplicadorST = "+ multiplicadorST);

            //kilos de leche entregados
            int kilosLeche = calcularCantidadKilosLeche(proveedor);
            System.out.println("kilosLeche: "+kilosLeche);
            //calcular bonificación por frecuencia de entrega
            int multiplicadorFrecuencia = obtenerBonificacionFrecuencia(proveedor);
            System.out.println("MultiplicadorFrecuencia = "+ multiplicadorFrecuencia);
            //calcular descuento variación según quincena anterior

            //ver si paga o no impuestos
        }




        return null;
    }

    private int obtenerBonificacionFrecuencia(ProveedorEntity proveedor) {
        String codigo = proveedor.getCodigo();
        ArrayList<SubirDataEntity> acopioM = subirDataService.obtenerAcopioPorTurnoAndCodigo("M",codigo);
        ArrayList<SubirDataEntity> acopioT = subirDataService.obtenerAcopioPorTurnoAndCodigo("T",codigo);


        int sizeM = acopioM.size();
        int sizeT = acopioT.size();
        System.out.println(acopioM);
        System.out.println(sizeM);
        System.out.println(acopioT);
        System.out.println(sizeT);

        int sizeTotal = sizeM + sizeT;
        if(sizeTotal > 10 && sizeM > 0 && sizeT > 0){
            return 20;
        }else if(sizeM > 10){
            return 12;
        }else if(sizeT > 10){
            return 8;
        }

        return 1;
    }

    private int calcularCantidadKilosLeche(ProveedorEntity proveedor) {
        String codigo = "0"+proveedor.getCodigo();

        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorCodigo(codigo);

        int cant = 0;
        for(SubirDataEntity a:acopio){
            int kilos = Integer.parseInt(a.getKls_leche());
            cant = cant + kilos;
        }
        return cant;
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
