package br.com.androidplayground.register.validator

import java.util.regex.Pattern

/**
 * @rodrigohsb
 */
class FantasyNameValidator: IValidator{

    override fun isValid(any: Any): Boolean =

            Pattern.compile("[a-zA-Z0-9.? ]*")
                .matcher(any as String)
                .matches()
}