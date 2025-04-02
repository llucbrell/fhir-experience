package es.lafe.fhir.custom;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;

@ResourceDef(name = "Patient", profile = "http://miempresa.com/StructureDefinition/mipaciente")
public class MiPaciente extends Patient {

    private static final long serialVersionUID = 1L;

    @Child(name = "nombreMascota")
    @Description(shortDefinition = "El nombre de la mascota del paciente")
    private StringType nombreMascota;

    // Getter para 'nombreMascota'
    public StringType getNombreMascota() {
        if (nombreMascota == null) {
            nombreMascota = new StringType();
        }
        return nombreMascota;
    }

    // Setter para 'nombreMascota'
    public void setNombreMascota(StringType nombreMascota) {
        System.out.println("Asignando nombre a la mascota: " + nombreMascota.getValue());
        this.nombreMascota = nombreMascota;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && (nombreMascota == null || nombreMascota.isEmpty());
    }
}
