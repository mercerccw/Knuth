//This file contains all the http requests that are available through the Node backend.
//Created with the help of Axios and config.js, this implements the REST client through attaching it to functions.

import Axios from "axios";
import API_BASE_URL from "./config";

export default class Request {

    static getAllAISMessages() {
        return Axios.get(API_BASE_URL);
    }

    static getRecentAISMessages(timestamp) {
        return Axios.get(API_BASE_URL + 'recentAIS/' + timestamp);
    }
}