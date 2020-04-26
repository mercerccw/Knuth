import React, {useEffect, useState} from 'react';
import Request from './Requests';
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
    const [aisMessages, updateAISMessages] = useState(null);

    useEffect(() => {
        Request.getAllAISMessages().then(res => updateAISMessages(res.data));
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
