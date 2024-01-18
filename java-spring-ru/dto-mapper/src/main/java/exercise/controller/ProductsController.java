package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public List<ProductDTO> show() {
        var prodList = productRepository.findAll();
        List<ProductDTO> productsDTO = prodList.stream().map(product -> productMapper.map(product)).toList();
        return productsDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO createData) {
        var product = productMapper.map(createData); // в базе name приходит title // из title -> name

        productRepository.save(product);
        var productDTO = productMapper.map(product); // из name -> title

        return productDTO;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO showById(@PathVariable Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));

        return productMapper.map(product);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@RequestBody ProductUpdateDTO updateData, @PathVariable Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));

        productMapper.update(updateData, product); // из title -> name
        productRepository.save(product);

        var prodDTO = productMapper.map(product);

        return prodDTO;
    }
    // END
}
