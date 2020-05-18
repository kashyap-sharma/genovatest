package kashyap.genovatest.cusfo

import java.util.regex.Matcher
import java.util.regex.Pattern

 class ValidationUtils {

    // non-static Matcher object because it's created from the input String
     companion object {
         fun isEmptyEditText(s: String?): Boolean {
             return s != null && s != ""
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
         fun isValidAddress(address: String?): Boolean {
             return address != null && address != ""
         }
     }



}