import '../styles/App.css';
import '../styles/reset.css';
import { useNavigate } from 'react-router-dom';

const Main = () => {
    const movePage = useNavigate();

    function showMenu() {}
    function goReport() {
        movePage('/report');
    }
    function goMypage() {
        movePage('/mypage');
    }
    
    return (
        <div>
            <main id="main_page">
                <section>
                    <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo" /> <br />
                    <img id="user_tree" src="assets/userTree.png" alt="User Tree"></img>
                    <footer>
                        <input type="image" id="menu_btn" src="assets/menuBtn.png" alt="Menu Button" onClick={showMenu} />
                        <input type="image" id="camera_btn" src="assets/camerBtn.png" alt="Camera Button" onClick={goReport} />
                        <input type="image" id="mypage_btn" src="assets/stampBtn.png" alt="Mypage Button" onClick={goMypage} />
                    </footer>
                </section>
            </main>
        </div>
    );
}

export default Main;