package gwh.xyz.core.arch.data

class ApiError(val code: Int, message: String?) : Throwable(message)