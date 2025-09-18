package br.dgs.hanckathon.ia_market.product.controller;

import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import br.dgs.hanckathon.ia_market.externals.anymarket.client.AnymarketClient;
import br.dgs.hanckathon.ia_market.externals.huggingface.IAGeneratorService;
import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import br.dgs.hanckathon.ia_market.product.model.Product;
import br.dgs.hanckathon.ia_market.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;
    private final AnymarketClient anymarketClient;
    private final IAGeneratorService iaService;


    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.listProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/adjust")
    public String adjustProductWithIA(@ModelAttribute AdjustedProduct product, Model model) {
        String token = "Bearer YOUR_TOKEN";
        CategoryResponse categoryResponse = anymarketClient.getCategory(product.getMarketPlace(), "123", token);
        CategoryAttributesResponse attributesResponse = anymarketClient.getCategoryAttributes(123L, token);

        AdjustedProduct adjusted = iaService.adjustProduct(product, categoryResponse, attributesResponse);

        model.addAttribute("product", product);
        model.addAttribute("adjustedProduct", adjusted);
        return "edit_product";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model model) {
        Product product = new Product(id, "Produto Exemplo", "Descrição Exemplo"); // Mock
        AdjustedProduct adjustedProduct = new AdjustedProduct(product.getId(), product.getName(), product.getDescription(), "");
        model.addAttribute("product", adjustedProduct);
        return "edit_product";
    }


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute AdjustedProduct product) {
        productService.saveProduct(product);
        return "redirect:/";
    }
}
