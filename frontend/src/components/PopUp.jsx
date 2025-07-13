import './style.css'
import './popup.css'

function PopUp({showPopUp, closePopUp, children}){
  if (!showPopUp) {return null}
  return (
    <div className="popup-container">
    <div className="PopUp" >
        <button id="pop-button" onClick={closePopUp} >❌</button>
        {children}
    </div>
    </div>
  );
};

export default PopUp;