package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO createDTO) {
        var contact = crreateDTOToEntity(createDTO);
        contactRepository.save(contact);

        return entityToDTO(contact);
    }

    private Contact crreateDTOToEntity (ContactCreateDTO createDTO) {
        var contact = new Contact();
        contact.setFirstName(createDTO.getFirstName());
        contact.setLastName(createDTO.getLastName());
        contact.setPhone(createDTO.getPhone());
        return contact;
    }

    private ContactDTO entityToDTO (Contact entity) {
        var contactDTO = new ContactDTO();
        contactDTO.setId(entity.getId());
        contactDTO.setPhone(entity.getPhone());
        contactDTO.setLastName(entity.getLastName());
        contactDTO.setFirstName(entity.getFirstName());
        contactDTO.setUpdatedAt(entity.getUpdatedAt());
        contactDTO.setCreatedAt(entity.getCreatedAt());

        return contactDTO;
    }
    // END
}
