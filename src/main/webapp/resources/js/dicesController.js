function GUI() {
    var ws = null;

    function setMessage(msg) {
        let message = document.getElementById("message");
        message.innerHTML = msg;
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
            ws = new WebSocket(`ws://localhost:8080/RpgDocs_Descorpdices/Dices`);
            ws.onmessage = readData;
        }
    }
    function closeConnection(closeCode) {
        ws.close(closeCode);
        ws = null;
    }

    function readData(evt) {
        let data = JSON.parse(evt.data);
        switch (data.type) {
            case "OPEN":
                break;
                
            case "MESSAGE":
                console.log(data);
                break;

            case "CLOSE":
                console.log(data);
                //setMessage("You Win!");
                closeConnection(1000);
                break;
        }
    }

    function sendMessage(board, piece, type, queue) {
        var msg = new FrontMessage(board, piece, type, queue);
        ws.send(JSON.stringify(msg));
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

