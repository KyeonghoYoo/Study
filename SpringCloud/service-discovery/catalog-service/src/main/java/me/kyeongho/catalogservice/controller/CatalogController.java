package me.kyeongho.catalogservice.controller;

import lombok.RequiredArgsConstructor;
import me.kyeongho.catalogservice.entity.CatalogEntity;
import me.kyeongho.catalogservice.service.CatalogService;
import me.kyeongho.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/catalog-service/")
@RequiredArgsConstructor
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping(path = "/health_check")
    public String status() {
        return String.format("it's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping(path = "/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    /**
     * 전체 카탈로그 조회
     */
    @GetMapping(path = "/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getAllCatalogs() {
        Iterable<CatalogEntity> catalogEntityIterable = catalogService.getAllCatalogs();

        List<ResponseCatalog> responseCatalogs = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        catalogEntityIterable
                .forEach(catalogEntity ->
                        responseCatalogs.add(mapper.map(catalogEntity, ResponseCatalog.class))
                );

        return ResponseEntity.ok(responseCatalogs);
    }
}
