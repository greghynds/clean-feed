package com.allsouls.newsapp.arch.data


import okhttp3.MediaType
import okhttp3.ResponseBody
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.Result.Companion.success


@RunWith(MockitoJUnitRunner::class)
class ApiAdapterTest {

    @Test
    fun `should return result when response is successful`() {
        val data = "xxx"
        val response = Response.success(data)

        val result = response.toResult()

        assertThat(result).isEqualTo(success(data))
    }

    @Test
    fun `should return error when response is not successful`() {
        val code = 500
        val message = "Error"
        val body = ResponseBody.create(MediaType.parse("application/json; charset=UTF-8"), message)
        val response = Response.error<String>(code, body)

        val result = response.toResult()

        assertThat(result.exceptionOrNull())
            .isInstanceOf(ApiError::class.java)
            .hasMessage(message)
    }
}