  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyDvvnPLQMzF3JOdg-ddsdV5sah6FpEpOsY",
    authDomain: "doctalkfinal-ce5b5.firebaseapp.com",
    databaseURL: "https://doctalkfinal-ce5b5.firebaseio.com",
    projectId: "doctalkfinal-ce5b5",
    storageBucket: "doctalkfinal-ce5b5.appspot.com",
    messagingSenderId: "1039956260614"
  };
  firebase.initializeApp(config);
  var flag=0;
  var emailField = document.getElementById("email");
  var pswField = document.getElementById("password");
  const btLogin = document.getElementById("btlogin");




firebase.auth().onAuthStateChanged(firebaseUser =>  {
    if(firebaseUser){
        console.log(firebaseUser);
    }else{
        console.log("not logged in");
    }
})

function doc(){
    const email=emailField.value;
    const psw=pswField.value;
    const auth=firebase.auth();
    const promise=auth.signInWithEmailAndPassword(email,psw);
    (promise.then(function (){window.location.assign("doctor_home.html");}).catch(e=> {window.flag=1; alert(e.message);
                                       }));

}

function patient(){
    const email=emailField.value;
    const psw=pswField.value;
    const auth=firebase.auth();
    const promise=auth.signInWithEmailAndPassword(email,psw);
    (promise.then(function (){window.location.assign("patient_home.html");}).catch(e=> {window.flag=1; alert(e.message);
                                       }));

}

// function forgot(){
//     var auth = firebase.auth();
//     var emailAddress = emailField.value;

//     auth.sendPasswordResetEmail(emailAddress).then(function() {
//         alert("Mail Sent Successfully");
//         window.location.assign();
//     }).catch(function(error) {
//         alert("There were some errors. Check if you have blocked the external mails and try again");
//     });
// }