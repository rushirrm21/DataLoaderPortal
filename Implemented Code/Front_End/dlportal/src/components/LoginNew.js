import { useState } from 'react';
import { Link } from 'react-router-dom';
import "./LoginNew.css";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { useContext } from 'react';
import { LoginContext } from './LoginContext';

const endpoint = 'http://localhost:8080/login'

const Login = () => {
    const { loggedIn, setLoggedIn } = useContext(LoginContext)

    const navigate = useNavigate()
   
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [patternValid, setPatternValid] = useState('')
    const [patternValidE, setPatternValidE] = useState('')

    const onSubmit = async () => {

        const payload = {
            username,
            password
        }
        if (username === '' || password === '' || !isvalidEmail(username) || !isvalid(password) ) {
            // alert("Please fill in the fields")
            toast.warn('Please fill in the fields properly', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
        else {
           
            const response = await axios.post(endpoint, payload)
            if (response.data === "Logged in Successfully") {
                localStorage.setItem('patient-induction', true)
                toast.success('Login Successfull', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
                setLoggedIn(true)
                navigate('/menu')
            }
            else if (response.data === "Incorrect Password") {
                // alert("Incorrect Password");
                toast.error('Incorrect Password', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
            else {
                // alert("Incorrect Username or Password");
                toast.error('Incorrect Username or Password', {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
        }
    }

    const isvalid = (password) => {
        const pattern = /(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,20}).*$/
        return pattern.test(password);
    }

    const isPasswordValid = () => {
        const isPassValid = password && isvalid(password)
        setPatternValid(isPassValid ? '' : 'Invalid Passwordd')
    }


    const isvalidEmail = (username) => {
        const pattern = /[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
        return pattern.test(username);
    }

    const isEmailValid = () => {
        const isEValid = username && isvalidEmail(username)
        setPatternValidE(isEValid ? '' : 'Invalid Username/Email')
    }

    return (

        <div className='formInputs' >
            {/* <ToastContainer /> */}
            <form onSubmit={e => e.preventDefault()} className="form" >
                <h1 className='h12'>LOGIN</h1>

                <label>USERNAME</label>
                <input
                    onBlur={isEmailValid}
                    onFocus={() => setPatternValidE('')}
                    type="email" name="username"
                    placeholder="abcde@gmail.com"
                    required="true"
                    value={username} onChange={(event) => setUsername(event.target.value)} />
                <span>{patternValidE}</span>

                <label>PAASWORD</label>
                <input
                    onBlur={isPasswordValid}
                    onFocus={() => setPatternValid('')}
                    type="password" name='password' placeholder='abcDe@12DUMP' required="true"
                    value={password} onChange={(event) => setPassword(event.target.value)}
                />

                <span>{patternValid}</span>

                <button className='successButton' onClick={onSubmit}>Login</button>
                <Link className='forgotPwd' to="/forgotpass">Forgot Password?</Link>
            </form>
        </div>
    )
}

export default Login