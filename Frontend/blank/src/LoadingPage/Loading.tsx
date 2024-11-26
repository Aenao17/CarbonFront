import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import './Loading.css';
import carbonFootprintImg from '../imagine/carbon-footprint-removebg-preview.png';

const Loading: React.FC = () => {
  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Track Your Carbon Footprint</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <div className="container">
          <div className="illustration">
            {/* Add your illustration or image */}
            <img
              src={carbonFootprintImg}
              alt="Recycling Illustration"
              className="image"
            />
          </div>
          <h1 className="title">Track Your Carbon Footprint</h1>
        </div>
      </IonContent>
    </IonPage>
  );
};

export default Loading;
