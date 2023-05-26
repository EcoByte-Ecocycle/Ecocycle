import '../styles/App.css';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { postReport } from '../api/apis';


function GoReportModal({ isOpen }) {

    const movePage = useNavigate();

    function goMain() {
        movePage('/main');
    }

    const [input, setInput] = useState("");

    const onChange = (e) => {
        setInput(e.target.value);
    };

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    const report = () => {
        console.log(`유저가 등록한 정보: ${input}`);
        postReport(input);
        goMain();
    }

    return (
        <div>
            <div className="modal" style={{ display: isOpen ? "block" : "none" }}>
                <div id="goReportModal_span" >
                    <span style={{ display: isModalOpen ? "none" : "block" }}>
                        <b className="text"> 해당 제품은 등록된 정보가 없습니다 </b> <br />
                        <b className="text"> 제보하기를 통해 정보를 등록해주세요! </b>
                        <button className="small_user_btn" onClick={openModal}>제보하러 가기</button>
                        <button className="small_user_btn" onClick={goMain}>메인 화면으로</button>
                    </span>

                    <span style={{ display: isModalOpen ? "block" : "none" }}>
                        <input type="text" id="product_info"
                            placeholder="제보할 물건의 이름을 기입해주세요"
                        onChange={onChange}
                        value={input}
                        /> <br />
                        <button className="small_user_btn" onClick={report}>제보하기</button>
                    </span>
                </div>
            </div>
        </div>
    );
}

export default GoReportModal;