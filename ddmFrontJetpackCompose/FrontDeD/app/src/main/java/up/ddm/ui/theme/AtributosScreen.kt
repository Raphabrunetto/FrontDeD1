package up.ddm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import up.ddm.Atributos
import up.ddm.Personagem

@Composable
fun AtributosScreen(
    atributos: Atributos,
    personagem: Personagem,
    saveClick: () -> Unit,
    deleteClick: () -> Unit,
    salvarClick: () -> Unit
) {
    var pontosRestantes by remember { mutableStateOf(atributos.getPontosDisponiveis()) }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Criação de Personagem", fontSize = 18.sp)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Pontos restantes:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$pontosRestantes", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // colunas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Atributo", modifier = Modifier.weight(1f))
            Text(
                text = "Valor",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Raça",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Mod",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Atributos com colunas adicionais
        AtributoInputRow(
            "Força",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Destreza",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Constituição",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Sabedoria",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Inteligência",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AtributoInputRow(
            "Carisma",
            atributos,
            { pontosRestantes = atributos.getPontosDisponiveis() },
            { showMessage(it, { snackbarMessage = it; snackbarVisible = true }) }
        )

        if (snackbarVisible) {
            Snackbar(
                action = {
                    Button(onClick = { snackbarVisible = false }) {
                        Text("Fechar")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(snackbarMessage)
            }
        }

        Button(onClick = saveClick) {
            Text("Salvar atributos")
        }

        Button(onClick = salvarClick, Modifier.align(Alignment.CenterHorizontally)) {
            Text("Salvar Personagem")
        }

        Button(onClick = deleteClick, Modifier.align(Alignment.CenterHorizontally)) {
            Text("Deletar Personagem")
        }
    }
}

@Composable
fun AtributoInputRow(
    label: String,
    atributos: Atributos,
    updatePontos: () -> Unit,
    onError: (String) -> Unit
) {
    var textValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Alinhamento vertical centralizado
    ) {
        // Coluna de Atributos
        Text(text = label, modifier = Modifier.weight(1f))

        // Campo de entrada de número inteiro
        OutlinedTextField(
            value = textValue,
            onValueChange = { newValue ->
                // Permitir apenas números
                if (newValue.all { it.isDigit() }) {
                    textValue = newValue
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f)
                .width(50.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && textValue.isNotEmpty()) {
                        try {
                            val valor = textValue.toInt()
                            atributos.setAtributo(
                                valor,
                                valor = 0
                            ) // Envia o valor para setAtributo
                            updatePontos() // Atualiza os pontos
                        } catch (e: IllegalArgumentException) {
                            onError(e.message ?: "Erro desconhecido")
                            textValue = "" // Limpa o campo em caso de erro
                        } catch (e: NumberFormatException) {
                            onError("Por favor, insira um número válido.")
                            textValue = "" // Limpa o campo em caso de erro
                        }
                    }
                }
        )

        // Coluna Bônus de Raça
        Text(
            text = "0",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // Coluna Mod
        Text(
            text = "0",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

fun showMessage(message: String, callback: (String) -> Unit) {
    callback(message)
}
