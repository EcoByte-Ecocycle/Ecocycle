import '../styles/App.css';
import '../styles/reset.css';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { addProductInfo } from '../api/apis';

const AddProduct = () => {

    const movePage = useNavigate();

    function goAdmin() {
        movePage('/admin');
    }

    const [inputs, setInputs] = useState({
        productName: "",
        recyclingInfo: "",
        tip: "",
    });

    const { productName, recyclingInfo, tip } = inputs;

    const onChange = (e) => {
        const { name, value } = e.target;

        setInputs({
            ...inputs,
            [name]: value,
        });
    };

    function sendInfo() {
        console.log(productName);
        console.log(recyclingInfo);
        console.log(tip);

        addProductInfo(productName, recyclingInfo, tip);

        setInputs({
            productName: "",
            recyclingInfo: "",
            tip: "",
        });
    };


    return (
        <div>
            <main className="admin_page">
                <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo" onClick={goAdmin} /> <br />
                <img id="admin_img" src="assets/admin.png" alt="Admin logo" /> <br />
                <span id="add_products">
                    <div id="box">
                        <b id="text"> 제품 이름: </b>
                        <input type="text"
                            id="product_name"
                            name="productName"
                            placeholder="제품 이름을 입력해주세요"
                            onChange={onChange}
                            value={productName}
                        /> <br />
                    </div>
                    <div id="box">
                        <b id="text"> 재활용 정보: </b>
                        <input type="text"
                            id="recycling_info"
                            name="recyclingInfo"
                            placeholder="재활용 정보를 입력해주세요"
                            onChange={onChange}
                            value={recyclingInfo}
                        /> <br />
                    </div>
                    <div id="box">
                        <b id="text"> 팁: </b>
                        <input type="text"
                            id="tip"
                            name="tip"
                            placeholder="팁을 입력해주세요"
                            onChange={onChange}
                            value={tip}
                        /> <br />
                    </div>
                </span>
                <button className="admin_btn" id="addProduct_btn" onClick={sendInfo}>제품 추가</button>
                <br />
            </main>
        </div>
    );
}

export default AddProduct;
