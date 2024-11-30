package up.ddm

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "personagem",
    foreignKeys = [ForeignKey(
        entity = Atributos::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("atributosId"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["atributosId"])] // Adicionando índice para atributosId
)
data class Personagem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var nome: String = "",
    var atributosId: Int = 0,
    var raca: String = "" // Usando String para representar a raça
) {
    // Se precisar de um método para converter `raca` de volta para `IRaca`, adicione-o aqui
}
