package br.dgs.hanckathon.ia_market.product.controller;

import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import br.dgs.hanckathon.ia_market.commons.model.ProductSKURequest;
import br.dgs.hanckathon.ia_market.commons.model.ProductSKUResponse;
import br.dgs.hanckathon.ia_market.externals.anymarket.client.AnymarketClient;
import br.dgs.hanckathon.ia_market.externals.huggingface.IAGeneratorService;
import br.dgs.hanckathon.ia_market.externals.sandbox.SandboxClient;
import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
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

    private final AnymarketClient anymarketClient;
    private final IAGeneratorService iaService;
    private final SandboxClient sandboxClient;


    @GetMapping("/")
    public String index(Model model) {
        List<ProductSKUResponse> products = sandboxClient.getProductSKU(349464315);
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/adjust")
    public String adjustProductWithIA(@ModelAttribute AdjustedProduct product, Model model) {
        List<CategoryResponse> categoryResponse = anymarketClient.getCategory();
        List<CategoryAttributesResponse> attributesResponse = anymarketClient.getCategoryAttributes();

        AdjustedProduct adjusted = iaService.adjustProduct(product, categoryResponse, attributesResponse);

        model.addAttribute("product", product);
        model.addAttribute("adjustedProduct", adjusted);
        return "edit_product";
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model model) {
        List<ProductSKUResponse> products = sandboxClient.getProductSKU(349464315);
        AdjustedProduct adjustedProduct = new AdjustedProduct(products.get(0), "MERCADO_LIVRE");
        model.addAttribute("product", adjustedProduct);
        return "edit_product";
    }


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute AdjustedProduct product) {
        sandboxClient.patchProductSKU(349464315, product.getId(), new ProductSKURequest(product));
        return "redirect:/";
    }
}
