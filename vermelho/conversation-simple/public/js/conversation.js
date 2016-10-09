// The ConversationPanel module is designed to handle
// all display and behaviors of the conversation column of the app.
/* eslint no-unused-vars: "off" */
/* global Api: true, Common: true*/

var ConversationPanel = (function() {
  var settings = {
    selectors: {
      chatBox: '#scrollingChat',
      fromUser: '.from-user',
      fromWatson: '.from-watson',
      latest: '.latest'
    },
    authorTypes: {
      user: 'user',
      watson: 'watson'
    }
  };

  var availableRoutes = {transit: false, driving: false}
  // Publicly accessible methods defined
  return {
    init: init,
    inputKeyDown: inputKeyDown,
    displayMessage: displayMessage,
    settings: settings
  };

  function initMap(address) {
  // console.log("Batata")
  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;
  var directionsDisplayCar = new google.maps.DirectionsRenderer;
  
  var map = new google.maps.Map(document.getElementById('mapbus'), {
    zoom: 15,
    center: {lat: -25.412916, lng: -49.267349}
  });
  
  var mapcar = new google.maps.Map(document.getElementById('mapcar'), {
    zoom: 15,
    center: {lat: -25.412916, lng: -49.267349}
  });
  
  directionsDisplay.setMap(map);
  directionsDisplayCar.setMap(mapcar);
  // directionsDisplay.setPanel(document.getElementById('right-panel'));
  
  // console.log(calculateAndDisplayRoute(directionsService, directionsDisplay, address));
  calculateAndDisplayRoute(directionsService, directionsDisplay, address, google.maps.TravelMode.TRANSIT);
  calculateAndDisplayRoute(directionsService, directionsDisplayCar, address, google.maps.TravelMode.DRIVING);
  // document.getElementById('start').addEventListener('change', onChangeHandler);
  // document.getElementById('end').addEventListener('change', onChangeHandler);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay, address, travelMode) {
  // console.log(address);
  directionsService.route({
    origin: "Rua Deputado Mario de Barros, 1290, Juvevê",
    destination: address,
    travelMode: travelMode
  }, function(response, status) {
    // console.log(status);
    if (status === google.maps.DirectionsStatus.OK) {
      availableRoutes.transit = travelMode == google.maps.TravelMode.TRANSIT ? true : availableRoutes.transit;
      availableRoutes.driving = travelMode == google.maps.TravelMode.DRIVING ? true : availableRoutes.driving;
      directionsDisplay.setDirections(response);
      // console.log(this);
      // console.log("Tenta buscar a localização");
      // jsonResponse.output.text = "Hey, não encontrei resultados precisos. Que tal repetir o local com mais informações?"
      // displayMessage(jsonResponse, settings.authorTypes.watson);
      if (travelMode == google.maps.TravelMode.DRIVING) {
        console.log(this.Api.getResponsePayload().output.text);
        this.Api.getResponsePayload().output.text = ["É nesse local que você quer ir?"];
        displayMessage(this.Api.getResponsePayload(), 'watson');
      }
    } else {
      if (travelMode === google.maps.TravelMode.DRIVING) {
        console.log("Não achei.");
        var latestResponse = Api.getResponsePayload();
        if (latestResponse) {
          context = latestResponse.context;
        }
        // console.log(context);
        // Send the user message
        Api.sendRequest("notfound", context);
        //Common.fireEvent(inputBox, 'input');
      }
    }
  });
}
  // Initialize the module
  function init() {
    chatUpdateSetup();
    Api.sendRequest( '', null );
    setupInputBox();
  }
  // Set up callbacks on payload setters in Api module
  // This causes the displayMessage function to be called when messages are sent / received
  function chatUpdateSetup() {
    var currentRequestPayloadSetter = Api.setRequestPayload;
    // console.log(currentRequestPayloadSetter);
    Api.setRequestPayload = function(newPayloadStr) {
      currentRequestPayloadSetter.call(Api, newPayloadStr);
      displayMessage(JSON.parse(newPayloadStr), settings.authorTypes.user);
    };

    var currentResponsePayloadSetter = Api.setResponsePayload;
    Api.setResponsePayload = function(newPayloadStr) {
      var jsonResponse = JSON.parse(newPayloadStr);
      console.log(jsonResponse);
      var isDestinations = false;
      var isWork = false;
      for (var i = 0; i < jsonResponse.entities.length; i++) {
        if (jsonResponse.entities[i].entity === 'destinations') {
          var isCapabilities = false;
          var isWork = jsonResponse.entities[i].value == 'trabalho';
          for (var j = 0; j < jsonResponse.intents.length; j++) {
            if (jsonResponse.intents[j].intent === 'capabilities') {
              isCapabilities = true;
            }
          }
          if (!isCapabilities) {
            isDestinations = true;
            if (isWork) {
              //Must get the workplace from user
              initMap("Rua Hermenegildo Soares Machado - Roseira, São José dos Pinhais - State of Paraná");
            }
            else {
              initMap(jsonResponse.input.text);
            }
          }
        }
        if (jsonResponse.entities[i].entity === "confirma" && (availableRoutes.transit || availableRoutes.driving)) {
          console.log(availableRoutes.transit + " " + availableRoutes.driving)
          if (availableRoutes.transit && availableRoutes.driving) {
            jsonResponse.output.text = "Ok. Vamos lá, vamos de ônibus ou dirigindo?";
          }
          else if (availableRoutes.driving) {
            jsonResponse.output.text = "Bom, vamos lá. Só nos resta ir de carro mesmo. Boa viagem [Inicia viagem]";
          }
          else {
            jsonResponse.output.text = "Vamos lá então, o ônibus chega em aproximadamente 10 minutos. Ah, e leve o casaco, pois hoje em Curitiba está 16ºC, e a mínima é: 12ºC."; 
          }
        }
        // if (jsonResponse.entities[i].entity === "confirma" && (availableRoutes.transit || availableRoutes.driving)) {
        //   console.log(availableRoutes.transit + " " + availableRoutes.driving)
        //   if (availableRoutes.transit && availableRoutes.driving) {
        //     jsonResponse.output.text = "Ok. Vamos lá, vamos de ônibus ou dirigindo?";
        //   }
        //   else if (availableRoutes.driving) {
        //     jsonResponse.output.text = "Bom, vamos lá. Só nos resta ir de carro mesmo. Boa viagem [Inicia viagem]";
        //   }
        //   else {
        //     jsonResponse.output.text = "Vamos lá então, o ônibus chega em aproximadamente 10 minutos. Ah, e leve o casaco, pois hoje em Curitiba está 16ºC, e a mínima é: 12ºC."; 
        //   }
        // }
        else if (jsonResponse.entities[i].entity === "confirma") {
          console.log("É confirmação e não tem a rota");
          jsonResponse.output.text = "Para onde vamos?";
          // displayMessage(jsonResponse, settings.authorTypes.watson);
        }
      }
      if (!isDestinations) {
        currentResponsePayloadSetter.call(Api, newPayloadStr);
        displayMessage(jsonResponse, settings.authorTypes.watson);
      }
    };
  }

// Set up the input box to underline text as it is typed
  // This is done by creating a hidden dummy version of the input box that
  // is used to determine what the width of the input text should be.
  // This value is then used to set the new width of the visible input box.
  function setupInputBox() {
    var input = document.getElementById('textInput');
    var dummy = document.getElementById('textInputDummy');
    var minFontSize = 14;
    var maxFontSize = 16;
    var minPadding = 4;
    var maxPadding = 6;

    // If no dummy input box exists, create one
    if (dummy === null) {
      var dummyJson = {
        'tagName': 'div',
        'attributes': [{
          'name': 'id',
          'value': 'textInputDummy'
        }]
      };

      dummy = Common.buildDomElement(dummyJson);
      document.body.appendChild(dummy);
    }

    function adjustInput() {
      if (input.value === '') {
        // If the input box is empty, remove the underline
        input.classList.remove('underline');
        input.setAttribute('style', 'width:' + '100%');
        input.style.width = '100%';
      } else {
        // otherwise, adjust the dummy text to match, and then set the width of
        // the visible input box to match it (thus extending the underline)
        input.classList.add('underline');
        var txtNode = document.createTextNode(input.value);
        ['font-size', 'font-style', 'font-weight', 'font-family', 'line-height',
          'text-transform', 'letter-spacing'].forEach(function(index) {
            dummy.style[index] = window.getComputedStyle(input, null).getPropertyValue(index);
          });
        dummy.textContent = txtNode.textContent;

        var padding = 0;
        var htmlElem = document.getElementsByTagName('html')[0];
        var currentFontSize = parseInt(window.getComputedStyle(htmlElem, null).getPropertyValue('font-size'), 10);
        if (currentFontSize) {
          padding = Math.floor((currentFontSize - minFontSize) / (maxFontSize - minFontSize)
            * (maxPadding - minPadding) + minPadding);
        } else {
          padding = maxPadding;
        }

        var widthValue = ( dummy.offsetWidth + padding) + 'px';
        input.setAttribute('style', 'width:' + widthValue);
        input.style.width = widthValue;
      }
    }

    // Any time the input changes, or the window resizes, adjust the size of the input box
    input.addEventListener('input', adjustInput);
    window.addEventListener('resize', adjustInput);

    // Trigger the input event once to set up the input box and dummy element
    Common.fireEvent(input, 'input');
  }

  // Display a user or Watson message that has just been sent/received
  function displayMessage(newPayload, typeValue) {
    // console.log(newPayload.input);
    // console.log(newPayload.input.text === "notfound");
    var isUser = isUserMessage(typeValue);
    if (newPayload.input.text === "notfound" && isUser) {
      return;
    }
    else {
      var isUser = isUserMessage(typeValue);
      var textExists = (newPayload.input && newPayload.input.text)
        || (newPayload.output && newPayload.output.text);
      if (isUser !== null && textExists) {
        // Create new message DOM element
        var messageDivs = buildMessageDomElements(newPayload, isUser);
        var chatBoxElement = document.querySelector(settings.selectors.chatBox);
        var previousLatest = chatBoxElement.querySelectorAll((isUser
                ? settings.selectors.fromUser : settings.selectors.fromWatson)
                + settings.selectors.latest);
        // Previous "latest" message is no longer the most recent
        if (previousLatest) {
          Common.listForEach(previousLatest, function(element) {
            element.classList.remove('latest');
          });
        }

        messageDivs.forEach(function(currentDiv) {
          chatBoxElement.appendChild(currentDiv);
          // Class to start fade in animation
          currentDiv.classList.add('load');
        });
        // Move chat to the most recent messages when new messages are added
        scrollToChatBottom();
      }
    }
  }

  // Checks if the given typeValue matches with the user "name", the Watson "name", or neither
  // Returns true if user, false if Watson, and null if neither
  // Used to keep track of whether a message was from the user or Watson
  function isUserMessage(typeValue) {
    if (typeValue === settings.authorTypes.user) {
      return true;
    } else if (typeValue === settings.authorTypes.watson) {
      return false;
    }
    return null;
  }

  // Constructs new DOM element from a message payload
  function buildMessageDomElements(newPayload, isUser) {
    var textArray = isUser ? newPayload.input.text : newPayload.output.text;
    if (Object.prototype.toString.call( textArray ) !== '[object Array]') {
      textArray = [textArray];
    }
    var messageArray = [];

    textArray.forEach(function(currentText) {
      if (currentText) {
        var messageJson = {
          // <div class='segments'>
          'tagName': 'div',
          'classNames': ['segments'],
          'children': [{
            // <div class='from-user/from-watson latest'>
            'tagName': 'div',
            'classNames': [(isUser ? 'from-user' : 'from-watson'), 'latest', ((messageArray.length === 0) ? 'top' : 'sub')],
            'children': [{
              // <div class='message-inner'>
              'tagName': 'div',
              'classNames': ['message-inner'],
              'children': [{
                // <p>{messageText}</p>
                'tagName': 'p',
                'text': currentText
              }]
            }]
          }]
        };
        messageArray.push(Common.buildDomElement(messageJson));
      }
    });

    return messageArray;
  }

  // Scroll to the bottom of the chat window (to the most recent messages)
  // Note: this method will bring the most recent user message into view,
  //   even if the most recent message is from Watson.
  //   This is done so that the "context" of the conversation is maintained in the view,
  //   even if the Watson message is long.
  function scrollToChatBottom() {
    var scrollingChat = document.querySelector('#scrollingChat');

    // Scroll to the latest message sent by the user
    var scrollEl = scrollingChat.querySelector(settings.selectors.fromUser
            + settings.selectors.latest);
    if (scrollEl) {
      scrollingChat.scrollTop = scrollEl.offsetTop;
    }
  }

  // Handles the submission of input
  function inputKeyDown(event, inputBox) {
    // Submit on enter key, dis-allowing blank messages
    if (event.keyCode === 13 && inputBox.value) {
      // Retrieve the context from the previous server response
      var context;
      var latestResponse = Api.getResponsePayload();
      if (latestResponse) {
        context = latestResponse.context;
      }
      // console.log(context);
      // Send the user message
      Api.sendRequest(inputBox.value, context);

      // Clear input box for further messages
      inputBox.value = '';
      Common.fireEvent(inputBox, 'input');
    }
  }
}());
