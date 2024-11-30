package up.ddm

import Interfaces.IClass
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import Interfaces.IRaca

class CharacterCreationUtils(private val activity: AppCompatActivity) {

    fun chooseRace(races: List<IRaca>, raceChoice: String): IRaca? {
        val race = races.find { it.name.equals(raceChoice, ignoreCase = true) }
        return if (race != null) {
            race
        } else {
            showToast("Raça não encontrada.")
            null
        }
    }

    fun chooseClass(classes: List<IClass>, classChoice: String): IClass? {
        val characterClass = classes.find { it.name.equals(classChoice, ignoreCase = true) }
        return if (characterClass != null) {
            characterClass
        } else {
            showToast("Classe não encontrada.")
            null
        }
    }

    fun chooseCharacterName(characterName: String): String? {
        return if (characterName.isNotBlank()) {
            characterName
        } else {
            showToast("Nome inválido.")
            null
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}