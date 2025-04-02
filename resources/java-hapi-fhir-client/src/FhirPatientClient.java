import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateType;

import java.util.Scanner;

public class FhirPatientClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos del paciente
        System.out.print("Nombre: ");
        String firstName = scanner.nextLine();

        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();

        System.out.print("Teléfono (opcional): ");
        String phone = scanner.nextLine();

        System.out.print("Correo electrónico (opcional): ");
        String email = scanner.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();

        System.out.print("URL base del servidor FHIR acabado en /: ");
        String serverUrl = scanner.nextLine();

        // Crear recurso Patient en formato FHIR usando HAPI FHIR
        Patient patient = new Patient();

        // Configurar nombre del paciente
        HumanName name = new HumanName();
        name.setFamily(lastName).addGiven(firstName);
        patient.addName(name);

        // Configurar contacto telefónico si está presente
        if (!phone.isEmpty()) {
            ContactPoint phoneContact = new ContactPoint();
            phoneContact.setSystem(ContactPoint.ContactPointSystem.PHONE);
            phoneContact.setValue(phone);
            phoneContact.setUse(ContactPoint.ContactPointUse.MOBILE);
            patient.addTelecom(phoneContact);
        }

        // Configurar correo electrónico si está presente
        if (!email.isEmpty()) {
            ContactPoint emailContact = new ContactPoint();
            emailContact.setSystem(ContactPoint.ContactPointSystem.EMAIL);
            emailContact.setValue(email);
            patient.addTelecom(emailContact);
        }

        // Configurar fecha de nacimiento
        if (!birthDate.isEmpty()) {
            patient.setBirthDateElement(new DateType(birthDate));
        }

        // Configurar el cliente FHIR
        FhirContext context = FhirContext.forR4();
        IGenericClient client = context.newRestfulGenericClient(serverUrl);

        // Enviar el recurso al servidor FHIR mediante una solicitud POST para crear un nuevo paciente
        try {
            MethodOutcome outcome = client.create().resource(patient).execute();
            if (outcome.getCreated() != null && outcome.getCreated()) {
                System.out.println("Paciente creado exitosamente");
                System.out.println("ID del paciente: " + outcome.getId().getIdPart());
            } else {
                System.out.println("Error al crear el paciente");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}
