package br.com.androidplayground.register.validator

import java.util.regex.Pattern

/**
 * @rodrigohsb
 */
class PhoneValidator: IValidator{

    override fun isValid(any: Any): Boolean =

        Pattern.compile("^\\d{11}\$")
                .matcher(any as String)
                .matches()
}