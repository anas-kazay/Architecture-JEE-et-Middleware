package ma.enset.hospital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") int page,
                        @RequestParam(name="size",defaultValue ="4") int size,
                        @RequestParam(name="keyword",defaultValue = "") String kw
                        ){
        //int p = (page==0)?0:page-1;
        Page<Patient> patientPage = patientRepository.findByNomContains(kw,PageRequest.of(page-1,size));
        model.addAttribute("listPatients",patientPage.getContent());
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);
        model.addAttribute("currenctPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam Long id,
                         @RequestParam(defaultValue = "") String keyword,
                         @RequestParam(defaultValue = "1") int page){
    patientRepository.deleteById(id);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public String home(){
        return "redirect:/user/index";
    }

    @GetMapping("/admin/formPatients")
    @PreAuthorize("hasRole('ADMIN')")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }

    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/user/index?keyword="+patient.getNom();

    }

    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPatient(Model model,@RequestParam(name = "id") Long id){
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient",patient);
        return "editPatients";
    }
}
