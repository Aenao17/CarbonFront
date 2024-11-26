import React from "react";
import {
    IonPage,
    IonContent,
    IonInput,
    IonButton,
    IonIcon,
    IonText,
} from "@ionic/react";
import {
    personAddOutline,
    lockClosedOutline,
    checkmarkCircleOutline,
} from "ionicons/icons";
import "./Register.css";

const Register: React.FC = () => {
    return (
        <IonPage>
            <IonContent className="register-content">
                <div className="register-container">
                    <h1 className="register-title">Sign Up</h1>
                    <form className="register-form">
                        <div className="input-wrapper">
                            <IonInput
                                type="text"
                                placeholder="Full Name"
                                className="register-input"
                            />
                            <IonIcon icon={checkmarkCircleOutline} className="input-icon" />
                        </div>

                        <div className="input-wrapper">
                            <IonInput
                                type="email"
                                placeholder="email"
                                className="register-input"
                            />
                            <IonIcon icon={checkmarkCircleOutline} className="input-icon" />
                        </div>

                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="Password"
                                className="register-input"
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>

                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="Confirm Password"
                                className="register-input"
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>

                        <IonButton expand="block" className="register-button">
                            SIGN UP
                        </IonButton>
                    </form>

                    <IonText className="login-text">
                        Already have an account? <a href="#login">Login</a>
                    </IonText>

                    <div className="social-login">
                        <IonText>or connect with</IonText>
                        <div className="social-buttons">
                            <IonButton className="facebook-button">FACEBOOK</IonButton>
                            <IonButton className="twitter-button">TWITTER</IonButton>
                        </div>
                    </div>
                </div>
            </IonContent>
        </IonPage>
    );
};

export default Register;