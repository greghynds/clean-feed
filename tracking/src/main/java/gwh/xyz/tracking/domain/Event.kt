package gwh.xyz.tracking.domain

const val TYPE_DISPLAY = "display"
const val TYPE_LOAD = "load"

sealed class Event(val key: String) {
    class Display(val screen: String) : Event(TYPE_DISPLAY)
    class NetworkRequest(val time: Int) : Event(TYPE_LOAD)

    override fun equals(other: Any?): Boolean {
        if (other !is Event) return false
        if (key == other.key) return true
        return false
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}