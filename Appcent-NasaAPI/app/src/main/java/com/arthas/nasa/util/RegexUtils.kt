package com.arthas.nasa.util

class RegexUtils {
    companion object {
        val ascii = Regex("[^\\p{ASCII}]")
        val email = Regex("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}\$")
        val phoneNumber = Regex("[^0-9 ]")
        val date = Regex("[^0-9/]")
        val charactersNumbers = Regex("[^A-Za-z0-9]")
        val charactersWithTurkishAndSpace = Regex("[^A-Za-zğüşöçıİĞÜŞÖÇ ]")
        val charactersWithTurkishSpaceNumbers = Regex("[^A-Za-zğüşöçıİĞÜŞÖÇ 0-9]")
    }
}