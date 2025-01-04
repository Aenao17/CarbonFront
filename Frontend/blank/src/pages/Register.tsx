import React, { useState } from "react";
import {
    IonPage,
    IonContent,
    IonInput,
    IonButton,
    IonIcon,
    IonText, IonCard, IonCardContent,
} from "@ionic/react";
import {
    personAddOutline,
    lockClosedOutline,
    checkmarkCircleOutline,
} from "ionicons/icons";
import "./style/Register.css";

const Register: React.FC = () => {
    const [email,setEmail] = useState('');
    const [password,setPassword] = useState('');
    const [pass, setPass] = useState('');

    const handleRegister = async () => {
        if(email == '' || password == '' || pass == ''){
            alert('Please fill in all fields');
            return;
        }

        if(password !== pass){
            alert('Passwords do not match');
            return;
        }
        const id=1;
        console.log(email,password,pass);
        const response = await fetch('http://localhost:8080/auth/register',{
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
        if (data.status === 'ok') {
            alert('Registration successful');
        }
        if (data.status === 'Username already exists'){
            alert('User already registered');
        }
    }

    return (
        <IonPage>
            <IonCard className="register-content">
            <IonCardContent>
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
                                value={email}
                                onIonChange={e=>setEmail(e.detail.value!)}
                            />
                            <IonIcon icon={checkmarkCircleOutline} className="input-icon" />
                        </div>

                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="Password"
                                className="register-input"
                                value={password}
                                onIonChange={e=>setPassword(e.detail.value!)}
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>

                        <div className="input-wrapper">
                            <IonInput
                                type="password"
                                placeholder="Confirm Password"
                                className="register-input"
                                value={pass}
                                onIonChange={e=>setPass(e.detail.value!)}
                            />
                            <IonIcon icon={lockClosedOutline} className="input-icon" />
                        </div>

                        <IonButton expand="block" className="register-button" onClick={handleRegister}>
                            SIGN UP
                        </IonButton>
                    </form>

                    <IonText className="login-text">
                        Already have an account? <a href="/login">Login</a>
                    </IonText>
                </div>
            </IonCardContent>
            </IonCard>
        </IonPage>
    );
};

export default Register;