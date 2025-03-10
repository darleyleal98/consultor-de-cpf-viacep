package com.darleyleal.consultordecpf.presenter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darleyleal.consultordecpf.presenter.viewmodel.ZipCodeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: ZipCodeViewModel
) {
    var text by remember { mutableStateOf("") }
    val zipCodeResponse by viewModel.zipCodeResponse.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("ViaCEP")
                }
            )
        },
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(top = padding.calculateTopPadding(), start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = modifier.fillMaxWidth(),
                    value = text, onValueChange = { text = it },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.clear() }) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    label = {
                        Text("Digite um CEP", fontSize = 20.sp)
                    }
                )

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), onClick = { viewModel.getZipCode(text) }) {
                    Text("Buscar", fontSize = 18.sp)
                }

                zipCodeResponse?.body()?.let {
                    if (text.isNotEmpty()) {
                        Text(
                            text = "Resultado da pesquisa:",
                            textAlign = TextAlign.Center, modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )

                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            content = {
                                Column(modifier = modifier.padding(8.dp)) {
                                    Text(text = it.bairro, fontSize = 18.sp)
                                    Text(text = it.cep, fontSize = 18.sp)
                                    Text(text = it.complemento, fontSize = 18.sp)
                                    Text(text = it.ddd, fontSize = 18.sp)
                                    Text(text = it.estado, fontSize = 18.sp)
                                    Text(text = it.gia, fontSize = 18.sp)
                                    Text(text = it.ibge, fontSize = 18.sp)
                                    Text(text = it.localidade, fontSize = 18.sp)
                                    Text(text = it.logradouro, fontSize = 18.sp)
                                    Text(text = it.regiao, fontSize = 18.sp)
                                    Text(text = it.siafi, fontSize = 18.sp)
                                    Text(text = it.uf, fontSize = 18.sp)
                                    Text(text = it.unidade, fontSize = 18.sp)
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}