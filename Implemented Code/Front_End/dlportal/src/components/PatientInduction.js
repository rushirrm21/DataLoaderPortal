import React from "react";
import { useState } from "react";
import { toast } from "react-toastify";
import "./LoginNew.css";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Navbar, NavLink, NavItem, NavbarBrand, Nav } from 'reactstrap'
import sideNavbar from "./sideNavbar";

const endpoint = 'http://localhost:8081/load/patientdata';
const PatientInduction = () => {
    const navigate = useNavigate()
    const [excelFile, setExcelFile] = useState(null);
    // it will store excel sheet data after submit
    const [fileTypeError, setFileTypeError] = useState(null);

    const fileType = ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet']
    // const fileType = ['text/csv']
    const handleFile = (event) => {
        let selectedFile = event.target.files[0];


        if (selectedFile && fileType.includes(selectedFile.type)) {
            setExcelFile(event.target.files[0]);
        }

        else {
            setFileTypeError('Please select only EXCEL file types !');
            setExcelFile(null);
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (excelFile !== null) {
            let formData = new FormData();
            formData.append('file', excelFile);

            const response = await axios.post(endpoint, formData)
            const m1 = response.data.message
            console.log(m1)
            if (m1 === "FAILED") {
                toast.error("Failed to save the Patient and Prescription details into the Database", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
            else if (m1 === "INDUCTED") {
                toast.success('Patient and Prescription details saved successfully into the Database', {
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


        }
        else {
            toast.warn('Please select file', {
                position: "bottom-right",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            setExcelFile(null);

        }
    }

    return (



        <div className="formInputs">
            <form onSubmit={handleSubmit} style={{ width: '30%', height: '350px', marginTop: '120px' }}>
                <div>
                    <label><h1 className='h11'>Upload File Here</h1></label>
                    <div>
                        <input type='file' onFocus={() => setFileTypeError('')} onChange={handleFile} required style={{ width: '80%', marginTop: '70px', marginLeft: '40px' }} />
                        <span style={{ marginLeft: '45px', fontSize: '01em' }}>{fileTypeError}</span>
                    </div>

                    <button className="successButton" type="Submit" style={{ width: '40%', marginLeft: '145px' }}>Upload</button>
                </div>
            </form>
        </div>

    );
};

export default PatientInduction;