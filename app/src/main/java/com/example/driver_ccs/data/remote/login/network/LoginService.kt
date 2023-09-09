package com.example.driver_ccs.data.remote.login.network

import com.example.driver_ccs.utils.ProcessStatus
import com.example.driver_ccs.utils.Resource
import java.net.SocketTimeoutException

class LoginService(private val service: ILoginService) {

    suspend fun login(
        email: String,
        password: String
    ): Resource<String> {
        return try {
            val response = service.login()

            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                var message = response.code().toString()
                if(response.body() != null){
                    message += " - ${response.body()}"
                }
                if(response.message().isNotBlank()){
                    message += " - ${response.message()}"
                }
                if(response.errorBody() != null){
                    message += " - ${response.errorBody()}"
                }
                if (response.errorBody() != null) {
                    message += " - ${response.errorBody()}"
                    if (response.code() in 400..499) {
                        Resource.Fail(ProcessStatus.WrongParameter, message)
                    } else {
                        Resource.Fail(ProcessStatus.Fail, message)
                    }
                } else {
                    Resource.Fail(ProcessStatus.Fail, message)
                }
            }
        } catch (e: Exception) {
            Resource.Fail(ProcessStatus.NoInternet, e.message ?: e.toString())
        } catch (e: SocketTimeoutException) {
            Resource.Fail(ProcessStatus.TimeOut, e.message ?: e.toString())
        } catch (e: Exception) {
            Resource.Fail(ProcessStatus.Exception, e.message ?: e.toString())
        }
    }
}
