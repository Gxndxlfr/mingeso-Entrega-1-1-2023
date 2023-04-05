package MilkStgo.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planilla")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanillaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;
    private String quincena;
    private String codigo;
    private String Nombre;
    private int klsLeche;
    private int cantDias;
    private int promedioKlsLeche;
    private int varLeche;
    private int varGrasa;
    private int varSt;
    private int pagoLeche;
    private int pagoGrasa;
    private int pagoSt;
    private int bonificacionFrecuencia;
    private int descuentoLeche;
    private int descuentoGrasa;
    private int descuentoSt;
    private int pagoTotal;
    private int retencion;
    private int pagoFinal;

}
