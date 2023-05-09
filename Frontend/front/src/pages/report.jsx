import '../styles/App.css';
import '../styles/reset.css';
import { useNavigate } from 'react-router-dom';

const Report = () => {
    const movePage = useNavigate();

    function goCheckReport() {
        movePage('/checkReport');
    }

    return (
        <div>
            <main>
                <hr className="line"/>
                <section>
                    <img id="logo2_img" src="assets/logo2.png" alt="EcoCycle logo" /> <br />
                    <img id="report_img" src="assets/report.png" alt="Please Upload Photo" /> <br />
                    <button className="user_btn" id="upload_btn"> 사진 첨부하기 </button>
                </section>
            </main>
            <footer id="report_footer">
                <hr className="line" />
                <img id="copyright2_img" src="assets/copyright2.png" alt="Copyright by EcoByte" />
            </footer>
        </div>
    );
}

export default Report;