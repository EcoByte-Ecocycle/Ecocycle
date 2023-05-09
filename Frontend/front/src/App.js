import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/login';
import Main from './pages/main';

function App() {
    return (
        <div className = "App">
            <BrowserRouter>
                <Routes>
                    <Route path={"/login"} element = {<Login/>}></Route>
                    <Route path={"/main"} element = {<Main/>}></Route>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;