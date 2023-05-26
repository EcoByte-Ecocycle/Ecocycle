import axios from 'axios';

const getUserInfo = async () => {
    const accessToken = localStorage.getItem('token');

    try {
        const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/users/me`,
            {
                headers: { Authorization: `Bearer ${accessToken}` }
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
        const data = await fetch(`${process.env.REACT_APP_SERVER_URL}/presigned-url`, {
            headers: { Authorization: `Bearer ${accessToken}` }
        });
        const jsonData = await data.json();

        return { presignedUrl: jsonData.presignedUrl };
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
    ).catch((err) => {
        console.log(`Error in uploadImage: ${err}`);
    });
}

const getProductInfo = async (presignedUrl) => {
    const accessToken = localStorage.getItem('token');

    try {
        const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/products`,
            {
                headers: { Authorization: `Bearer ${accessToken}` },
                params: {url : presignedUrl},
            });

        console.log(data);
        return {info: data};

    } catch (err) {
        console.log(`Error in getProductInfo: ${err}`);

        if(err.response.status === 204) {
            var tmp = {
                "name": "goReport"
            }
            return { info: tmp };
        }
        else if(err.response.status === 400) {
            console.alert("이미지를 다시 첨부해주세요");
            return null;
        }
    }   
}

const addProductInfo = async (productName, recyclingInfo, tip) => {
    const accessToken = localStorage.getItem('token');

    console.log(productName, recyclingInfo, tip);

    try {
        const { data } = await axios.post(`${process.env.REACT_APP_SERVER_URL}/products`,
            {
                name: productName,
                recyclingInfo: recyclingInfo,
                tip: tip
            },
            {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

        console.log(data);
        return data;

    } catch (err) {
        console.log(`Error in addProductInfo: ${err}`);
    }
}

const postReport = async (productName) => {
    const accessToken = localStorage.getItem('token');

    const imageUrl = localStorage.getItem('presignedUrl');

    try {
        const { data } = await axios.post(`${process.env.REACT_APP_SERVER_URL}/reports`,
            {
                productName: productName,
                imageUrl: imageUrl
            },
            {
                headers: { Authorization: `Bearer ${accessToken}` }
            });

        console.log(data);

    } catch (err) {
        console.log(`Error in addProductInfo: ${err}`);
    }

}

export { getUserInfo, getPresignedUrl, uploadImage, getProductInfo, addProductInfo, postReport };
