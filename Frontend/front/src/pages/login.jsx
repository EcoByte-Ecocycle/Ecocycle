import useGoogle from '../hooks/UseGoogle';
import '../styles/App.css';
import '../styles/reset.css';

const CI = process.env.REACT_APP_CLIENT_ID;

const Login = () => {

    const { loginUrl } = useGoogle({
        clientId: CI,
    })

    return (
        <div>
            <hr className="line" />
            <main>
                <section>
                    <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo" /> <br />
                    <button className="user_btn" id="login_btn" onClick={() => { window.location.href = loginUrl }}>Google로 로그인</button>
                </section>
                <footer>
                    <hr className="line" />
                    <img id="copyright_img" src="assets/copyright.png" alt="Copyright by EcoByte" />
                </footer>
            </main>
        </div>
    );
}

export default Login;