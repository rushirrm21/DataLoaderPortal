import { Link } from 'react-router-dom';
import "./LoginNew.css";
import 'react-toastify/dist/ReactToastify.css';

const Menu = () => {
    return (
        
        <div className='formInputs' >
            {/* <ToastContainer /> */}
            <form className="formMenu" >
                
                <h1 className='h12'>Select Action</h1>
                <div style={{marginTop:"10px"}}>
                <div>
                <Link className='menu' to="/menu/patient-induction">Patient Induction</Link>
                </div>
                <div>
                <Link className='menu' to="/menu/patient-update">Patient Update</Link>
                </div>
                <div>
                <Link className='menu' to="/menu/process-patient-data">Process Patient Data</Link>
                </div>
                </div>
            </form>
        </div>
    )
    }
export default Menu