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
    const [averageCoG, updateAverageCoG] = useState("0");
    const [averageSoG, updateAverageSoG] = useState("0");
    const [destinationList, updateDestinationList] = useState([]);

    //This makes a request per second to update the active AIS messages to render
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
    }, [timestamp]);

    //This function calculates the averages and updates the proper states per aisMessage state update
    useEffect(() => {
        if (aisMessages.length === 0) {
            updateAverageCoG(0);
        } else {
            //Update the averages from the active AIS messages
            let CoGtotal = 0;
            let SoGTotal = 0;
            for (let i = 0; i < aisMessages.length; i++) {
                //Update CoGTotal from all messages
                let CoG = 0;
                if (aisMessages[i].PositionReport.CoG) {
                    CoG = aisMessages[i].PositionReport.CoG;
                }
                CoGtotal += CoG;
                //Update SoGTotal from all messages
                let SoG = 0;
                if (aisMessages[i].PositionReport.SoG) {
                    SoG = aisMessages[i].PositionReport.SoG;
                }
                SoGTotal += SoG;
            }
            let CoGAverage = (CoGtotal / aisMessages.length).toFixed(3);
            let SoGAverage = (SoGTotal / aisMessages.length).toFixed(3);
            updateAverageCoG(CoGAverage);
            updateAverageSoG(SoGAverage);
        }
    }, [aisMessages]);

    useEffect(() => {
        let loctionList = [];
        aisMessages.map(message => {
            if (message.StaticData) {
                if (message.StaticData.Destination) {
                    loctionList.push(message.StaticData.Destination);
                }
            }
        });
        updateDestinationList(loctionList);
    }, [aisMessages]);

    const renderDestinations = () => {
        if (destinationList.length === 0) {
            return <p>No active destinations listed.</p>
        } else {
            return <ul>
                {destinationList.map(destination => {
                    return <li>{destination}</li>;
                })}
            </ul>
        }
    };

    return (
    <div className="App">
        <h1>Traffic Controller</h1>
        {/*These coordinates are derived from a midpoint formula which used the boundaries of the project information.*/}
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
            <h4>Averages</h4>
            <p>Average CoG: {averageCoG}&deg;</p>
            <p>Average SoG: {averageSoG} knots</p>
            <br/>
            <h4>List of Gathered Destinations from Ships:</h4>
            {renderDestinations()}
        </section>
    </div>
    );
}

export default App;
