package up.ddm

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import personagem.DistribuicaoPontos
import personagem.GameCharacter
import Interfaces.IRaca
import Interfaces.IClass
import personagem.Archer
import personagem.Dwarf
import personagem.Elf
import personagem.Human
import personagem.Mage
import personagem.Warrior

class CriarPersonagemActivity : AppCompatActivity() {

    private lateinit var distribuicaoPontos: DistribuicaoPontos
    private lateinit var personagem: GameCharacter
    private var raca: IRaca? = null
    private var classe: IClass? = null

    private lateinit var buttonIncrementarForca: Button
    private lateinit var buttonDecrementarForca: Button
    private lateinit var buttonIncrementarDestreza: Button
    private lateinit var buttonDecrementarDestreza: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_personagem)

        // Inicializa os componentes
        val spinnerRacas = findViewById<Spinner>(R.id.spinnerRacas)
        val spinnerClasses = findViewById<Spinner>(R.id.spinnerClasses)
        val buttonListarPersonagem: Button = findViewById(R.id.buttonListarPersonagem)
        buttonIncrementarForca = findViewById(R.id.btnIncrementarForca)
        buttonDecrementarForca = findViewById(R.id.btnDecrementarForca)
        buttonIncrementarDestreza = findViewById(R.id.btnIncrementarDestreza)
        buttonDecrementarDestreza = findViewById(R.id.btnDecrementarDestreza)

        // Configura os Spinners
        val racas = arrayOf("Humano", "Elfo", "Anão")
        val classes = arrayOf("Guerreiro", "Mago", "Ladrão")

        spinnerRacas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, racas)
        spinnerClasses.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)

        // Inicializa os objetos de distribuição de pontos e personagem
        distribuicaoPontos = DistribuicaoPontos()
        personagem = GameCharacter()

        // Configurar os listeners dos Spinners
        spinnerRacas.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val racaEscolhida = racas[position]
                raca = obterRacaEscolhida(racaEscolhida)
                if (classe != null) { // Certifica-se de que a classe foi selecionada
                    distribuicaoPontos.distribuirAtributosIniciais(personagem, raca!!, classe!!)
                }
                // Atualize a UI se necessário
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Implementação vazia, mas necessária
            }
        })

        spinnerClasses.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val classeEscolhida = classes[position]
                classe = obterClasseEscolhida(classeEscolhida)
                if (raca != null) { // Certifica-se de que a raça foi selecionada
                    distribuicaoPontos.distribuirAtributosIniciais(personagem, raca!!, classe!!)
                }
                // Atualize a UI se necessário
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Implementação vazia, mas necessária
            }
        })

        // Configura os listeners dos botões
        setupListeners()
    }

    private fun setupListeners() {
        buttonIncrementarForca.setOnClickListener {
            distribuicaoPontos.ajustarAtributo("força", personagem, aumento = true)
            // Atualize a UI para refletir a nova força
        }

        buttonDecrementarForca.setOnClickListener {
            distribuicaoPontos.ajustarAtributo("força", personagem, aumento = false)
            // Atualize a UI para refletir a nova força
        }

        buttonIncrementarDestreza.setOnClickListener {
            distribuicaoPontos.ajustarAtributo("destreza", personagem, aumento = true)
            // Atualize a UI para refletir a nova destreza
        }

        buttonDecrementarDestreza.setOnClickListener {
            distribuicaoPontos.ajustarAtributo("destreza", personagem, aumento = false)
            // Atualize a UI para refletir a nova destreza
        }
    }

    private fun obterRacaEscolhida(racaEscolhida: String): IRaca {
        return when (racaEscolhida) {
            "Humano" -> Human()
            "Elfo" -> Elf()
            "Anão" -> Dwarf()
            // Adicione mais raças conforme necessário
            else -> Human() // Pode retornar uma raça padrão ou lançar uma exceção
        }
    }

    private fun obterClasseEscolhida(classeEscolhida: String): IClass {
        return when (classeEscolhida) {
            "Guerreiro" -> Warrior() // Supondo que existe uma classe 'Guerreiro' que implementa IClass
            "Mago" -> Mage() // Supondo que existe uma classe 'Mago' que implementa IClass
            "Ladrão" -> Archer() // Supondo que existe uma classe 'Ladrão' que implementa IClass
            // Adicione mais classes conforme necessário
            else -> Warrior() // Pode retornar uma classe padrão ou lançar uma exceção
        }
    }
}



