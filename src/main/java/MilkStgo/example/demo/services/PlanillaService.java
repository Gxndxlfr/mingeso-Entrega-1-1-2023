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

    @Autowired
    RegistroQuincenaService registroQuincenaService;


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

            int pagoLeche = kilosLeche*multiplicadorCategoria;
            int pagoGrasa = kilosLeche*multiplicadorGrasa;
            int pagoST = kilosLeche*multiplicadorST;

            double porcentajeBonificacion = multiplicadorFrecuencia/100;
            int bonificacionPago = (int) Math.floor(pagoLeche*porcentajeBonificacion);

            int pagoAcopioLeche = pagoLeche + pagoGrasa + pagoST + bonificacionPago;

            System.out.println("pagoAcopioLeche: "+pagoLeche+"+"+pagoGrasa+"+"+pagoST+"+"+bonificacionPago+"="+pagoAcopioLeche);
            //calcular descuento variación según quincena anterior

            //Descuento variacion leche
            int multiplicadorDescuentoLeche = obtenerDescuentoLeche(proveedor);
            double porcentajeDescuentoLeche = multiplicadorDescuentoLeche/100;
            System.out.println("multiplicadorDescuentoLeche: "+multiplicadorDescuentoLeche);
            //Descuento variación Grasa
            int multiplicadorDescuentoGrasa = obtenerDescuentoGrasa(proveedor);
            double porcentajeDescuentoGrasa = multiplicadorDescuentoGrasa/100;
            System.out.println("multiplicadorDescuentoGrasa: "+multiplicadorDescuentoGrasa);
            //Descuento variación ST
            int multiplicadorDescuentoSt = obtenerDescuentoSt(proveedor);
            double porcentajeDescuentoSt = multiplicadorDescuentoSt/100;
            System.out.println("multiplicadorDescuentoSt: "+multiplicadorDescuentoSt);

            //aplicar descuentos
            int descuento_1 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoLeche);
            int descuento_2 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoGrasa);
            int descuento_3 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoSt);

            System.out.println("descuentos ->"+descuento_1+" | "+descuento_2+" | "+descuento_3);

            int descuento = descuento_1+descuento_2+descuento_3;
            System.out.println(descuento);

            int pagoTotal = pagoAcopioLeche - descuento;

            //retencion
            int retencion = obtenerRetencion(pagoTotal);

            //pagoFinal
            int pagoFinal = pagoTotal-retencion;

            //actualizar valores anterior quincena
            actualizarPorcentajes(proveedor.getCodigo(),kilosLeche);

            //nueva entidad planilla
        }




        return null;
    }

    private void actualizarPorcentajes(String codigo,int kilos) {

        int stActual = subirPorcentajeService.obtenerStActual(codigo);

        int grasaActual = subirPorcentajeService.obtenerGrasaActual(codigo);

        registroQuincenaService.actualizarDatos(codigo,kilos,stActual,grasaActual);
    }

    private int obtenerRetencion(int pagoTotal) {
        int retencion = 0;
        if(pagoTotal > 950000){
            retencion = (int) Math.floor(pagoTotal*0.13);

        }
        return retencion;
    }

    private int obtenerDescuentoSt(ProveedorEntity proveedor) {
        //codigo del proveedor

        String codigo = proveedor.getCodigo();
        //obtener Porcentajes actuales
        int stActual = subirPorcentajeService.obtenerStActual(codigo);
        //obtener Porcentajes antiguos
        int stAntigua = registroQuincenaService.obtenerStAntigua(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(stAntigua,stActual);

        if(variacionPorcentual <= 0 && variacionPorcentual >= -6 ){
            return 0;
        }else if(variacionPorcentual <= -7 && variacionPorcentual >= -12 ){
            return 18;
        }else if(variacionPorcentual <= -13 && variacionPorcentual >= -35){
            return 27;
        }else if(variacionPorcentual <= -36){
            return 45;
        }


        return 0;
    }

    private int obtenerDescuentoGrasa(ProveedorEntity proveedor) {
        //codigo del proveedor
        String codigo = proveedor.getCodigo();
        //obtener Porcentajes actuales
        int grasaActual = subirPorcentajeService.obtenerGrasaActual(codigo);
        //obtener Porcentajes antiguos
        int grasaAntigua = registroQuincenaService.obtenerGrasaAntigua(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(grasaAntigua,grasaActual);

        if(variacionPorcentual <= 0 && variacionPorcentual >= -15 ){
            return 0;
        }else if(variacionPorcentual <= -16 && variacionPorcentual >= -25 ){
            return 12;
        }else if(variacionPorcentual <= -26 && variacionPorcentual >= -40){
            return 20;
        }else if(variacionPorcentual <= -41){
            return 30;
        }

        return 0;
    }
    private double calcularVariacionPorcentual(int valor1, int valor2){

        if(valor1 == 0){
            return 0;
        }else{
            return ((valor2-valor1)/valor1)*100;
        }
    }
    private int obtenerDescuentoLeche(ProveedorEntity proveedor) {
        //codigo del proveedor
        String codigo = proveedor.getCodigo();
        //obtener kilos actuales
        int kilosActuales = calcularCantidadKilosLeche(proveedor);
        //obtener kilos antiguos
        int kilos_antiguos = registroQuincenaService.getKilosByCodigo(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(kilos_antiguos,kilosActuales);

        if(variacionPorcentual <= 0 && variacionPorcentual >= -8 ){
            return 0;
        }else if(variacionPorcentual <= -9 && variacionPorcentual >= -25 ){
            return 7;
        }else if(variacionPorcentual <= -26 && variacionPorcentual >= -45){
            return 15;
        }else if(variacionPorcentual <= -46){
            return 30;
        }

        return 0;
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

        return 0;
    }

    private int calcularCantidadKilosLeche(ProveedorEntity proveedor) {
        String codigo = proveedor.getCodigo();

        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorCodigo(codigo);
        System.out.println(acopio);
        int cant = 0;
        int kilos;
        for(SubirDataEntity a:acopio){
            kilos = Integer.parseInt(a.getKls_leche());

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
