package es.lafe.fhir.custom;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.StringType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity // Marca la clase como una entidad JPA
@Table(name = "recurso_personalizado") // Nombre de la tabla en la base de datos
@ResourceDef(name = "RecursoPersonalizado", profile = "http://miempresa.com/StructureDefinition/recursopersonalizado")
public class RecursoPersonalizado extends DomainResource {

    private static final long serialVersionUID = 1L;

    @Child(name = "campoPersonalizado")
    @Description(shortDefinition = "Un campo personalizado para el recurso")
    private StringType campoPersonalizado;

    // Getter y Setter
    public StringType getCampoPersonalizado() {
        if (campoPersonalizado == null) {
            campoPersonalizado = new StringType();
        }
        return campoPersonalizado;
    }

    public void setCampoPersonalizado(StringType campoPersonalizado) {
        this.campoPersonalizado = campoPersonalizado;
    }

    // Implementación del método copy() requerido por DomainResource
    @Override
    public RecursoPersonalizado copy() {
        RecursoPersonalizado copia = new RecursoPersonalizado();
        copia.campoPersonalizado = campoPersonalizado != null ? campoPersonalizado.copy() : null;
        return copia;
    }

    // Define el tipo de recurso para HAPI FHIR
    @Override
    public ResourceType getResourceType() {
        return ResourceType.Basic; // Cambia "Basic" por otro tipo si corresponde
    }
}
