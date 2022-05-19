open class Enemy(val name: String, var hitPoints: Int, var lives: Int) {

    open fun takeDamage(damage: Int) {
        val remaininghitPoints = hitPoints - damage
        if (remaininghitPoints > 0) {
            hitPoints = remaininghitPoints
            println("$name took $damage points of damage, and has $hitPoints left")
        } else {
            lives -= 1
            if(lives>0){
            println("$name lost a life!!!")
            }
            else{
                println("$name is dead.")
                hitPoints=0
            }
        }
    }

    override fun toString(): String {
        return "Name: $name , Hitpoints : $hitPoints , Lives: $lives"
    }
}