package up.ddm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atributos")
class Atributos {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var pontos = 27
    var forca: Int = 8
    var destreza: Int = 8
    var constituicao: Int = 8
    var inteligencia: Int = 8
    var sabedoria: Int = 8
    var carisma: Int = 8

    fun setAtributo(atributo: Int, valor: Int) {
        if (valor < 8 || valor > 16) {
            throw IllegalArgumentException("Valor deve estar entre 8 e 16")
        }

        val custo = calcularCusto(valor)
        if (custo > pontos) {
            throw IllegalArgumentException("Pontos insuficientes para definir $atributo como $valor")
        }

        // Reduzir os pontos disponíveis
        pontos -= custo

        // Atualizar o atributo específico
        fun atualizarAtributo(atributo: String, valor: Int) {
            when (atributo.lowercase()) {
                "forca" -> forca = valor
                "destreza" -> destreza = valor
                "constituição" -> constituicao = valor
                "inteligência" -> inteligencia = valor
                "sabedoria" -> sabedoria = valor
                "carisma" -> carisma = valor
                else -> throw IllegalArgumentException("Atributo desconhecido: $atributo")
            }
        }

    }

    private fun calcularCusto(valor: Int): Int {
        return when {
            valor < 13 ->  valor - 8 // Custo de 1 ponto para valores entre 8 e 12
            else -> 2 * (valor - 10) + 5 // Custo de 2 pontos para valores a partir de 13
        }
    }

    fun getPontosDisponiveis(): Int {
        return pontos
    }

    fun resetarAtributos() {
        forca = 8
        destreza = 8
        constituicao = 8
        inteligencia = 8
        sabedoria = 8
        carisma = 8
        pontos = 27 // Resetar pontos
    }
}
