import { useState, useEffect } from "react";
import qs from 'qs';
import axios from 'axios';

//const AUTHORIZE_URI = "https://accounts.google.com/o/oauth2/v2/auth";

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

    useEffect(() => {
        (async () => {
            if (window.location.search.split('?').length > 1) {
                console.log(window.location.search.split('?')[1]) //왜 두번씩 찍히지?
                const { code } = qs.parse(window.location.search.split('?')[1]) 

                //console.log(code)
                //console.log(scope)

                //const { data } = await axios.get(`http://localhost:3000/login?code=${code}&redirect_uri=${redirect_uri}`);
                //console.log(data)

                axios.post(`http://localhost:8080/login/?code=${code}`, {

                })
                .then(function(response) {
                    console.log(response);
                })
                // .catch(fuction(error) {
                //     console.log(error);
                // })
            } else if (window.location.search.split('#').length) { }
        }) ();
    }, [])

    return {
        loginUrl: process.env.REACT_APP_AUTHORIZE_URI + "?" + loginQueryString
    }
}

export default useGoogle;