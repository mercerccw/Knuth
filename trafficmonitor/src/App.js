import React, {useEffect, useState} from 'react';
import moment from 'moment';
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
    const [aisMessages, updateAISMessages] = useState([]);
     const [timestamp, updateTimestamp] = useState(new Date("2018-09-11T10:38:00Z"));

    useEffect(() => {
    }, []);

    useEffect(() => {
        const interval = setInterval(() => {
            let momentTime = moment(timestamp);
            let formattedTimestamp = momentTime.toISOString();
            Request.getRecentAISMessages(formattedTimestamp).then(res => {
                updateAISMessages(res.data)
            }).catch(error => console.log(error.message));
            updateTimestamp(momentTime.add(1, 's'));
        }, 1000);
        return () => clearInterval(interval);
    }, []);

    return (
    <div className="App">
      <Map center={[55.66, 12.7875]} zoom={12}>
        <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        />
          {aisMessages.length > 0 && aisMessages.map((aisMessage) => {
              return <Marker position={[aisMessage.PositionReport.Position.coordinates[1], aisMessage.PositionReport.Position.coordinates[0]]} icon={anchor}>
                  <Popup>VESSEL</Popup>
              </Marker>
          })}
          <Marker position={[55.626233, 13.016933]} icon={anchor}>
              <Popup>Vessel information here.</Popup>
          </Marker>
      </Map>
    </div>
    );
}

export default App;
