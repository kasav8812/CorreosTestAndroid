package com.example.correostestandroid.Adapters
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "Mail")
data class MailEntity (
    @ColumnInfo(name = "emisor")
    val emisor : String,
    @ColumnInfo(name = "correoEmisor")
    val correoEmisor : String,
    @ColumnInfo(name = "asunto")
    val asunto: String,
    @ColumnInfo(name = "mensaje")
    val mensaje : String,
    @ColumnInfo(name = "hora")
    val hora: String,
    @ColumnInfo(name = "leido")
    var leido: Boolean,
    @ColumnInfo(name = "destacado")
    var destacado: Boolean,
    @ColumnInfo(name = "spam")
    var spam: Boolean,
    @ColumnInfo(name = "eliminado")
    var eliminado: Boolean
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

    constructor():this("","","","","",false,false,false,true)
}
