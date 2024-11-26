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
import './Home.css';
import { useState } from 'react';
import femeie from '../imagine/femeie-cu-planta-removebg-preview.png'

const Home: React.FC = () => {
  const [activeIndex, setActiveIndex] = useState(0); // Tracks the active navigation button
  const middleIcons = [home, Leaderboard, Emissions , Goal]; // Icons from Ionicons library

  const handleButtonClick = (index: number) => {
    setActiveIndex(index);
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
              src="/assets/graph-placeholder.png"
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
