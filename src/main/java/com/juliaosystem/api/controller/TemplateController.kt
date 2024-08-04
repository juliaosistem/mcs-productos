package com.juliaosystem.api.controller

import com.common.lib.api.controller.CrudController
import com.common.lib.api.dtos.request.RegisterUserDTO
import com.juliaosystem.infraestructure.entitis.Entity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/ejemplo")
class TemplateController():CrudController<RegisterUserDTO,RegisterUserDTO,Entity,UUID> {
}