function Init() {
    var ws = null;
    var tableIdentifier = document.getElementById("identifier").value;
    
    function setMessage(value) {
        let message = document.getElementById("H2dado");
        message.innerHTML = "Rasultado do dado: "+value;
    }
    
    function startDice() {
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
                sendMessage(tableIdentifier, "OPEN");
                document.getElementById("rollDices").addEventListener("click", () => {
                    sendMessage(tableIdentifier, "MESSAGE");
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
                closeConnection(1000);
                break;
        }
    }

    function sendMessage(identifier, type) {
        var msg = {
                    "identifier" : identifier,
                    "connectionType" : type
                    };
        ws.send(JSON.stringify(msg));
        console.log(msg);
    }

    return {startDice};
}

onload = function () {
    let init = new Init();
    init.startDice();
};

