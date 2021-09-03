//class FrontMessage {
//    constructor(msg) {
//        this.msg = msg;
//    }
//}

function GUI() {
    var ws = null;

    function setMessage(value) {
        let message = document.getElementById("H2dado");
        message.innerHTML = "Rasultado do dado: "+value;
    }

//    function init() {
//        let button = document.querySelector("input[type='button']");
//        button.onclick = startGame;
//    }
//    
    function setButtonText(txt) {
        let button = document.querySelector("input[type='button']");
        button.value = txt;
    }
    
    function startGame() {
        if (ws) {

        } else {
            ws = new WebSocket(`ws://localhost:8080/RpgDocs_Descorp/dices`);
            ws.onmessage = readData;
        }
    }
    function closeConnection(closeCode) {
        ws.close(closeCode);
        ws = null;
    }

    function readData(evt) {
        let data = JSON.parse(evt.data);
        switch (data.connectionType) {
            case "OPEN":
                document.getElementById("rollDices").addEventListener("click", () => {
                    sendMessage();
                    console.log("a")
                });
                console.log(data);
                break;
                
            case "MESSAGE":
                console.log(data);
                setMessage(data.dice);
                break;

            case "CLOSE":
                console.log(data);
                //setMessage("You Win!");
                closeConnection(1000);
                break;
        }
    }

    function sendMessage() {
        var msg = {"msg" : "teste"};
        ws.send(JSON.stringify(msg));
        console.log(msg);
    }

    function gameOver() {
        closeConnection(4000);
//        setMessage("Game Over.");
//        setButtonText("Restart");
    }
    return {startGame};
}


onload = function () {
    let gui = new GUI();
    gui. startGame();
};

