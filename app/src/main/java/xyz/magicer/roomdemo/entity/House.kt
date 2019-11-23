package xyz.magicer.roomdemo.entity

data class House(
    val address: String,
    val price: String,
    val floor: String
) {
    override fun toString(): String {
        return "House(address='$address', price='$price', floor='$floor')"
    }
}
