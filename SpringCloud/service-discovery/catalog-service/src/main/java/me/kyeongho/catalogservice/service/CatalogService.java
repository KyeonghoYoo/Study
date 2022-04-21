package me.kyeongho.catalogservice.service;

import me.kyeongho.catalogservice.entity.CatalogEntity;

public interface CatalogService {

    Iterable<CatalogEntity> getAllCatalogs();
}
