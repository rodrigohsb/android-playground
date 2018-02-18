package br.com.androidplayground.register.validator

/**
 * @rodrigohsb
 */
class CNPJValidator: IValidator
{
    override fun isValid(any: Any): Boolean {

        val cnpj = any as String

        if (cnpj == "00000000000000" || cnpj == "11111111111111" ||
                cnpj == "22222222222222" || cnpj == "33333333333333" ||
                cnpj == "44444444444444" || cnpj == "55555555555555" ||
                cnpj == "66666666666666" || cnpj == "77777777777777" ||
                cnpj == "88888888888888" || cnpj == "99999999999999" ||
                cnpj.length != 14)
            return false

        val dig13: Char
        val dig14: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

        try {
            sm = 0
            peso = 2
            i = 11
            while (i >= 0) {
                num = cnpj[i].toInt() - 48
                sm = sm + num * peso
                peso = peso + 1
                if (peso == 10)
                    peso = 2
                i--
            }

            r = sm % 11
            if (r == 0 || r == 1)
                dig13 = '0'
            else
                dig13 = (11 - r + 48).toChar()

            sm = 0
            peso = 2
            i = 12
            while (i >= 0) {
                num = cnpj[i].toInt() - 48
                sm = sm + num * peso
                peso = peso + 1
                if (peso == 10)
                    peso = 2
                i--
            }

            r = sm % 11
            if (r == 0 || r == 1)
                dig14 = '0'
            else
                dig14 = (11 - r + 48).toChar()

            return dig13 == cnpj[12] && dig14 == cnpj[13]
        } catch (error: Exception) {
            return false
        }

    }
}