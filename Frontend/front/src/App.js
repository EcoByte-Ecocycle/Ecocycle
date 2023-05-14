import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/login';
import Main from './pages/main';
import Admin from './pages/admin';
import Report from './pages/report';


function App() {
    return (
        <div className = "App">
            <BrowserRouter>
                <Routes>
                    <Route path={"/login"} element = {<Login/>}></Route>
                    <Route path={"/main"} element = {<Main/>}></Route>
                    <Route path={"/admin"} element = {<Admin/>}></Route>
                    <Route path={"/report"} element = {<Report/>}></Route>

                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;