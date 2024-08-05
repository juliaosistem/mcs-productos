package com.juliaosystem.infraestructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface DefualtRepository<E,I>: JpaRepository<E,I> {
     fun findByIdBussines(idBussines:Long): List<E>
}