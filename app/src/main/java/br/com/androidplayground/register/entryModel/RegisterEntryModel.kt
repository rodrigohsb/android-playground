package br.com.androidplayground.register.entryModel

import java.util.*

/**
 * @rodrigohsb
 */
data class RegisterEntryModel(val name: String, val cnpj: String,
                              val date: Date, val email: String,
                              val fantasyName: String, val phone: String,
                              val isMEI: Boolean)