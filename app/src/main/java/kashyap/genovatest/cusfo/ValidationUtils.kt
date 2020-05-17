package kashyap.genovatest.cusfo

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidationUtils {

    // non-static Matcher object because it's created from the input String
    private var matcher: Matcher? = null

    init {
        // initialize the Pattern object
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
    }


    fun validateEmail(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher!!.matches()
    }

    fun isValidConfirmPasswrod(confirmPassword: String, password: String): Boolean {
        return confirmPassword != password
    }

    fun isValidAddress(address: String?): Boolean {
        return address != null && address != ""
    }

    fun isValidPincode(pincode: String?): Boolean {
        if (pincode == null) {
            return false
        } else {
            val PINCODE_PATTERN = "^[0-9]{6}$"

            val pattern = Pattern.compile(PINCODE_PATTERN)
            val matcher = pattern.matcher(pincode)
            return matcher.matches()
        }
    }


    fun isValidMobile(mobile: String?): Boolean {
        val p = Pattern.compile("^[789]\\d{9}$")
        if (mobile == null) {
            return false
        } else {
            val m = p.matcher(mobile)
            return m.matches()
        }
    }

    fun isValidPassword(password: String?): Boolean {
        val p = Pattern.compile("((?!\\s)\\A)(\\s|(?<!\\s)\\S){6,20}\\Z")
        if (password == null) {
            return true
        } else {
            val m = p.matcher(password)
            return !m.matches()
        }
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches("[a-zA-Z0-9._-][a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}".toRegex())
    }

    fun isValidLastName(lastName: String?): Boolean {
        val p = Pattern.compile("^[a-zA-Z]{3,20}$")
        if (lastName == null) {
            return false
        } else {
            val m = p.matcher(lastName)
            return m.matches()
        }
    }

    fun isValidFirstName(firstname: String?): Boolean {
        val p = Pattern.compile("^[a-zA-Z]{3,20}$")
        if (firstname == null) {
            return false
        } else {
            val m = p.matcher(firstname)
            return m.matches()
        }
    }

    fun isValidAge(age: String?): Boolean {
        val p = Pattern.compile("^[1-9]{1,3}$")
        if (age == null || age == "") {
            return false
        } else {
            val m = p.matcher(age)
            return m.matches()
        }
    }

    fun isEmptyEditText(s: String?): Boolean {
        return s != null && s != ""
    }

    companion object {
        private val EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"


        private lateinit var pattern: Pattern
    }
}