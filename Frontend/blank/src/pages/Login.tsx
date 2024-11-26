import React from "react";
import {
    IonPage,
    IonContent,
    IonInput,
    IonButton,
    IonIcon,
    IonText,
} from "@ionic/react";
import { checkmarkCircleOutline, lockClosedOutline } from "ionicons/icons";
import facebookImg from "../images/Facebook.png";
import twitterImg from "../images/twitter.png";
import "./Login.css";
const Login: React.FC = () => {
    return (
        <IonPage>
            <IonContent className="login-content">
                <div className="login-container">
                    <h1 className="login-title">Login</h1>
                    <form className="login-form">
                        {/* Input pentru Email */}
                        <div className="input-wrapper">
                            <IonInput
                                type="email"
                                placeholder="email"
                                className="login-input"
                            />
                            <IonIcon icon={checkmarkCircleOutline} className="input-icon" />
                        </div>

                        {/* Input pentru Parolă */}
                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="password"
                                className="login-input"
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>

                        {/* Buton Login */}
                        <IonButton expand="block" className="login-button">
                            LOGIN
                        </IonButton>
                    </form>

                    {/* Link pentru Forgot Password */}
                    <IonText className="forgot-password">Forgot your password?</IonText>

                    {/* Connect with social media */}
                    <div className="social-login">
                        <IonText>connect with</IonText>
                        <div className="social-buttons">
                            <IonButton className="facebook-button">
                                <img src={facebookImg} alt="Facebook" className="social-icon" />
                                FACEBOOK
                            </IonButton>
                            <IonButton className="twitter-button">
                                <img src={twitterImg} alt="Twitter" className="social-icon" />
                                TWITTER
                            </IonButton>
                        </div>
                    </div>

                    {/* Link pentru Sign Up */}
                    <IonText className="signup-text">
                        Don’t have an account? <a href="#signup">Sign up</a>
                    </IonText>
                </div>
            </IonContent>
        </IonPage>
    );
};

export default Login;