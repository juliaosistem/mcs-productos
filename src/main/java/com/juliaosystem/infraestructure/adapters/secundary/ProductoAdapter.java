package com.juliaosystem.infraestructure.adapters.secundary;

import com.common.lib.api.mappers.GenericMapper;
import com.common.lib.api.response.PlantillaResponse;
import com.common.lib.utils.UserResponses;
import com.common.lib.utils.enums.MensajesRespuesta;
import com.common.lib.utils.enums.ResponseType;
import com.common.lib.utils.errors.AbtractError;
import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.api.mappers.ProductMapper;
import com.juliaosystem.infraestructure.entitis.Producto;
import com.juliaosystem.infraestructure.repository.DefualtRepository;
import com.juliaosystem.infraestructure.repository.ProductoRepository;
import com.juliaosystem.infraestructure.services.secundary.ProductoServiceImpl;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductoAdapter   extends DefaultAdapter<ProductoDTO,ProductoDTO, Producto, UUID>  implements ProductoServiceImpl {

    private final  ProductoRepository productoRepository;

    private  final  AbtractError abstractErrorProductoAdapter;
    private final ProductMapper mapper;
    private  final  UserResponses<ProductoDTO> userResponses;

    public ProductoAdapter(@NotNull GenericMapper<ProductoDTO, ProductoDTO, Producto> mapper, @NotNull AbtractError abstractError,
                           @NotNull UserResponses<ProductoDTO> userResponses, ProductoDTO resClass, Producto entityClass,
                           @NotNull DefualtRepository<Producto, UUID> defaultRepository,
                           ProductoRepository productoRepository, ProductMapper productMapper) {
        super(mapper, abstractError, userResponses, resClass, entityClass, defaultRepository);
        this.productoRepository = productoRepository;
        this.abstractErrorProductoAdapter = abstractError;
        this.mapper = productMapper;
        this.userResponses = userResponses;
    }

    @Override
    @Transactional
    public ProductoDTO getfindByIdForUpdate(UUID id){
         try {
              return  mapper.productToProductDTO(productoRepository.findByIdForUpdate(id));
         }catch (Exception e) {
             abstractErrorProductoAdapter.logError(e);
             return ProductoDTO.builder().build();
         }
    }

    @Override
    @Transactional
    public PlantillaResponse<ProductoDTO> updateCantidad(ProductoDTO productoDTO){
          try {

              var product = productoRepository.updateCantidad(productoDTO.getId(), productoDTO.getCantidad());
            if(product != 0){
                abstractErrorProductoAdapter.logInfo("ProductoAdapter.updateCantidad()" + MensajesRespuesta.ACTUALIZADO);
                return userResponses.buildResponse(ResponseType.UPDATED.getCode(), productoDTO);
            }else {
                abstractErrorProductoAdapter.logInfo("ProductoAdapter.updateCantidad()" + MensajesRespuesta.FALLO.getMensaje());
                return  userResponses.buildResponse(ResponseType.FALLO.getCode(), productoDTO);
            }
          }catch (Exception e) {
              abstractErrorProductoAdapter.logError(e);
              return  userResponses.buildResponse(ResponseType.FALLO.getCode(), productoDTO);
          }
     }
}
