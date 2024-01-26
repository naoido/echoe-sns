import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
    apiKey: "AIzaSyBMvxe5GcDTdEe2Vs3cRpdlR_of5oFOWVw",
    authDomain: "echoe-6e9a2.firebaseapp.com",
    databaseURL: "https://echoe-6e9a2-default-rtdb.firebaseio.com",
    projectId: "echoe-6e9a2",
    storageBucket: "echoe-6e9a2.appspot.com",
    messagingSenderId: "118672577020",
    appId: "1:118672577020:web:5b23da4f3d85cdb0f9e279",
    measurementId: "G-PFBY7DWR82"
};

initializeApp(firebaseConfig);

export const auth = getAuth();