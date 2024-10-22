import axios from "axios";

const Login_Credentials_Base_URL = "http://localhost:8080/dlportal/v1/loginn";

class LoginService{

    getLoginCred(){
        return axios.get(Login_Credentials_Base_URL);
    }

    createLoginCred(logCred)
    {   
        
        console.log('logC =>' + JSON.stringify(logCred));
        return axios.post(Login_Credentials_Base_URL ,(logCred));
        alert("Data sent to back end");
    }
}

export default new LoginService();
/* {
            headers:{'Access-Control-Allow-Origin' : "*",
             'Access-Control-Allow-Methods':"GET,PUT,POST,DELETE,PATCH,OPTIONS",
             "Access-Control-Allow-Credentials":"true"
            }
        } */