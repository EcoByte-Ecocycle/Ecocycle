import axios from 'axios';

const getUserInfo = async () => {
    const accessToken = localStorage.getItem('token');

    try {
        const {data} = await axios.get("http://localhost:8080/api/users/me",
            {
                headers: {Authorization: `Bearer ${accessToken}`}
            });

        localStorage.setItem('nickname', data.nickname);
        localStorage.setItem('stamps', data.stamps);
        localStorage.setItem('dailyQuiz', data.dailyQuiz);
    } catch (err) {
        console.log(`Error in getUserInfo: ${err}`);
    }
}

const getPresignedUrl = async () => {

    const accessToken = localStorage.getItem('token');

    try {
        const data = await fetch("http://localhost:8080/api/presigned-url", {
            headers: {Authorization: `Bearer ${accessToken}`}
        });
        const jsonData = await data.json();
        return {presignedUrl: jsonData.presignedUrl};
    } catch (err) {
        console.log(`Error in getPresignedUrl: ${err}`);
    }
}

const uploadImage = (presignedUrl, file) => {
    console.log("presigned URL: ", presignedUrl);

    const res = fetch(
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
        const {data} = await axios.post("http://localhost:8080/api/products/info",
            {
                imageUrl: photoUrl
            },
            {
                headers: {Authorization: `Bearer ${accessToken}`}
            });

        console.log(data);
    } catch (err) {
        console.log(`Error in getProductInfo: ${err}`);
    }
}

export {getUserInfo, getPresignedUrl, uploadImage, getProductInfo};
