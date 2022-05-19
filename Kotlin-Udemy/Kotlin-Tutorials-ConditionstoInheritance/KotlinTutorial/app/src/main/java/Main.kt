fun main() {
    /*val lives = 1
    var isGameOver = (lives <= 1)

    println(isGameOver)
    if (isGameOver) {
        println("Game Over")
    } else {
        println("Have $lives hp keep going")
    }

    println("How old are you ?")
    val age = readLine()!!.toInt()
    println(age)

    val message: String

    message = when {
        age < 18 -> {
            "Your age too young to vote"
        }
        age == 100 -> {
            "You're okey to voteing"
        }
        else -> {
            "Its not an number or age"
        }
    }

    println(message)

    val player1 = Player(name = "Arthas")

    /* println("Name is :${player1.name} \t Level is:${player1.level}\n"
             +"Lives is :${player1.lives} \t Score is :${player1.score}")*/
    player1.show()

    val player2 =Player(name = "Demonloci")
    player2.level=5
    player2.show()

    var firstInstance = Player("first", 8)
    firstInstance.level = 4
    firstInstance.show()

    var secondInstance = Player("second", 5, 1000)
    secondInstance.level = 2
    secondInstance.show()

    println(secondInstance.weapon.name.toUpperCase())
    println(secondInstance.weapon.damageInflicted)

    val Axe = Weapon("Axe", 12)

    firstInstance.weapon = Axe

    println(firstInstance.weapon.name)
    println(firstInstance.weapon.damageInflicted)
    Axe.damageInflicted = 32

    println(firstInstance.weapon.damageInflicted)

    val Sword=Weapon("Sword",7)

    secondInstance.weapon=Sword
    println(secondInstance.weapon.damageInflicted)

    Sword.damageInflicted=6

    println(secondInstance.weapon.damageInflicted)
    */
    var tim = Player("tim", 3, 10)
    var louis = Player("louis", 3, 20)
    var me = Player("Arthas", 3, 100)


    val Sword = Weapon("Sword", 12)
    val Spear = Weapon("Spear", 20)
    val Knife = Weapon("Knife", 33)

    val redPotion = Loot("Red Potion", lootType.POTION, 7.5)
    val chestArmor = Loot("Chest Armor", lootType.ARMOR, 80.00)
    /* tim.weapon = Sword
     louis.weapon = Sword
     tim.level = 6
     louis.level = 6

     tim.show()
     louis.show()

     tim.level = 7
     tim.weapon = Spear

     tim.show()
     */
    me.level = 8
    me.weapon = Knife
    me.getLoot(redPotion)
    me.getLoot(chestArmor)
    me.getLoot(Loot("Ring of Protection +2", lootType.RING, 40.5))
    me.getLoot(Loot("Invisibilty Potion", lootType.POTION, 30.55))
    println("Player İnfo:")
    println(me.show())

    if (me.dropLoot(redPotion)) {
        me.show()
    } else {
        println("You dont have ${redPotion.name}")
    }

    val bluePotion = Loot("Blue Potion", lootType.POTION, 18.98)

    if (me.dropLoot(bluePotion)) {
        me.show()
    } else {
        println("You dont have ${bluePotion.name}")
        me.getLoot(bluePotion)
    }
    me.show()
    me.dropLoot("Invisibilty Potion")
    me.show()

    var conan = Player("Conan", 3, 10)
    conan.getLoot(Loot("a", lootType.ARMOR, 4.0))
    conan.getLoot(Loot("b", lootType.POTION, 5.0))
    conan.getLoot(Loot("c", lootType.POTION, 6.0))
    conan.getLoot(Loot("d", lootType.ARMOR, 80.5))
    conan.getLoot(Loot("e", lootType.RING, 4.8))
    conan.getLoot(Loot("f", lootType.ARMOR, 80.9))
    conan.getLoot(Loot("f", lootType.ARMOR, 80.9))
    conan.getLoot(Loot("f", lootType.ARMOR, 80.9))
    conan.getLoot(Loot("f", lootType.ARMOR, 80.9))
    conan.getLoot(Loot("f", lootType.ARMOR, 80.9))



    conan.showInv()
    conan.dropLoot("f")
    conan.showInv()

}
