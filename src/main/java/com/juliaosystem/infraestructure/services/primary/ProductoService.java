package com.juliaosystem.infraestructure.services.primary;

import com.common.lib.api.response.PlantillaResponse;
import com.common.lib.infraestructure.services.primary.CrudPrimaryService;
import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.infraestructure.adapters.primary.ProductoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService   implements CrudPrimaryService<ProductoDTO,ProductoDTO, UUID> {

    private final ProductoImpl productoImpl;

    @Override
    public PlantillaResponse<ProductoDTO> add(ProductoDTO productoDTO, UUID id) {
        return productoImpl.add(productoDTO, id);
    }

    @Override
    public PlantillaResponse<ProductoDTO> all(UUID id, Long idBussines) {
        return productoImpl.all(id, idBussines);
    }

    @Override
    public PlantillaResponse<ProductoDTO> delete(UUID id) {
        return productoImpl.delete(id);
    }

    @Override
    public PlantillaResponse<ProductoDTO> update(ProductoDTO productoDTO) {
        return productoImpl.update(productoDTO);
    }
}