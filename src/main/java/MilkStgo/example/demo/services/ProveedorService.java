package MilkStgo.example.demo.services;

import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public void guardarProveedor(String codigo, String nombre, String categoria){
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCodigo(codigo);
        proveedor.setNombre(nombre);
        proveedor.setCategoria(categoria);
        proveedorRepository.save(proveedor);
    }

    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }
}