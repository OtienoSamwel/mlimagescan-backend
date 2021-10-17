package com.otienosamwel.mlimagescan.domain.repository

import com.otienosamwel.mlimagescan.domain.model.Photo
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface PhotoRepository : DatastoreRepository<Photo, String>