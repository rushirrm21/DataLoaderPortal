import { useState } from 'react';
import "./LoginNew.css";
import axios from 'axios';
import { toast } from 'react-toastify';

const endpoint = 'http://localhost:8080/forgotpwd'

const ForgotPass = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [cpassword, setCPassword] = useState('')
    const [patternValid, setPatternValid] = useState('')
    const [patternValidE, setPatternValidE] = useState('')

    const onSubmit = async () => {

        if (!isvalidEmail(username) && !isvalid(password)) {
            // alert("Invalid Username/Email and Password, Please match the pattern")
            toast.warn('Invalid Username/Email and Password, Please match the pattern', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
        else if (!isvalidEmail(username)) {
            // alert("Invalid Username/Email, Please match the pattern")
            toast.warn('Invalid Username/Email, Please match the pattern', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        }
        else if (username === '' || password === '') {
            // alert("Please fill in the fields")
            toast.warn('Please fill in the fields', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });

        }
        else if (password === cpassword && password !== '' && isvalid(password) && isvalidEmail(username)) {
            const payload = {
                username,
                password
            }
            const response = await axios.put(endpoint, payload)
            if (response.data === "Password Changed") {
                // alert("Password Changed Successfully");
                toast.success('Password Changed Successfully!', {
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
                // alert("Incorrect Username, not found in Database");
                toast.error('Incorrect Username, not found in Database', {
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
        else if (!isvalid(password) && !isvalid(cpassword)) {
            // alert("Invalid Passwords, Please match the pattern")
            toast.warn('Invalid Passwords, Please match the pattern', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });

        }
        else if (password !== cpassword) {
            toast.warn("Passwords don't match", {
                position: "top-centert",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
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

    const isDifferent = password !== cpassword
    const error = isDifferent ? 'Pasword don\'t match' : ''

    return (
        <div className='formInputs'>
            <form onSubmit={e => e.preventDefault()} className="formPassReset" >
                <h1 className='h11'>RESET PASSWORD</h1>

                <label>USERNAME</label>
                <input type="email" name="username"
                    placeholder="abcde@gmail.com"
                    required='true'
                    onBlur={isEmailValid}
                    onFocus={() => setPatternValidE('')}
                    value={username} onChange={(event) => setUsername(event.target.value)} />
                <span>{patternValidE}</span>

                <label>NEW PAASWORD</label>
                <input type="password" name='password' placeholder='abcDe@12DUMP' required="true"
                    onBlur={isPasswordValid}
                    onFocus={() => setPatternValid('')}
                    value={password} onChange={(event) => setPassword(event.target.value)} />
                <span>{patternValid}</span>
                <label>CONFIRM PAASWORD</label>
                <input type="password" name='cpassword' placeholder='abcDe@12DUMP' required="true"
                    value={cpassword} onChange={(event) => setCPassword(event.target.value)} />

                <span>{error}</span>

                <button className='successButton'onClick={onSubmit}>Change Password</button>
            </form>
        </div>
    )
}

export default ForgotPass