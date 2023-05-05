import useGoogle from '../hooks/UseGoogle';

const ci = process.env.REACT_APP_CLIENT_ID;

const Login = () => {
    const {loginUrl} = useGoogle ({
        clientId: ci,
    })

    return (
        <div>
            <h1>Login</h1>
            <a href={loginUrl}>google login</a>
        </div>
    );
}

export default Login;