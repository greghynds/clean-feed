package gwh.xyz.core.arch.adapter


import gwh.xyz.core.arch.presentation.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class Receiver(private val dispatchers: Dispatchers) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job
}