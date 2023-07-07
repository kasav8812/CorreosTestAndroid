package com.example.correostestandroid.Adapters

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Mail( val emisor : String,
                     val correoEmisor : String,
                     val asunto: String,
                     val mensaje : String,
                     val hora: String,
                     val leido: Boolean,
                     val destacado: Boolean,
                     val spam: Boolean) : java.io.Serializable{
                            constructor() : this("","","","","",false,false,false)
}

