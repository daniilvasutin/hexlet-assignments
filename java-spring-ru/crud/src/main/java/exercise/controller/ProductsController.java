package exercise.controller;

import java.security.PublicKey;
import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.BadRequest;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        var productsDTO = products.stream().map(prod -> productMapper.map(prod)).toList();

        return productsDTO;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO show(@PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        var productDTO = productMapper.map(product);
        productDTO.setCategoryName(product.getCategory().getName());

        return productDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        var categoryId = productCreateDTO.getCategoryId();
        var noup = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequest("no such category"));

        var product = productMapper.map(productCreateDTO);
        productRepository.save(product);

        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@Valid @RequestBody ProductUpdateDTO updateDTO, @PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        var category = categoryRepository.findById(updateDTO.getCategoryId().get())
                .orElseThrow(() -> new BadRequest("no such category"));
        productMapper.update(updateDTO, product);
        product.setCategory(category);
        productRepository.save(product);

        var productDTO = productMapper.map(product);
        return productDTO;

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }
    // END
}
