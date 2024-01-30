package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> index() {
        var authors = authorRepository.findAll();
        var authorsDTO = authors.stream().map(author -> authorMapper.map(author)).collect(Collectors.toList());

        return authorsDTO;
    }

    public AuthorDTO show(Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return authorMapper.map(author);
    }

    public AuthorDTO create(AuthorCreateDTO createDTO) {
        var author = authorMapper.map(createDTO);
        authorRepository.save(author);

        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO updateDTO, Long id) {
//        var author = authorRepository.findByFirstNameAndLastName(updateDTO.getFirstName().get(), updateDTO.getLastName().get()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        authorMapper.update(updateDTO ,author);

        authorRepository.save(author);

        return authorMapper.map(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }


    // END
}
