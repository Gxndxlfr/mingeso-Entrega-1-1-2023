package MilkStgo.example.demo.services;

import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.RegistroQuincenaEntity;
import MilkStgo.example.demo.repositories.ProveedorRepository;
import MilkStgo.example.demo.repositories.RegistroQuincenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroQuincenaService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private RegistroQuincenaRepository registroQuincenaRepository;

    public void guardarRegistroQuincena(String codigo, String grasa, String st,String kilos){
        RegistroQuincenaEntity registroQuincena = new RegistroQuincenaEntity();
        registroQuincena.setCodigo(codigo);
        registroQuincena.setGrasa(grasa);
        registroQuincena.setSt(st);
        registroQuincena.setKilos(kilos);
        registroQuincenaRepository.save(registroQuincena);
    }
    public void setAnteriorQuince() {

        List<RegistroQuincenaEntity> anteriorQuincena = registroQuincenaRepository.findAll();
        List<ProveedorEntity> proveedores = proveedorRepository.findAll();

        int sizeAQ = anteriorQuincena.size();
        int sizeP = proveedores.size();

        if(sizeAQ == 0){ //si no existe quincena registrada
            if(sizeP != 0){ //si tenemos empleados
                for (ProveedorEntity p:proveedores){
                    RegistroQuincenaEntity newRegistroQuincena = new RegistroQuincenaEntity();

                    guardarRegistroQuincena(p.getCodigo(),"0","0","0");


                }
            }


        }
    }

    public int getKilosByCodigo(String codigo) {
       return Integer.parseInt(registroQuincenaRepository.getByCodigo(codigo).getKilos());
    }

    public int obtenerGrasaAntigua(String codigo) {
        return Integer.parseInt(registroQuincenaRepository.getByCodigo(codigo).getGrasa());
    }

    public int obtenerStAntigua(String codigo) {
        return Integer.parseInt(registroQuincenaRepository.getByCodigo(codigo).getSt());
    }

    public void actualizarDatos(String codigo, int kilos, int stActual, int grasaActual) {

        //eliminar por codigo
        registroQuincenaRepository.deleteByCodigo(codigo);
        //nueva entidad por codigo
        RegistroQuincenaEntity nuevoRegistro = new RegistroQuincenaEntity();
        nuevoRegistro.setKilos(String.valueOf(kilos));
        nuevoRegistro.setSt(String.valueOf(stActual));
        nuevoRegistro.setGrasa(String.valueOf(grasaActual));

        registroQuincenaRepository.save(nuevoRegistro);


    }
}
