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

export const defaultInstance = axiosApi(BASE_URL);
export const authInstance = axiosAuthApi(BASE_URL);