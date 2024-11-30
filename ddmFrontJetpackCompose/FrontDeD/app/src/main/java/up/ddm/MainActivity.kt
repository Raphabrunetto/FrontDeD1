package up.ddm


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import personagem.GameCharacter


class MainActivity(private val personagens: List<GameCharacter>) :
    RecyclerView.Adapter<MainActivity.PersonagemViewHolder>() {

    class PersonagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.textViewNome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_personagem, parent, false)
        return PersonagemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val personagem = personagens[position]
        holder.nomeTextView.text = personagem.nome
    }

    override fun getItemCount(): Int {
        return personagens.size
    }
}
