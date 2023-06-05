import '../styles/App.css';

function DailyQuizModal({isOpen, children, closeModal}) {
    return (
        <div id="dailyQuiz_Modal" style={{ display: isOpen ? "block" : "none" }}>
            <div>{children}</div>
        </div>
    );
}

export default DailyQuizModal;