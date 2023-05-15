import '../styles/App.css';
import '../styles/reset.css';
import {useNavigate} from 'react-router-dom';
import React, {useRef, useState} from "react";
import imageHandler from '../hooks/ImageHandler.js'; //???


const Report = () => {
    const movePage = useNavigate();

    function goReport() {
        movePage('/goReport');
    }

    const [imgFile, setImgFile] = useState("");
    const imgRef = useRef();

    const saveImgFile = () => {
        const file = imgRef.current.files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = () => {
            setImgFile(reader.result);
        };
        imageHandler(imgFile);
    };


    return (
        <div>
            <main>
                <hr className="line"/>
                <section>
                    <img id="logo2_img" src="assets/logo2.png" alt="EcoCycle logo"/> <br/>
                    <span id="report_span">
                        <img id="upload_img" src={imgFile ? imgFile : "assets/report.png"} alt="Upload"/>
                    </span>
                    <form>
                        <label className="uploadImage_label" htmlFor="uploadImage_input"> 사진 첨부하기</label>
                        <input type="file" id="uploadImage_input" accept="image/*" style={{display: "none"}}
                               ref={imgRef} onChange={saveImgFile}/>
                    </form>

                    {/* <button className="user_btn" id="upload_btn" onClick={OnClickImageUpload} > 사진 첨부하기 </button> */}
                </section>
            </main>
            <footer id="report_footer">
                <hr className="line"/>
                <img id="copyright2_img" src="assets/copyright2.png" alt="Copyright by EcoByte"/>
            </footer>
        </div>
    );
}

export default Report;
