import {getPresignedUrl, uploadImage} from '../api/apis.js'

const imageHandler = async (imageFile) => {

    const {presignedUrl} = await getPresignedUrl();

    const tmp = presignedUrl.split("?");
    console.log(tmp[0]);

    await uploadImage(tmp[0], imageFile);

    return {presignedUrl};
}

export default imageHandler;
