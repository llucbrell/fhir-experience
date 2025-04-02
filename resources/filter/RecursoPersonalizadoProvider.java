package es.lafe.fhir.custom;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.StringType;

import java.util.ArrayList;
import java.util.List;

public class RecursoPersonalizadoProvider implements IResourceProvider {

    @Override
    public Class<RecursoPersonalizado> getResourceType() {
        return RecursoPersonalizado.class;
    }

    @Create
    public MethodOutcome createRecursoPersonalizado(@ResourceParam RecursoPersonalizado recurso) {
        System.out.println("Creando RecursoPersonalizado: " + recurso.getCampoPersonalizado().getValue());

        // Asigna un ID único al recurso creado
        recurso.setId("1");

        // Devuelve el recurso creado como parte del resultado
        MethodOutcome outcome = new MethodOutcome();
        outcome.setCreated(true);
        outcome.setResource(recurso);
        

        System.out.println("CREADO RECURSO");
        return outcome;
    }

    @Search
    public List<RecursoPersonalizado> searchRecursoPersonalizado(RequestDetails theRequestDetails) {
        System.out.println("Realizando búsqueda de RecursoPersonalizado...");

        // Crea una lista de ejemplo con recursos personalizados
        List<RecursoPersonalizado> recursos = new ArrayList<>();
        RecursoPersonalizado recurso = new RecursoPersonalizado();
        recurso.setCampoPersonalizado(new StringType("Ejemplo de recurso personalizado"));

        // Asigna un ID único al recurso
        recurso.setId("1");

        recursos.add(recurso);
        System.out.println("BUSQUEDA REALIZADA");

        return recursos;
    }

    @Read
    public RecursoPersonalizado readRecursoPersonalizado(@IdParam IdType id) {
        System.out.println("Leyendo RecursoPersonalizado con ID: " + id.getIdPart());

        // Crea un recurso con el ID solicitado
        RecursoPersonalizado recurso = new RecursoPersonalizado();
        recurso.setCampoPersonalizado(new StringType("Valor personalizado"));
        recurso.setId(id.getIdPart()); // Asigna el ID proporcionado
        System.out.println("BUSQUEDA REALIZADA");

        return recurso;
    }

    @Update
    public MethodOutcome updateRecursoPersonalizado(@IdParam IdType id, @ResourceParam RecursoPersonalizado recurso) {
        System.out.println("Actualizando RecursoPersonalizado con ID: " + id.getIdPart());

        // Asigna el ID al recurso actualizado
        recurso.setId(id.getIdPart());

        MethodOutcome outcome = new MethodOutcome();
        outcome.setResource(recurso);

        return outcome;
    }

    @Delete
    public MethodOutcome deleteRecursoPersonalizado(@IdParam IdType id) {
        System.out.println("Eliminando RecursoPersonalizado con ID: " + id.getIdPart());

        MethodOutcome outcome = new MethodOutcome();
        return outcome;
    }
}
