package com.darleyleal.consultordecpf.presenter.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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

    var loading by remember { mutableStateOf(false) }

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
                        .padding(top = 16.dp),
                    onClick = {
                        viewModel.getZipCode(text)
                        loading = true
                    }) {
                    Text("Buscar", fontSize = 18.sp)
                }

                when {
                    loading && zipCodeResponse == null -> {
                        CircularProgressIndicator(
                            modifier = modifier
                                .width(54.dp)
                                .padding(top = 8.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }

                    else -> {
                        loading = false
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
                                            Text(text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("Bairro: ")
                                                }

                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp
                                                    )
                                                ) {
                                                    append(it.bairro)
                                                }
                                            })

                                            Text(text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("Cep: ")
                                                }

                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp
                                                    )
                                                ) {
                                                    append(it.cep)
                                                }
                                            })

                                            Text(text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("Estado: ")
                                                }

                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp
                                                    )
                                                ) {
                                                    append(it.estado)
                                                }
                                            })

                                            Text(text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("DDD: ")
                                                }

                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp
                                                    )
                                                ) {
                                                    append(it.complemento)
                                                }
                                            })

                                            Text(text = buildAnnotatedString {
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                ) {
                                                    append("Região: ")
                                                }

                                                withStyle(
                                                    style = SpanStyle(
                                                        color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                                                        fontSize = 18.sp
                                                    )
                                                ) {
                                                    append(it.regiao)
                                                }
                                            })
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}