import {getPresignedUrl, uploadImage} from "../api/api.js"

const imageHandler = (imageData) => {

    const {preSignedUrl, fileName} = getPresignedUrl();

    const imageFile = imageData.toFile(fileName);
    const imageUrl = `https://s3.ap-northeast-2.amazonaws.com/ecocycle-image-upload-bucket/${fileName}`;

    uploadImage(preSignedUrl, imageFile);

    return {imageUrl};
}

export default imageHandler;
