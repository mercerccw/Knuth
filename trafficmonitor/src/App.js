import React, {useEffect} from 'react';
import * as request from './Requests';
import './App.css';
import {Map, Marker, Popup, TileLayer} from "react-leaflet";
import { Icon } from "leaflet";

const vessel = new Icon({
    iconUrl: "../assets/vessel.png",
    iconRetinaUrl: "../assets/vessel.png",
    iconSize: [25, 25]
});

const anchor = new Icon({
    iconUrl: "/anchor.png",
    iconRetinaUrl: "/anchor.png",
    iconSize: [25, 25],
});

function App() {
    useEffect(() => {
        console.log(request.getAllAISMessages());
    }, []);

    return (
    <div className="App">
      <Map center={[55.66, 12.7875]} zoom={12}>
        <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        />
          <Marker position={[55.626233, 13.016933]} icon={anchor}>
              <Popup>Vessel information here.</Popup>
          </Marker>
      </Map>
    </div>
    );
}

export default App;
