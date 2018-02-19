package br.com.androidplayground.register.validator

import java.util.regex.Pattern

/**
 * @rodrigohsb
 */
class NameValidator: IValidator{

    override fun isValid(any: Any): Boolean {

        val name = any as String

        return name.split(" ").size == 2
                && Pattern
                        .compile("[a-zA-Z.? ]*")
                            .matcher(name)
                                .matches()

    }
}