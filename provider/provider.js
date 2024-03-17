import express from "express"
import bodyParser from "body-parser"
import axios from "axios"
import { dirname } from "path";
import { fileURLToPath } from "url";

// Define the server
const app = express();
const port = 5555;

const __dirname = dirname(fileURLToPath(import.meta.url))
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static("public"));

//Create Asset Body for API call
var createAssetReqBody = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
    },
    "@id": "",
    "properties": {
      "name": "REST API to query data",
      "contenttype": "application/json"
    },
    "dataAddress": {
      "type": "HttpData",
      "name": "Test asset",
      "baseUrl": "",
      "proxyPath": "true",
      "proxyQueryParams": "true"
    }
  };

//Create Policy Body for API call
var createPolicyDef = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/",
      "odrl": "http://www.w3.org/ns/odrl/2/"
    },
    "@id": "aPolicy",
    "policy": {
      "@type": "set",
      "odrl:permission": [],
      "odrl:prohibition": [],
      "odrl:obligation": []
    }
}

//Create Contract Definition Body for API call
var createContractDefinition = {
    "@context": {
      "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
    },
    "@id": "1",
    "accessPolicyId": "aPolicy",
    "contractPolicyId": "aPolicy",
    "assetsSelector": []
  };
  
var errorAsset = "";
var createAssetResp = "";

var createPolicyResp = "";
var errorPolicy = "";

var createContractResp = "";
var errorContract = "";

var registerResp = "";
var errorRegister = "";


// Implementation of the interface
app.get("/", (req, res) => {

    res.render("index.ejs", {createAssetResp: createAssetResp, errorAsset:errorAsset, createPolicyResp:createPolicyResp, errorPolicy:errorPolicy, createContractResp:createContractResp, 
    errorContract:errorContract, registerResp:registerResp, errorRegister:errorRegister});
  });

app.post("/create-asset", async (req, res) => {
    errorAsset = "";
    createAssetResp = "";
    try {
        createAssetReqBody["dataAddress"]["baseUrl"] = req.body.apiUrl;
        createAssetReqBody["@id"] = req.body.assetId;
        const response = await axios.post("http://localhost:19193/management/v3/assets", createAssetReqBody, 
        {
            headers: {
                "content-type": "application/json"
            }
        });
        createAssetResp = JSON.stringify(response.data);
        console.log(response.data);
    }
    catch (error) {
        errorAsset = JSON.stringify(error.response.data);
        console.log(error.response.data);
    };
    res.redirect("/");
})

app.post("/create-policy", async (req, res) => {
    errorPolicy = "";
    createPolicyResp = "";
    try {
        const response = await axios.post("http://localhost:19193/management/v2/policydefinitions", createPolicyDef, 
        {
            headers: {
                "content-type": "application/json"
            }
        });
        createPolicyResp = JSON.stringify(response.data);
        console.log(response.data);
    }
    catch (error) {
        errorPolicy = JSON.stringify(error.response.data);
        console.log(error.response.data);
    };
    res.redirect("/");
})


app.post("/create-contract", async (req, res) => {
    errorContract = "";
    createContractResp = "";
    try {
        createContractDefinition["@id"] = (parseInt(createContractDefinition["@id"]) + 1).toString();
        const response = await axios.post("http://localhost:19193/management/v2/contractdefinitions", createContractDefinition, 
        {
            headers: {
                "content-type": "application/json"
            }
        });
        createContractResp = JSON.stringify(response.data);
        console.log(response.data);
    }
    catch (error) {
        errorContract = JSON.stringify(error.reposne.data);
        console.log(error.response.data);
    };
    res.redirect("/");
})


// Implementation of the REST API
app.get("/api", (req, res) => {
    let supportedQuery = [
        {
            "/{id}" : {
                "type": "path parameter",
                "function": "Return Data of a specific object with the specified ID"
            }
        }, 
        {
            "/all" : {
                "type": "path parameter",
                "function": "Return all available data"
            }
        },
        {
            "/filter" : {
                "type": "path parameter",
                "function": "Return data that has the value of {property} {operator} then {value}",
                "guide": 'You have to attach a json-body to your get request, specifying {"value" : value, "property": {name of property}, "operator": {eq/le/gt}}'
            }
        }
    ]   
    res.send(JSON.stringify(supportedQuery, undefined, 2));
})

app.get("/api/:id", (req, res) => {
    const id = req.params.id;
    let type = parseInt(id);
    console.log(type);
    if (!isNaN(type)) {
        console.log("Inside number statement");
        let requestedData = json_data.find((data) => data.id === type);
        console.log(requestedData)
        if (requestedData != undefined) {
            res.send(JSON.stringify(requestedData, undefined, 2));
        }
        else {
            res.send("Object with id doesn't exist!");
        }
    }
    else {
        switch (id) {
            case "all":
                res.send(JSON.stringify(json_data, undefined, 2));
                console.log("Sending all data...");
                break;
            case "filter":
                let value = parseInt(req.query.value);
                let operator = req.query.operator;
                let property = req.query.property;
                let requestedData = undefined;
                switch (operator) {
                    case "gt": 
                        requestedData = json_data.filter((data) => data[property] > value);
                        res.send(JSON.stringify(requestedData, undefined, 2));
                        break;
                    case "eq":
                        requestedData = json_data.filter((data) => data[property] === value);
                        res.send(JSON.stringify(requestedData, undefined, 2));
                        break;
                    case "le":
                        requestedData = json_data.filter((data) => data[property] < value);
                        res.send(JSON.stringify(requestedData, undefined, 2));
                        break;
                    default: 
                        res.send("Operator isn't supported");
                }
                break;
            default: 
                res.send("Query Path not supported!");
                break;
        }
    }
})

 
app.post("/register", async (req, res) => {
    registerResp = "";
    errorRegister = "";
    let registerBody = {
        "@context": {
          "@vocab": "https://w3id.org/edc/v0.0.1/ns/"
        },
        "@id": "http-pull-provider-dataplane",
        "url": "http://localhost:19192/control/transfer",
        "allowedSourceTypes": [
          "HttpData"
        ],
        "allowedDestTypes": [
          "HttpProxy",
          "HttpData"
        ],
        "properties": {
          "https://w3id.org/edc/v0.0.1/ns/publicApiUrl": "http://localhost:19291/public/"
        }
      };
    try {
        const response = await axios.post("http://localhost:19193/management/v2/dataplanes", registerBody, {
            headers: {
                "content-type": "application/json"
            }
        });
        registerResp = JSON.stringify(response.data);
    }
    catch (error) {
        errorRegister = JSON.stringify(error.reposne.data);
        console.log(error.response.data);
    }
    res.redirect("/");
})

//Start the server
app.listen(port, () => {
    console.log(`Provider Backend server is running on http://localhost:${port}`);
});


// In Memory Data

const json_data = [
    {
        "id": 1,
        "property1": "something-11",
        "property2": "something-12",
        "property3": 200
    }, 
    {
        "id": 2,
        "property1": "something-21",
        "property2": "something-22",
        "property3": 300
    },
    {
        "id":3,
        "property5":500
    }
]