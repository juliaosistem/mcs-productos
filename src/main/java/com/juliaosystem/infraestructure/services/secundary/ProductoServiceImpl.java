package com.juliaosystem.infraestructure.services.secundary;

import com.common.lib.api.response.PlantillaResponse;
import com.juliaosystem.api.dtos.ProductoDTO;

import java.util.UUID;

public interface ProductoServiceImpl {

     ProductoDTO getfindByIdForUpdate(UUID id);
     PlantillaResponse<ProductoDTO> updateCantidad(ProductoDTO productoDTO);
}
