package app;

import java.util.Vector;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PatientController {

    private final Vector patients = new Vector(); 

    public PatientController() {
        patients.add(new Patient("Ronald","McDonald"));
        patients.add(new Patient("Jimmy","Johns"));
        patients.add(new Patient("Colonel","Sanders"));
    }

    @RequestMapping(value = "/api/v1/patients", method = RequestMethod.GET)
    public Vector getPatients() {
        return patients; 
    }

    @RequestMapping(value = "/api/v1/patients", method = RequestMethod.POST)
    public Patient postPatients(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName) {
        Patient patient = new Patient(firstName,lastName);
        patients.add(patient);
        return patient;
    }

    @RequestMapping(value = "/api/v1/patients/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable int id) {
        Patient patient;
        try {
            patient = (Patient)patients.get(id - 1);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ResourceNotFoundException();
        }
        return patient;
    }

    @RequestMapping(value = "/api/v1/patients/{id}", method = RequestMethod.PUT)
    public Patient putPatient(@PathVariable int id, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName) {
        Patient patient;
        try {
            patient = (Patient)patients.get(id - 1);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ResourceNotFoundException();
        }
        return patient;
    }

    @RequestMapping(value = "/api/v1/patients/{id}", method = RequestMethod.DELETE)
    public Patient deletePatient(@PathVariable int id) {
        Patient patient;
        try {
            patient = (Patient)patients.get(id - 1);
            patients.removeElementAt(id - 1);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new ResourceNotFoundException();
        }
        return patient;
    }
}
