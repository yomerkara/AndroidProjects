class Weapon(val name :String, var damageInflicted :Int) {

    override fun toString(): String {
        return """
            weapon name:$name
            weapon damage:$damageInflicted
            """
    }
}