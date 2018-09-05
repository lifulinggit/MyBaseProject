package baseproject.com.mybaseproject.view

interface LoginView : IView{
    fun getUserName() : String
    fun getPassWord() : String
    fun loginSuccess(msg : String)
    fun loginFailed(msg : String)
}