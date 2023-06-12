import {getPresignedUrl, uploadImage} from '../api/apis.js'

const imageHandler = async (imageFile) => {

    const {presignedUrl} = await getPresignedUrl();

    await uploadImage(presignedUrl, imageFile);

    const tmp = presignedUrl.split("?");
    return tmp[0];
}

export default imageHandler;
