package gwh.xyz.core.arch.domain

interface InputPort<In> {
    suspend fun send(params: In)
}