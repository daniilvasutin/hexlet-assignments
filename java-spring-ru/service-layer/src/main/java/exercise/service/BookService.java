package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> findAll() {
        var books = bookRepository.findAll();
        var result = books.stream().map(book -> bookMapper.map(book)).collect(Collectors.toList());

        return result;
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        var bookDTO = bookMapper.map(book);

        return bookDTO;
    }

    public BookDTO create(BookCreateDTO createDTO) {
//        var author = authorRepository.findById(createDTO.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("not found"));
        var book = bookMapper.map(createDTO);
//        book.setAuthor(author);
        bookRepository.save(book);
        var bookDTO = bookMapper.map(book);

        return bookDTO;
    }

    public BookDTO update(BookUpdateDTO updateDTO, Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        bookMapper.update(updateDTO,book);

        bookRepository.save(book);
        var bookDTO = bookMapper.map(book);

        return bookDTO;

    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
