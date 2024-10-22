import { useState } from 'react';
import "./LoginNew.css";
import axios from 'axios';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import "./PatientUpdate.css";

const endpoint1 = 'http://localhost:8081/load/patient'
const endpoint2 = 'http://localhost:8081/updatepatient'

const PatientUpdate = () => {
    const navigate = useNavigate()
    const [patientId, setPatientId] = useState('')
    const [patientName, setPatientName] = useState('')
    const [patientAddress, setPatientAddress] = useState('')
    const [patientDateofBirth, setPatientDateofBirth] = useState('')
    const [patientEmail, setPatientEmail] = useState('')
    const [patientContactNumber, setPatientContactNumber] = useState('')
    const [patientDrugId, setPatientDrugId] = useState('')
    const [patientDrugName, setPatientDrugName] = useState('')

    //used for edit button to enable inputs fields
    const [patientIdField, setPatientIdField] = useState('true')
    const [patientAddressField, setPatientAddressField] = useState('')
    const [patientDateofBirthField, setpatientDateofBirthField] = useState('')
    const [patientEmailField, setPatientEmailField] = useState('')
    const [patientContactNumberField, setPatientContactNumberField] = useState('')

    //for validation error message
    const [patientIdValid, setPatientIdValid] = useState('')
    const [addressError, setAddressError] = useState('')
    const [DOBError, setDOBError] = useState('')
    const [emailError, setEmailError] = useState('')
    const [contactError, setContactError] = useState('')

    //for enabling buttons
    const [okField, setOkField] = useState('true')
    const [editButtons, setEditButtons] = useState("")
    const onSubmit = async () => {
        const payload = {
            patientId,
            patientName,
            patientAddress,
            patientDateofBirth,
            patientEmail,
            patientContactNumber,
            patientDrugId,
            patientDrugName
        }
        const response = await axios.put(endpoint2,payload);
        console.log(response.data.message)
        if(response.data.message == "Updated"){
            toast.success("Successfully updated details", {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });  
            navigate('/menu')
        }
        else{
            toast.error("An error occured while updating details", {
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


    //on clicking the ok this function makes a GET call using axios to fetch the patient details form DB
    const loadPatientDetails = async (e) => {
        const payload = {
            patientId,
        }
        const response = await axios.post(endpoint1, payload);
        const { detailsAvailable, name, address, DOB, email, contactNo, drugId, drugName } = response.data
        if (detailsAvailable === "Yes") {
            console.log(name)
            console.log(address)
            console.log(DOB)
            console.log(email)
            console.log(contactNo)
            console.log(drugId)
            console.log(drugName)
            const str = DOB;
            const dateResult = new Date(str);
            var year = dateResult.getFullYear();
            var month = String(dateResult.getMonth() + 1).padStart(2, 0);
            var day = String(dateResult.getDate()).padStart(2, 0);
            var datePattern = month + '-' + day + '-' + year;
            setPatientName(name)
            setPatientAddress(address)
            setPatientDateofBirth(DOB)
            setPatientEmail(email)
            setPatientContactNumber(contactNo)
            setPatientDrugId(drugId)
            setPatientDrugName(drugName)
            setPatientIdField("")
            setOkField("")
            setEditButtons("true")

        }
        else {
            toast.error("Patient details not found", {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            setPatientIdField("true")
        }
    }

    //onClick functions to enable the input fields
    const handleAddressClick = () => {
        setPatientAddressField("true")
    }
    const handleDateofBirthClick = () => {
        setpatientDateofBirthField("true")
    }
    const handleEmailClick = () => {
        setPatientEmailField("true")
    }
    const handleContactNumberClick = () => {
        setPatientContactNumberField("true")
    }

    //onChange functions to set new value to variables
    const handleAddressChange = (e) => {
        setPatientAddress(e.target.value)
    }

    const handleDateofBirthChange = (e) => {
        setPatientDateofBirth(e.target.value)
    }

    const handleEmailChange = (e) => {
        setPatientEmail(e.target.value)
    }

    const handleContactNumberChange = (e) => {
        setPatientContactNumber(e.target.value)
    }

    //validation functions
    const isPatientIdValid = (e) => {
        const pattern = /[0-9]{1,4}/
        const res = pattern.test(patientId);

        if (res === false) {
            setPatientIdValid("Invalid Patient Id")
            setOkField("")
        }
    }

    const validateAddress = (e) => {
        const pattern = /^$/
        const res = pattern.test(patientAddress)
        if (res === true) {
            setAddressError("Address can't be empty")
        }
    }
    const validateDOB = (e) => {
        let today = new Date();
        // let date = parseInt(today.getMonth() + 1) + '-' + today.getDate() + '-' + today.getFullYear()
        let dob = new Date(patientDateofBirth);
        let dateOB = parseInt(dob.getMonth() + 1) + '-' + dob.getDate() + '-' + dob.getFullYear()

        const pattern = /(0[1-9]|1[0-2])\-(0?[1-9]|1[0-9]|2[0-9]|3[01])\-([0-9]{4})/
        const res = pattern.test(patientDateofBirth)
        if (res !== true) {
            setDOBError("Please enter in MM-DD-YYYY format")
        }
        else {
            if (today >= dob) {

                // alert("Its okay")
            }
            else {
                setDOBError("It's not a valid DOB")
            }
        }
    }
    const validateEmail = (e) => {
        const pattern = /[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
        const res = pattern.test(patientEmail);
        if (res !== true) {
            setEmailError("Please enter valid email id")
        }
    }
    const validateContactNumber = (e) => {
        const pattern = /[6-9]{1}[0-9]{9}/
        const res = pattern.test(patientContactNumber);
        if (!res) {
            setContactError("Please 10 digit valid")
        }
    }
    const onIdInputFocus = () => {
        setOkField("true")
        setPatientIdValid('')
    }
    return (
        <div className='formInputs'>
            <form style={{ width: '30%', height: '940px', marginTop: '10px' }}
                onSubmit={e => e.preventDefault()} className="formPassReset" >
                <h1 className='h11'>MODIFY THE PATIENT DETAILS</h1>

                <label>Enter Patient Id</label>
                <input type="text"
                    style={{ width: '320px' }}
                    name="patientName"
                    placeholder="ID"
                    required='true'
                    disabled={patientIdField.length === 0}
                    onFocus={onIdInputFocus}
                    value={patientId}
                    onChange={(event) => setPatientId(event.target.value)}
                    onBlur={isPatientIdValid} />
                <span style={{ fontSize: '0.9em' }}>{patientIdValid}</span>

                <button disabled={okField.length === 0}
                    className='successButton' onClick={loadPatientDetails}
                    style={{ width: '80px', marginLeft: "140px" }}>OK</button>



                <label>Name</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text" name='patientAddress'
                            value={patientName}
                            disabled="true"
                        />
                    </span>
                </div>
                <label>Address</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text" name='patientAddress'
                            value={patientAddress}
                            disabled={patientAddressField.length === 0}
                            onFocus={() => setAddressError('')}
                            onChange={handleAddressChange}
                            onBlur={validateAddress} />
                    </span>
                    <span className='spanEdit'>
                        <button className='editButton'
                            disabled={editButtons.length === 0}
                            onClick={handleAddressClick}>Edit</button>
                    </span>
                </div>
                <span style={{ fontSize: '0.9em' }}>{addressError}</span>


                <label>Date of Birth</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }}
                            type="text" name='patientDateofBirth'
                            value={patientDateofBirth}
                            disabled={patientDateofBirthField.length === 0}
                            onFocus={() => setDOBError('')}
                            onBlur={validateDOB}
                            onChange={handleDateofBirthChange} />
                    </span>
                    <span className='spanEdit'>
                        <button className='editButton'
                            disabled={editButtons.length === 0}
                            onClick={handleDateofBirthClick}>Edit</button>
                    </span>
                </div>
                <span style={{ fontSize: '0.9em' }}>{DOBError}</span>

                <label>Email Id</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="email"
                            name='patientEmail' value={patientEmail}
                            disabled={patientEmailField.length === 0}
                            onFocus={() => setEmailError('')}
                            onBlur={validateEmail}
                            onChange={handleEmailChange} />
                    </span>
                    <span className='spanEdit'>
                        <button className='editButton'
                            disabled={editButtons.length === 0}
                            onClick={handleEmailClick}>Edit</button>
                    </span>
                </div>
                <span style={{ fontSize: '0.9em' }}>{emailError}</span>

                <label>Phone Number</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text"
                            name='patientContactNumber'
                            value={patientContactNumber}
                            disabled={patientContactNumberField.length === 0}
                            onFocus={() => setContactError('')}
                            onBlur={validateContactNumber}
                            onChange={handleContactNumberChange} />
                    </span>
                    <span className='spanEdit'>
                        <button className='editButton'
                            disabled={editButtons.length === 0}
                            onClick={handleContactNumberClick}>Edit</button>
                    </span>
                </div>
                <span style={{ fontSize: '0.9em' }}>{contactError}</span>

                <button style={{ width: '100px', marginLeft: "130px" }}
                    disabled={editButtons.length === 0}
                    className='successButton' onClick={onSubmit}>SAVE</button>

            </form>
        </div>
    )
}

export default PatientUpdate