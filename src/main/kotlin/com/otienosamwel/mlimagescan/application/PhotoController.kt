package com.otienosamwel.mlimagescan.application

import com.google.cloud.vision.v1.Feature
import com.otienosamwel.mlimagescan.domain.model.Photo
import com.otienosamwel.mlimagescan.domain.repository.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gcp.vision.CloudVisionTemplate
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource
import org.springframework.core.io.WritableResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
class PhotoController @Autowired constructor(
    private val photoRepository: PhotoRepository,
    private val visionTemplate: CloudVisionTemplate,
    private val ctx: ApplicationContext
) {
    //configure bucket uri
    private val bucket = "gs://personal-playground-319423.appspot.com"

    @PostMapping("/photo")
    fun create(@RequestBody photo: Photo) {
        photoRepository.save(photo)
    }

    @PostMapping("/upload")
    fun upload(@RequestParam("file") file: MultipartFile): Photo {
        val id = UUID.randomUUID().toString()
        val uri = "$bucket/$id"

        val gcs = ctx.getResource(uri) as WritableResource

        file.inputStream.use { inputStream ->
            gcs.outputStream.use {
                inputStream.copyTo(it)
            }
        }
        val response = visionTemplate.analyzeImage(file.resource, Feature.Type.LABEL_DETECTION)
        println(response)
        val labels = response.labelAnnotationsList.take(5).map { it.description }.joinToString(", ")
        return photoRepository.save(Photo(id = id, url = "/image/$id", label = labels))
    }

    @GetMapping("/image/{id}")
    fun getImage(@PathVariable id: String): ResponseEntity<Resource> {
        val resource = ctx.getResource("$bucket/$id")

        return if (resource.exists()) ResponseEntity.ok(resource) else ResponseEntity(HttpStatus.NOT_FOUND)
    }
}