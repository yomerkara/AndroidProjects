fun main() {
    println("Hello World")
    println("This is a try text")

    val myname = "Omer Kara"//it cant be reaasign
    var name ="abc"

    println(myname)
    println(name)
    val number = 25
    println(number)

    val omerWeeklySalary = 32
    val omerMounthlySalary = omerWeeklySalary * 4
    println("Tim salary monthly is: $omerMounthlySalary" )

    val apples = 5.5
    val oranges = 6
    val fruits = oranges + apples
    println("\n" + fruits)
    println("A quarter of apples is : ${apples/4}")

    val weeks =234
    val years =weeks/52.0

    println("My name is $myname and ı cant change it but $name is a random name and when ı want ı can change")
    name="xyz"
    println("Look here name is different now $name")
    println("$weeks in years is: $years")

    val var1 =64
    val var2 ="Kotlin"
    val var3 =32.6

    println("First var is : $var1, its an integer. \nSecond var is $var2, its a String .\nLast one is a double $var3" +
            "\nWe can concanate behind var1 and var3 : Result => ${var3/var1}\n" +
            "$var2 , i try learn $var2")
}
