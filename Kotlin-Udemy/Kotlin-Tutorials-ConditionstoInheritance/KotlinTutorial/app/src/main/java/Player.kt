import java.util.ArrayList

class Player(val name: String, var lives: Int = 3, var score: Int = 0) {
    var weapon: Weapon = Weapon("fist", 1)
    var level = 1
    private val invertory = ArrayList<Loot>()

    fun show() {
        println(
            """
            name:$name
            lives:$lives
            level:$level
            score:$score
            $name's weapon:
            $weapon
            $name's inventory:
            $invertory
        """
        )
    }

    fun showInv() {
        var total = 0.0
        println("$name's inventory:")
        for (item in invertory) {
            println(item)
            total += item.value
        }
        println("=================")
        println("Total score: $total")
    }

    fun getLoot(item: Loot) {
        invertory.add(item)
        //codes here for save items
    }

    fun dropLoot(item: Loot): Boolean {
        return if (invertory.contains(item)) {
            invertory.remove(item)
            true
        } else {
            false
        }

    }

    fun dropLoot(name: String): Boolean {
        println("$name will be dropped!!!")
        var count = 0
        for (item in invertory) {
            if (item.name == name) {
                count++
                invertory.remove(item)
                return true
                break
            }
        }
        return false


    }
}