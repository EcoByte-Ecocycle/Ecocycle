import '../styles/App.css';
import '../styles/reset.css';
import { useNavigate } from 'react-router-dom';
import React, { useRef, useState } from "react";
import imageHandler from '../hooks/ImageHandler';
import { getProductInfo } from '../api/apis';
import ProductInfoModal from '../modals/productInfo';


const Report = () => {
    const movePage = useNavigate();

    function goMain() {
        movePage('/main');
    }

    const [isModalOpen, setIsModalOpen] = useState(false);

    const [name, setName] = useState(false);
    const [rI, setrI] = useState(false);
    const [tip, setTip] = useState(false);


    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    const [imgFile, setImgFile] = useState("");
    const imgRef = useRef();


    const saveImgFile = async () => { 

        const file = imgRef.current.files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = () => {
            setImgFile(reader.result);
        };

        const presignedUrl = await imageHandler(file);
        const { info } = await getProductInfo(presignedUrl);

        if (info.status === "goReport") {

        } 
        else if (info !== null) {

            console.log(info.name);

            setName(info.name);
            setrI(info.recyclingInfo);
            setTip(info.tip);
        }
        
        openModal();
    };


    return (
        <div>
            <main>
                <hr className="line" />
                <section>
                    <img id="logo2_img" src="assets/logo2.png" alt="EcoCycle logo" onClick={goMain} /> <br />
                    <span id="report_span">
                        <img id="upload_img" src={imgFile ? imgFile : "assets/report.png"} alt="Upload" />
                    </span>
                    <form>
                        <label className="uploadImage_label" htmlFor="uploadImage_input"> 사진 첨부하기</label>
                        <input type="file" id="uploadImage_input" accept="image/*" style={{ display: "none" }}
                            ref={imgRef} onChange={saveImgFile} />
                    </form>
                </section>
                <ProductInfoModal isOpen={isModalOpen} closeModal={closeModal}>
                    <div>
                        <div id="productInfoModal_span">
                            <button className="modalClose_btn" onClick={closeModal}>X</button>
                            <span className="modal_text">
                                <div id='productName_div'>
                                    <span id="title_text"  >이것은 </span>
                                    <span id="name_text">{name}</span>
                                    <span id="title_text" >입니다</span>
                                </div> <br />
                                <div id="producInfo_text" >{rI}</div> <br />
                                <div id="tip_text" >Tip. {tip}</div>
                            </span>
                        </div>
                    </div>
                </ProductInfoModal>
                <footer id="report_footer">
                    <hr className="line" />
                    <img id="copyright2_img" src="assets/copyright2.png" alt="Copyright by EcoByte" />
                </footer>
            </main>
        </div>
    );
}

export default Report;
