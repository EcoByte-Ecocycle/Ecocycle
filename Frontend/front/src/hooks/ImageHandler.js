import {getPresignedUrl, uploadImage} from '../api/api.js'

const imageHandler = async (imageFile) => {

    const {presignedUrl} = await getPresignedUrl();

    await uploadImage(presignedUrl, imageFile);

    return {presignedUrl};
}

export default imageHandler;
