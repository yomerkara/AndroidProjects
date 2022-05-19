
enum class lootType{
    POTION,RING,ARMOR
}
class Loot(val name :String,val type:lootType,val value: Double) {
    override fun toString(): String {
        return """
            item name:$name
            item type:$type
            item value:$value
            """
    }
}