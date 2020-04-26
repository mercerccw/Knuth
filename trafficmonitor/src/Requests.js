//This file contains all the http requests that are available through the Node backend.
//Created with the help of Axios and config.js, this implements the REST client through attaching it to functions.

import Axios from "axios";
import {API_BASE} from "./config";

export const getAllAISMessages = () => Axios.get(API_BASE + '').then(function (response) {
    return response.data;
});