import { useNavigate } from 'react-router-dom';
import { useEffect } from "react";
import qs from 'qs';
import axios from 'axios';

const useGoogle = ({
    clientId
}) => {
    const redirect_uri = process.env.REACT_APP_REDIRECT_URI;

    const loginQueryString = qs.stringify({
        client_id: clientId,
        redirect_uri,
        response_type: 'code',
        scope: process.env.REACT_APP_SCOPE
    })

    const movePage = useNavigate();

    const goAdmin = () => {
        movePage('/admin');
    }

    const goMain = () => {
        movePage('/main');
    }

    useEffect(() => {
        (async () => {
            if (window.location.search.split('?').length > 1) {
                console.log(window.location.search.split('?')[1]) 
                const { code } = qs.parse(window.location.search.split('?')[1]) 

                const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/login?code=${code}`);
                
                localStorage.clear();
                localStorage.setItem('token', data.accessToken);  


                if (data.role === "user") {
                    goMain();
                }
                else if (data.role === "admin") {
                    goAdmin();
                }
            }
        }) ();
    }, [])


    return {
        loginUrl: process.env.REACT_APP_AUTHORIZE_URI + "?" + loginQueryString
    }
}

export default useGoogle;