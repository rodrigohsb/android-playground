package br.com.androidplayground.register.validator

import java.util.regex.Pattern

/**
 * @rodrigohsb
 */
class FantasyNameValidator: IValidator{

    override fun isValid(any: Any): Boolean {

    val fantasyName = any as String

     return fantasyName.isNotEmpty()
             && Pattern.compile("[a-zA-Z0-9.? ]*")
             .matcher(any)
             .matches()
    }
}