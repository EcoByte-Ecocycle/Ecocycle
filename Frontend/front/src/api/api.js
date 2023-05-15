import axios from 'axios';
import { defaultInstance, authInstance } from './utils';

const getUserInfo = async () => {
    const accessToken = localStorage.getItem('token');

    try {
        const { data } = await axios.get("http://localhost:8080/api/users/me",
            {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

        localStorage.setItem('nickname', data.nickname);
        localStorage.setItem('stamps', data.stamps);
        localStorage.setItem('dailyQuiz', data.dailyQuiz);
    }
    catch (err) {
        console.log(`Error in getUserInfo: ${err}`);
    }
}

const getPresignedUrl = async () => {

    const accessToken = localStorage.getItem('token');
    const userName = localStorage.getItem('nickname');

    const getFormatedToday = () => {
        let now = new Date();
        let toDayYear = now.getFullYear();
        let toDayMonth = (now.getMonth() + 1) > 9 ? (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
        let todayDate = now.getDate() > 9 ? now.getDate() : '0' + now.getDate();
        let hours = now.getHours() > 9 ? now.getHours() : '0' + now.getHours();
        let minutes = now.getMinutes() > 9 ? now.getMinutes() : '0' + now.getMinutes();
        return toDayYear + toDayMonth + todayDate + hours + minutes;
    }

    console.log(getFormatedToday());

    try {
        const fileName = `${userName}_${getFormatedToday()}.png`;
        const response = await axios.get("http://localhost:8080/api/presigned-url",
            {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

        return { preSignedUrl: response.preSignUrl, fileName };

    }
    catch (err) {
        console.log(`Error in getPresignedUrl: ${err}`);
    }
}

const uploadImage = async (presignedUrl, file) => {
    console.log("presigned URL: ", presignedUrl);

    const res = await fetch(
        new Request(presignedUrl, {
            method: 'PUT',
            body: file,
            headers: new Headers({
                'Content-Type': 'image/*',
            }),
        })
    );
    if (res.status !== 200) {
        console.log("Error in uploadImage");
    }
}

const getProductInfo = async (photoUrl) => {
    const accessToken = localStorage.getItem('token');

    try {
        const { data } = await axios.post("http://localhost:8080/api/products/info",
            {
                imageUrl: photoUrl
            },
            {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

        console.log(data);
    }
    catch (err) {
        console.log(`Error in getProductInfo: ${err}`);
    }
}

export { getUserInfo, getPresignedUrl, uploadImage, getProductInfo };