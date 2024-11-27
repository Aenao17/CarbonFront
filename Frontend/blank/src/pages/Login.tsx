import React, {useState} from "react";
import {
    IonPage,
    IonContent,
    IonInput,
    IonButton,
    IonIcon,
    IonText, IonCardContent, IonCard,
} from "@ionic/react";
import { checkmarkCircleOutline, lockClosedOutline } from "ionicons/icons";
import "./style/Login.css";
const Login = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin =async() =>{
        if(email == '' || password == ''){
            alert('Please fill in all fields');
            console.log(email + " " + password);
            return;
        }
        const id=1;
        const response = await fetch('http://localhost:8080/auth/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify({
                'id': id,
                'username': email,
                'password': password
            }),
            credentials: 'include'
        });
        const data = await response.json();

        if(data.token !== undefined){
            localStorage.setItem('token', data.token);
            localStorage.setItem('username', email);
            window.location.href = '/home';
        }
    }

    return (
        <IonPage>
            <IonCard className="login-content">
                <IonCardContent className="login-content">
                    <div className="login-container">
                        <h1 className="login-title">Login</h1>

                        <div className="input-wrapper">
                            <IonInput
                                type="email"
                                placeholder="email"
                                className="login-input"
                                value={email}
                                onIonChange={e => setEmail(e.detail.value!)}
                            />
                            <IonIcon icon={checkmarkCircleOutline} className="input-icon" />
                        </div>


                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="password"
                                className="login-input"
                                value={password}
                                onIonChange={e => setPassword(e.detail.value!)}
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>


                        <IonButton expand="block" className="login-button" onClick={handleLogin}>
                            LOGIN
                        </IonButton>

                        {/* Link pentru Sign Up */}
                        <IonText className="signup-text">
                            Don’t have an account? <a href="/register">Sign up</a>
                        </IonText>
                    </div>
                </IonCardContent>
            </IonCard>
        </IonPage>
    );
};

export default Login;