fun main() {


    val uglyTroll = Troll("Ugly Troll", 22, 1)
    val blackVampyre = Vampyre("Black Vampyre")
    val dracula = VampyreKing("Dracula")
    println(uglyTroll)
    println(blackVampyre)
    println(dracula)

    uglyTroll.takeDamage(28)
    blackVampyre.takeDamage(2)
    println(dracula)
    while (dracula.lives > 0) {
        if(dracula.dodges()){
            continue
        }
        if (dracula.runAway()) {
            println("Dracula runaway!!")
            break
        }
        else{
            dracula.takeDamage(40)}

    }

    println(dracula)
    println(uglyTroll)
    println(blackVampyre)


}
