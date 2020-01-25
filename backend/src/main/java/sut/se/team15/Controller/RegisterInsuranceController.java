package sut.se.team15.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Collection;
import java.util.stream.Collectors;


import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RegisterInsuranceController {

    @Autowired
    private final RegisterInsuranceRepository registerInsuranceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CreateInsuranceRepository createInsuranceRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private UserRepository userRepository;

    RegisterInsuranceController(RegisterInsuranceRepository registerInsuranceRepository) {
        this.registerInsuranceRepository = registerInsuranceRepository;
    }

    @GetMapping("/registerInsurance")
    public Collection<RegisterInsurance> registerInsurances() {
        return registerInsuranceRepository.findAll().stream().collect(Collectors.toList());
    }

    
    @PutMapping("/registerInsurance/{registerid}/{Userid}/{Insuranceid}/{Personid}/{Titleid}/{name}/{Surname}")
    public RegisterInsurance newsaveChangeInsurance(RegisterInsurance newsaveChangeInsurance, 
    @PathVariable long registerid,
    @PathVariable long Userid,
    @PathVariable long Insuranceid, 
     @PathVariable long Personid,
    @PathVariable long Titleid,
    @PathVariable String name, 
    @PathVariable String Surname) {
    
       
        CreateInsurance createInsurance = createInsuranceRepository.findById(Insuranceid);
        Title title = titleRepository.findById(Titleid);
        Person person  = personRepository.findById(Personid);
        User user = userRepository.findById(Userid);
      
        newsaveChangeInsurance.setRegisterInsuranceId(registerid);
        newsaveChangeInsurance.setUser(user);
        newsaveChangeInsurance.setCreateInsurance(createInsurance);
        newsaveChangeInsurance.setPerson(person);
        newsaveChangeInsurance.setTitle(title);
        newsaveChangeInsurance.setName(name);
        newsaveChangeInsurance.setSurname(Surname);
        return registerInsuranceRepository.save(newsaveChangeInsurance);
    }

    @PostMapping("/registerInsurance/{User_id}/{Insurance_id}/{Person_id}/{Title_id}/{Firstname}/{Surname}")
    public RegisterInsurance newRegisterInsurance(RegisterInsurance newRegisterInsurance, 
    @PathVariable long User_id,
    @PathVariable long Insurance_id, 
     @PathVariable long Person_id,
    @PathVariable long Title_id,
    @PathVariable String Firstname, 
    @PathVariable String Surname) {

        CreateInsurance createInsurance = createInsuranceRepository.findById(Insurance_id);
        Title title = titleRepository.findById(Title_id);
        Person person  = personRepository.findById(Person_id);
        User user = userRepository.findById(User_id);
      
        newRegisterInsurance.setUser(user);
        newRegisterInsurance.setCreateInsurance(createInsurance);
        newRegisterInsurance.setPerson(person);
        newRegisterInsurance.setTitle(title);
        newRegisterInsurance.setName(Firstname);
        newRegisterInsurance.setSurname(Surname);
       

        return registerInsuranceRepository.save(newRegisterInsurance);
    }
}