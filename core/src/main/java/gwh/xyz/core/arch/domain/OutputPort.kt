package gwh.xyz.core.arch.domain

interface OutputPort<Out> {
    suspend fun receive(result: Result<Out>)
}