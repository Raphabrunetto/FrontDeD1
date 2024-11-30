package up.ddm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import personagem.GameCharacter
import up.ddm.R

class ListarPersonagensActivity : AppCompatActivity() {
    private lateinit var personagens: List<GameCharacter> // Armazena personagens criados
    private lateinit var recyclerViewPersonagens: RecyclerView // Referência ao RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_personagens)

        // Inicializar o RecyclerView
        recyclerViewPersonagens = findViewById(R.id.recyclerViewPersonagens)
        recyclerViewPersonagens.layoutManager = LinearLayoutManager(this) // Configure o LayoutManager aqui

        // Carregar personagens (do banco de dados ou de uma lista)
        personagens = carregarPersonagens()

        // Exibir personagens em um RecyclerView
        val adapter = MainActivity(personagens)
        recyclerViewPersonagens.adapter = adapter
    }

    private fun carregarPersonagens(): List<GameCharacter> {
        // Lógica para carregar personagens
        return listOf() // Retornar a lista de personagens
    }
}
