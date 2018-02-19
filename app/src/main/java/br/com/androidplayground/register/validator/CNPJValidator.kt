package br.com.androidplayground.register.validator

/**
 * @rodrigohsb
 * See {@link <a href="https://gist.github.com/jansenfelipe/5283063">Source</a>}
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

        return try {

            val dig13 = calculateDigit(cnpj,11)
            val dig14 = calculateDigit(cnpj,12)

            dig13 == cnpj[12] && dig14 == cnpj[13]

        } catch (error: Exception) {
            false
        }
    }

    private fun calculateDigit(cnpj: String, indicator: Int): Char {
        val sm = calculateWeight(cnpj,indicator)
        val r = sm % 11
        return getDigit(r)
    }

    private fun calculateWeight(cnpj: String, indicator: Int): Int {

        var sm = 0
        var i = indicator
        var num: Int
        var peso = 2

        while (i >= 0) {
            num = cnpj[i].toInt() - 48
            sm += num * peso
            peso += 1
            if (peso == 10)
                peso = 2
            i--
        }
        return sm
    }

    private fun getDigit(r: Int): Char {
        return if (r == 0 || r == 1)
            '0'
        else
            (11 - r + 48).toChar()
    }
}