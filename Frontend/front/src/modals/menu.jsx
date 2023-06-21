import '../styles/App.css';

function MenuModal({isOpen, children}) {
    return (
        <div id="dailyQuiz_Modal" style={{ display: isOpen ? "block" : "none" }}>
            <div>{children}</div>
        </div>
    );
}

export default MenuModal;