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
     const [timestamp, updateTimestamp] = useState(moment(new Date("2018-09-11T10:38:00Z")));

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
    }, [timestamp, aisMessages]);

    return (
    <div className="App">
        <h1>Traffic Controller</h1>
        <Map center={[55.66, 12.7875]} zoom={10}>
        <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        />
          {/*Logically present vessels on leaflet map with popup information.*/}
          {aisMessages.length > 0 && aisMessages.map((aisMessage) => {
              let coordinates = [aisMessage.PositionReport.Position.coordinates[1], aisMessage.PositionReport.Position.coordinates[0]];
              let messageTimestamp = moment(aisMessage.Timestamp).toISOString();
              return <Marker position={coordinates} icon={anchor}>
                  <Popup>
                      <h3>AIS Message</h3>
                      <ul>
                          <li>MMSI: {aisMessage.MMSI}</li>
                          <li>CoG: {aisMessage.PositionReport.CoG}&deg;</li>
                          <li>Coordinates:
                              <ol>
                                  <li>Lat: {coordinates[0]}</li>
                                  <li>Lon: {coordinates[1]}</li>
                              </ol>
                          </li>
                          <li>Timestamp: {messageTimestamp}</li>
                      </ul>
                  </Popup>
              </Marker>
          })}
        </Map>
        <h2>AIS Statistics</h2>
        <h3>Current Information About Active Ships</h3>
        <section className="stats">

        </section>
    </div>
    );
}

export default App;
