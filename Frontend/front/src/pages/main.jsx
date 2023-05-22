import '../styles/App.css';
import '../styles/reset.css';
import {useNavigate} from 'react-router-dom';
import makeMain from '../hooks/MakeMain';

const Main = () => {
    const movePage = useNavigate();

    const {stateOfTree} = makeMain();

    let photoPath;

    if (stateOfTree === 'seed') {
        photoPath = 'assets/userSeed.PNG';
    } else if (stateOfTree === 'sprout') {
        photoPath = 'assets/userSprout.PNG';
    } else if (stateOfTree === 'seedling') {
        photoPath = 'assets/userSeedling.PNG';
    } else if (stateOfTree === 'tree') {
        photoPath = 'assets/userTree.png';
    }


    function showMenu() {
    }

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
                    <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo"/> <br/>
                    <img id="user_tree" src={`${photoPath}`} alt="User Tree"/>
                    <footer id="main_footer">
                        <input type="image" id="menu_btn" src="assets/menuBtn.png" alt="Menu Button"
                               onClick={showMenu}/>
                        <input type="image" id="camera_btn" src="assets/camerBtn.png" alt="Camera Button"
                               onClick={goReport}/>
                        <input type="image" id="mypage_btn" src="assets/stampBtn.png" alt="Mypage Button"
                               onClick={goMypage}/>
                    </footer>
                </section>
            </main>
        </div>
    );
}

export default Main;
