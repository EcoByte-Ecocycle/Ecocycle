import '../styles/App.css';

function ProductInfoModal({isOpen, children, closeModal}) {
    return (
        <div id="productInfo_Modal" style={{ display: isOpen ? "block" : "none" }}>
            <div>{children}</div>
        </div>
    );
}

export default ProductInfoModal;