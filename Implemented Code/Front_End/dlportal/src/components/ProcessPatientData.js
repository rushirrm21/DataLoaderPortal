import { useState } from 'react';
import "./LoginNew.css";
import axios from 'axios';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import "./PatientUpdate.css";

const endpoint1 = 'http://localhost:8081/retrieve'
const endpoint2 = 'http://localhost:8081/processpatient'

const ProcessPatientData = () => {
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

    //for validation error message
    const [patientIdValid, setPatientIdValid] = useState('')

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
        const response = await axios.put(endpoint2, payload);
        console.log(response.data.message)
        if(response.data.message == "Updated"){
            toast.success("Successfully processed data", {
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
            toast.error("An error occured while processing data", {
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
        const response = await axios.get(`${endpoint1}/${patientId}`);
        const { detailsAvailable, name, address, DOB, email, contactNo, drugId, drugName } = response.data
        if (detailsAvailable === "Yes") {
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

    //validation function
    const isPatientIdValid = (e) => {
        const pattern = /[0-9]{1,4}/
        const res = pattern.test(patientId);

        if (res === false) {
            setPatientIdValid("Invalid Patient Id")
            setOkField("")
        }
    }

    const onIdInputFocus = () => {
        setOkField("true")
        setPatientIdValid('')
    }
    return (
        <div className='formInputs'>
            <form style={{ width: '22.5%', height: '1000px', marginTop: '10px' }}
                onSubmit={e => e.preventDefault()} className="formPassReset" >
                <h1 className='h11'>PROCESS THE DATA</h1>

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
                            disabled="true"
                        />
                    </span>
                </div>

                <label>Date of Birth</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }}
                            type="text" name='patientDateofBirth'
                            value={patientDateofBirth}
                            disabled="true"
                        />
                    </span>
                </div>

                <label>Email Id</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="email"
                            name='patientEmail' value={patientEmail}
                            disabled="true"
                        />
                    </span>
                </div>


                <label>Phone Number</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text"
                            name='patientContactNumber'
                            value={patientContactNumber}
                            disabled="true"
                        />
                    </span>
                </div>

                <label>Drug Id</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text"
                            name='patientDrugId'
                            value={patientDrugId}
                            disabled="true"
                        />
                    </span>
                </div>

                <label>Drug Name</label>
                <div>
                    <span>
                        <input style={{ width: '320px' }} type="text"
                            name='patientDrugName'
                            value={patientDrugName}
                            disabled="true" />
                    </span>
                </div>

                <button style={{ width: '100px', marginLeft: "130px" }}
                    disabled={editButtons.length === 0}
                    className='successButton' onClick={onSubmit}>SUBMIT</button>

            </form>
        </div>
    )
}

export default ProcessPatientData