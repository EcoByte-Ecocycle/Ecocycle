import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/'

const axiosApi = (url, options) => {
    const instance = axios.create({baseURL: url})
    return instance
}

const axiosAuthApi = (url, options) => {
    const token = process.env.REACT_APP_ACCESS_TOKEN;
    const instance = axios.create({
        baseURL: url,
        headers: { Authorization: 'Bearer ' + token},
    })
    return instance
}

const setUserInfo = async () => {
    const accessToken = localStorage.getItem('token');

    try {
        const { data } = await axios.get("http://localhost:8080/api/users/me", 
        {     
            headers: { Authorization: `Bearer ${accessToken}` }
        });
        
        localStorage.setItem('nickname' , data.nickname);
        localStorage.setItem('stamps' , data.stamps);
        localStorage.setItem('dailyQuiz' , data.dailyQuiz);
    }
    catch(err) {
        console.log(`Error in setUserInfo: ${err}`);
    }
}

export const defaultInstance = axiosApi(BASE_URL);
export const authInstance = axiosAuthApi(BASE_URL);
export default setUserInfo;