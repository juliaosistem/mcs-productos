package com.juliaosystem.api.controller

import com.common.lib.api.controller.CrudController
import com.common.lib.api.response.PlantillaResponse
import com.juliaosystem.api.dtos.ProductoDTO
import com.juliaosystem.infraestructure.entitis.Producto
import com.juliaosystem.infraestructure.services.primary.ProductoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/productos")
class ProductoController(
  private  val productoService: ProductoService
):CrudController<ProductoDTO,ProductoDTO,Producto,UUID> {

    override fun add(
        entidad: ProductoDTO,
        id: UUID?,
        ip: String,
        dominio: String,
        usuario: String,
        idBussines: Long,
        proceso: String
    ): ResponseEntity<PlantillaResponse<ProductoDTO>> {
        val response = productoService.add(entidad, entidad.id)
        return ResponseEntity(response, response.httpStatus)

    }

    override fun all(
        id: UUID?,
        ip: String,
        dominio: String,
        usuario: String,
        idBussines: Long?,
        proceso: String
    ): ResponseEntity<PlantillaResponse<ProductoDTO>> {
        val response = productoService.all(id,idBussines)
        return ResponseEntity(response, response.httpStatus)
    }

    override fun update(
        entidad: ProductoDTO,
        ip: String,
        dominio: String,
        usuario: String,
        idBussines: Long,
        proceso: String
    ): ResponseEntity<PlantillaResponse<ProductoDTO>> {
        val response = productoService.update(entidad)
        return ResponseEntity(response, response.httpStatus)
    }
}