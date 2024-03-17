import express from "express"
import bodyParser from "body-parser"
import axios from "axios"
import { dirname } from "path";
import { fileURLToPath } from "url";

// Define the consumer's server
const app = express();
const port = 4000;
var negotiationStatus = "";

var transferStatus = "";
var contractId = "";
var authKey = "";
var authCode = "";
var endpoint = "";

const __dirname = dirname(fileURLToPath(import.meta.url))
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static("public"));


async function checkNegotiationStatus(negotiationId) {
    const response = await axios.get("http://localhost:29193/management/v2/contractnegotiations/"+negotiationId,
    {
        headers: {
            "Content-Type": "application/json"
        }
    });
    if (response.data.state == "TERMINATED" || response.data.state == "FINALIZED") {
        if (response.data.state == "TERMINATED") {
            let negotiationError = JSON.parse(response.data.errorDetail)["dspace:reason"];
            console.log(negotiationError);
            negotiationStatus = `Negotiation Process ${negotiationId} failed due to ${negotiationError}`; 
        }
        else {
            negotiationStatus = `Negotiation Process ${negotiationId} sucessful with agreement ID: ${response.data.contractAgreementId}`; 
        }
    }
    else {
        setTimeout(checkNegotiationStatus, 1000, negotiationId);
    }
}

async function checkTransferStatus(transferId) {
    const response = await axios.get("http://localhost:29193/management/v2/transferprocesses/"+ transferId);
    if (response.data.state === "TERMINATED") {
        transferStatus = `Transfer Process not started due to ${response.data.errorDetail}`;
}}

//Catalog Request's Body
const fetchCatalogReqBody = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
    },
    "counterPartyAddress": "http://localhost:19194/protocol",
    "protocol": "dataspace-protocol-http"
};

//Contract Negotiation Request's Body
var contractNegReqBody = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
    },
    "@type": "NegotiationInitiateRequestDto",
    "connectorId": "provider",
    "counterPartyAddress": "http://localhost:19194/protocol",
    "consumerId": "consumer",
    "providerId": "provider",
    "protocol": "dataspace-protocol-http",
    "policy": {
      "@context": "http://www.w3.org/ns/odrl.jsonld",
      "@id": "",
      "@type": "Set",
      "permission": [],
      "prohibition": [],
      "obligation": [],
      "target": ""
    }
  }


// Start Transfer Request's Body
var startTransferBody = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
    },
    "@type": "TransferRequestDto",
    "connectorId": "provider",
    "counterPartyAddress": "http://localhost:19194/protocol",
    "contractId": "",
    "assetId": "",
    "protocol": "dataspace-protocol-http",
    "dataDestination": {
      "type": "HttpProxy"
    }
  }

var guide = "";

//Implementation of the interface
app.get("/", async (req, res) => {
    try {
        const response = await axios.post("http://localhost:29193/management/v2/catalog/request", fetchCatalogReqBody,
        {
            headers: {
                "Content-Type": "application/json"
            }
        });
        if (authCode === "") {
            res.render("index.ejs", { catalog: JSON.stringify(response.data["dcat:dataset"], undefined, 2), negotiationStatus: negotiationStatus, transferStatus: transferStatus});
        } else if (queryResp.length === 0){
            res.render("index.ejs", { catalog: JSON.stringify(response.data["dcat:dataset"], undefined, 2), negotiationStatus: negotiationStatus, transferStatus: transferStatus, 
        authCode: authCode, authKey:authKey, endpoint:endpoint, contractId:contractId, guide:guide});
        } else {
            res.render("index.ejs", { catalog: JSON.stringify(response.data["dcat:dataset"], undefined, 2), negotiationStatus: negotiationStatus, transferStatus: transferStatus, 
        authCode: authCode, authKey:authKey, endpoint:endpoint, contractId:contractId, guide:guide, queryResp: queryResp});
        }

    } catch (error) {
        console.log(error);
    }
  });

app.post("/negotiate", async (req, res) => {
    contractNegReqBody["policy"]["@id"] = req.body["contractId"];
    contractNegReqBody["policy"]["target"] = req.body["assetId"];
    negotiationStatus = "";
    try {
        const response = await axios.post("http://localhost:29193/management/v2/contractnegotiations", contractNegReqBody, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        const negotiationId = response.data["@id"];
        negotiationStatus = "Negotiation Process initiated";
        setTimeout(checkNegotiationStatus, 1000, negotiationId);

    }
    catch (error) {
        console.log(error);
    }
    res.redirect("/");
  }) 

app.post("/transfer", async (req, res) => {
    transferStatus = "";
    startTransferBody["contractId"] = req.body["agreementId"];
    startTransferBody["assetId"] = req.body["assetId"];
    try {
        const response = await axios.post("http://localhost:29193/management/v2/transferprocesses", startTransferBody, {
            headers: {
                "Content-Type": "application/json"
            }
        });
        console.log(response.data);
        let transferId = response.data["@id"];
        transferStatus = "Transfer Process Started";
        setTimeout(checkTransferStatus, 1000, transferId);

    }
    catch (error) {
        console.log(error);
    }
    res.redirect("/");
}) 

app.post("/receiver", async (req, res) => {
    console.log(req.body);
    authCode = req.body.authCode;
    authKey = req.body.authKey;
    endpoint = req.body.endpoint;
    contractId = req.body.contractId;
    try {
        const response = await axios.get(endpoint, {
            headers: {
                'Authorization': authCode
            }
        });
        guide = JSON.stringify(response.data, undefined, 2);
    }
    catch (error) {
        console.log(error.response.data);
    }
    res.redirect("/");
})


//Implementation of the query using the REST API provided by the provider
var queryResp = "";
app.post("/query", async (req, res) => {
    console.log("Query is hit");
    queryResp = "";
    let authCode = req.body.authCode;
    let endpoint = req.body.endpoint;
    let query = req.body.query;
    let value = req.body.value;
    let operator = req.body.operator;
    let property = req.body.property;
    try {
        if (value != "") {
            const response = await axios.get(endpoint + query +`?operator=${operator}&value=${value}&property=${property}`, {
                headers: {
                    'Authorization': authCode
                }
            })
            console.log(response.data);
            queryResp = JSON.stringify(response.data, undefined, 2);
        }
        else {
            const response = await axios.get(endpoint + query, {
                headers: {
                    'Authorization': authCode
                }
            })
            console.log(response.data);
            queryResp = JSON.stringify(response.data, undefined, 2);
        }
     } catch (error) {
        console.log(error);
    }
    res.redirect("/");
})

//Start the app
app.listen(port, () => {
    console.log(`Consumer Backend server is running on http://localhost:${port}`);
});