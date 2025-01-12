import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonButton,
  IonIcon,
} from '@ionic/react';
import Leaderboard from '../imagine/Leaderbord.svg'
import Emissions from '../imagine/Emissions.svg'
import Goal from '../imagine/Goal.svg'
import home from '../imagine/Home.svg'
import './style/Home.css';
import React, { useState } from 'react';
import { useHistory } from "react-router-dom"

import femeie from '../imagine/femeie-cu-planta-removebg-preview.png'
import grafic from '../imagine/grafic-removebg-preview.png'
const Home: React.FC = () => {
  const [activeIndex, setActiveIndex] = useState(0); // Tracks the active navigation button
  const middleIcons = [home, Leaderboard, Emissions , Goal]; // Icons from Ionicons library
  const history = useHistory();


  const handleButtonClick = (index: number) => {
    setActiveIndex(index);

    if (index === 0) {
        history.push("/home") //Home
    }
    if (index === 1){
        history.push("/leaderboard");
    }
    if (index === 2){
        history.push("/Chestionar");
    }
    if (index === 3) {
        history.push("/Register");
    }
  };
  

  return (
    <IonPage>
      {/* Header */}
      <IonHeader>
        <IonToolbar className="header-toolbar">
          <IonTitle>My Footprint Tracker</IonTitle>
        </IonToolbar>
      </IonHeader>

      {/* Main Content */}
      <IonContent fullscreen>
        <div className="home-container">
          {/* CO₂ Details Section */}
          <div className="footprint-details">
            <h1 className="co2-stat">
              17.4 <span>ton CO₂/year</span>
            </h1>
            <p className="comparison">+5% since last month</p>
          </div>

          {/* Chart Section */}
          <div className="chart">
            <img
              src={grafic}
              alt="Monthly Progress Chart"
              className="chart-image"
            />
            <div className="months">
              <span>Jan</span>
              <span>Feb</span>
              <span>Mar</span>
              <span>Apr</span>
            </div>
          </div>

          {/* Illustration Section */}
          <div className="illustration">
            <img
              src={femeie}
              alt="Person Holding Plant"
              className="illustration-image"
            />
          </div>
        </div>

        {/* Navigation Bar */}
        <div className="navigation-bar">
          {middleIcons.map((icon, index) => (
            <IonButton
              key={index}
              className={`menu-item ${activeIndex === index ? 'active' : ''}`}
              onClick={() => handleButtonClick(index)}
              fill="clear"
            >
              <IonIcon icon={icon} className="nav-icon" />
            </IonButton>
          ))}
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Home;