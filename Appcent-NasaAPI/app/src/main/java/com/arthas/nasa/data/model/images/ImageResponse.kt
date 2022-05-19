package com.arthas.nasa.data.model.images

import com.arthas.nasa.data.model.photos.Photos
import java.io.Serializable

class ImageResponse(
    val photos: List<Photos>
) : Serializable