import '../styles/App.css';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { addQuizInfo } from '../api/apis';

const AddQuiz = () => {

    const movePage = useNavigate();

    function goAdmin() {
        movePage('/admin');
    }

    const [answer, setAnswer] = useState(true);

    const [inputs, setInputs] = useState({
        content: "",
        tip: "",
    });

    const handleRadioButton = (e) => {
        setAnswer(e.target.value);
    }

    const { content, tip } = inputs;

    const onChange = (e) => {
        const { name, value } = e.target;

        setInputs({
            ...inputs,
            [name]: value,
        });
    };

    function saveQuiz() {
        console.log(content);
        console.log(answer);
        console.log(tip);

        addQuizInfo(content, answer, tip);

        setInputs({
            content: "",
            answer: true,
            tip: "",
        });
    };


    return (
        <div>
            <main className="admin_page">
                <img id="logo_img" src="assets/logo.png" alt="EcoCycle logo" onClick={goAdmin} /> <br />
                <img id="admin_img" src="assets/admin.png" alt="Admin logo" /> <br />
                <span id="add_products">
                    <div className="box">
                        <b className="text"> Q. </b>
                        <input type="text"
                            name="content"
                            placeholder="퀴즈 내용을 입력해주세요"
                            onChange={onChange}
                            value={content}
                        /> <br />
                    </div>
                    <div>
                        <b className="text"> A. </b>
                        <label>
                            <input type="radio"
                                value="true"
                                checked={answer.toString() === "true"}
                                onChange={handleRadioButton} />
                            true
                        </label>

                        <input type="radio"
                            value="false"
                            checked={answer.toString() === "false"}
                            onChange={handleRadioButton} />
                        <label>false</label>
                    </div>

                    <div className="box">
                        <b className="text"> Tip. </b>
                        <input type="text"
                            name="tip"
                            placeholder="팁을 입력해주세요"
                            onChange={onChange}
                            value={tip}
                        /> <br />
                    </div>
                </span>
                <button className="admin_btn" id="addProduct_btn" onClick={saveQuiz}>퀴즈 추가</button>
                <br />
            </main>
        </div>
    );
}

export default AddQuiz;
