package br.dgs.hanckathon.ia_market.product.service;

import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import br.dgs.hanckathon.ia_market.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> listProducts() {
        return List.of(
                new Product("P001", "Notebook Gamer", "Notebook Gamer 16GB RAM, SSD 512GB, RTX 3060"),
                new Product("P002", "Smartphone Top", "Smartphone com tela AMOLED, 128GB, 8GB RAM"),
                new Product("P003", "Fone Bluetooth", "Fone Bluetooth com cancelamento de ruído"),
                new Product("P004", "Smartwatch", "Smartwatch com monitoramento cardíaco e GPS"),
                new Product("P005", "Câmera DSLR", "Câmera DSLR 24MP com lente 18-55mm")
        );
    }


    public AdjustedProduct saveProduct(AdjustedProduct product) {
        System.out.println("Produto salvo: " + product.getName() + " para marketplace " + product.getMarketPlace());
        return product;
    }
}
