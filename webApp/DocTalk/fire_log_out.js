var config = {
    apiKey: "AIzaSyDvvnPLQMzF3JOdg-ddsdV5sah6FpEpOsY",
    authDomain: "doctalkfinal-ce5b5.firebaseapp.com",
    databaseURL: "https://doctalkfinal-ce5b5.firebaseio.com",
    projectId: "doctalkfinal-ce5b5",
    storageBucket: "doctalkfinal-ce5b5.appspot.com",
    messagingSenderId: "1039956260614"
  };
  firebase.initializeApp(config);

  var btLogOut = document.getElementById("logout");

  btLogOut.addEventListener("click",e => {
    firebase.auth().signOut().then(function() {
        console.log('Signed Out');
        window.location.replace("first.html");
      }, function(error) {
        console.error('Sign Out Error', error);
      });
});


