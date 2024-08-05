package com.juliaosystem.infraestructure.repository;

import com.juliaosystem.infraestructure.entitis.Producto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductoRepository extends DefualtRepository<Producto, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Producto findByIdForUpdate(UUID id);

    @Modifying
    @Query("UPDATE Producto p SET p.cantidad = :cantidad WHERE p.id = :id")
    int updateCantidad(UUID id, int cantidad);
}
