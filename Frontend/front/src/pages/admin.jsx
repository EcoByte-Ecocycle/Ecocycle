import '../styles/App.css';
import '../styles/reset.css';
import { useNavigate } from 'react-router-dom';

const Admin = () => {
    const movePage = useNavigate();

    function goCheckReport() {
        movePage('/checkReport');
    }
    function goManageUsers() {
        movePage('/manageUsers');
    }
    function goManageQuizzes() {
        movePage('/manageQuizzes');
    }
    function goAddProduct() {
        movePage('/addProduct');
    }

    return (
        <div>
            <main class="admin_page">
                <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo" /> <br />
                <img id="admin_img" src="assets/admin.png" alt="EcoCycle logo" /> <br />
                <span id="admin_btns">
                    <button className="admin_btn" id="checkReport_btn">제보 확인</button> <br />
                    <button className="admin_btn" id="manageUser_btn">사용자 관리</button> <br />
                    <button className="admin_btn" id="manageQuiz_btn">퀴즈 관리</button> <br />
                    <button className="admin_btn" id="addProduct_btn" onClick={goAddProduct}>제품 추가</button>
                </span>
            </main>
        </div>
    );
}

export default Admin;